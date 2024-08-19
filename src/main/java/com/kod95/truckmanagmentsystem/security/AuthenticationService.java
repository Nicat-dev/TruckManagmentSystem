package com.kod95.truckmanagmentsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

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

