package com.kod95.truckmanagmentsystem.util;

import io.micrometer.common.util.StringUtils;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class SecurityUtils {

    public static final String BEARER = "Bearer ";

    public static Optional<String> parseJwtFromAuthorizationHeader(String authorizationHeader) {
        if (StringUtils.isBlank(authorizationHeader) || !authorizationHeader.startsWith(BEARER)) {
            return Optional.empty();
        }

        return Optional.of(authorizationHeader.substring(BEARER.length()));
    }

}
