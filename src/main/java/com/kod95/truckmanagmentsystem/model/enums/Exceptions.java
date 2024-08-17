package com.kod95.truckmanagmentsystem.model.enums;

import com.kod95.truckmanagmentsystem.model.constant.ExceptionMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Exceptions {
    TOKEN_IS_INVALID_EXCEPTION(HttpStatus.BAD_REQUEST, ExceptionMessage.TOKEN_IS_INVALID_EXCEPTION),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, ExceptionMessage.USER_NOT_FOUND_EXCEPTION),
    PASSWORD_DOESNT_MATCH(HttpStatus.NOT_FOUND, ExceptionMessage.PASSWORD_DOESNT_MATCH),
    RESOURCE_EXIST_EXCEPTION(HttpStatus.BAD_REQUEST, ExceptionMessage.RESOURCE_EXIST_EXCEPTION),
    RESOURCE_NOT_EXIST_EXCEPTION(HttpStatus.NOT_FOUND, ExceptionMessage.RESOURCE_NOT_EXIST_EXCEPTION),
    RESOURCE_ID_CAN_NOT_BE_NULL(HttpStatus.NOT_FOUND, ExceptionMessage.RESOURCE_ID_CAN_NOT_BE_NULL),
    TOKEN_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, ExceptionMessage.TOKEN_NOT_FOUND_EXCEPTION),
    CUSTOMER_CANNOT_FOUND(HttpStatus.NOT_FOUND,ExceptionMessage.CUSTOMER_NOT_FOUND);

    private final HttpStatus httpStatus;
    private final String message;
}
