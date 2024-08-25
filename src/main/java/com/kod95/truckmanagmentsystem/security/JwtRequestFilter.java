package com.kod95.truckmanagmentsystem.security;

import com.kod95.truckmanagmentsystem.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    public JwtRequestFilter(CustomUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtTokenUtil.extractUsername(jwt);
                logger.info("JWT token found, processing user: {}", username);
            } catch (ExpiredJwtException e) {
                logger.warn("JWT token has expired for user: {}", e.getClaims().getSubject());
                handleException(response, "JWT token has expired", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } catch (Exception e) {
                logger.error("JWT token processing failed: {}", e.getMessage());
                handleException(response, "JWT token processing failed", HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                logger.info("JWT token validated and user authenticated: {}", username);
            } else {
                logger.warn("Invalid JWT token for user: {}", username);
            }
        }

        chain.doFilter(request, response);
    }

    private void handleException(HttpServletResponse response, String errorMessage, int statusCode) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
    }
}
