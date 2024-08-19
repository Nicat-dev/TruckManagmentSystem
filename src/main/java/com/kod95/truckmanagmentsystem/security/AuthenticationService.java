package com.kod95.truckmanagmentsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public void authenticate(String username, String password) throws Exception {
        try {
            System.out.println("Attempting authentication for user: " + username);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("Authentication successful for user: " + username);
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed for user: " + username);
            throw new Exception("Incorrect username or password", e);
        }
    }

}

