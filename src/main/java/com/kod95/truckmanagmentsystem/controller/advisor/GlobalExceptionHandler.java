package com.kod95.truckmanagmentsystem.controller.advisor;

import com.kod95.truckmanagmentsystem.model.constant.ExceptionMessage;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.kod95.truckmanagmentsystem.model.constant.ExceptionMessage.TOKEN_IS_INVALID_EXCEPTION;
import static com.kod95.truckmanagmentsystem.model.constant.ExceptionMessage.USER_CREDENTIALS_WRONG;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(TOKEN_IS_INVALID_EXCEPTION);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(USER_CREDENTIALS_WRONG);
    }

}
