package com.kod95.truckmanagmentsystem.controller.management;

import com.kod95.truckmanagmentsystem.dto.request.AuthenticationRequest;
import com.kod95.truckmanagmentsystem.dto.response.AuthenticationResponse;
import com.kod95.truckmanagmentsystem.security.AuthenticationService;
import com.kod95.truckmanagmentsystem.security.CustomUserDetailsService;
import com.kod95.truckmanagmentsystem.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        // Authenticate the user using the AuthenticationService
        authenticationService.authenticate(authenticationRequest.username(), authenticationRequest.password());

        // Load user details to generate JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        // Return the generated JWT in the response
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
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
