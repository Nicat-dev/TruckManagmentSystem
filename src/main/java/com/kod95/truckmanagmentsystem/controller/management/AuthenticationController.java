package com.kod95.truckmanagmentsystem.controller.management;

import com.kod95.truckmanagmentsystem.dto.request.AuthenticationRequest;
import com.kod95.truckmanagmentsystem.dto.response.AuthenticationResponse;
import com.kod95.truckmanagmentsystem.security.AuthenticationService;
import com.kod95.truckmanagmentsystem.security.CustomUserDetailsService;
import com.kod95.truckmanagmentsystem.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception {
        // Authenticate the user using the AuthenticationService
        Authentication authentication = authenticationService.authenticate(authenticationRequest.username(), authenticationRequest.password());

        // Load user details to generate JWT
        final String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

        // Return the generated JWT in the response
        var authResponse = new AuthenticationResponse(jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwt);
        return ResponseEntity.ok().headers(httpHeaders).body(authResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            String username = jwtTokenUtil.extractUsername(jwt);

            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                    String newToken = jwtTokenUtil.generateToken(userDetails);
                    return ResponseEntity.ok(new AuthenticationResponse(newToken));
                }
            }
        }

        return ResponseEntity.badRequest().body("Invalid JWT token");
    }
}
