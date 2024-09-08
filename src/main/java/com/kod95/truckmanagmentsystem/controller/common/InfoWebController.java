package com.kod95.truckmanagmentsystem.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/v1/info")
@RequiredArgsConstructor
public class InfoWebController {

    private final MessageSource messageSource;

    @GetMapping(value = "/message" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMessage(@RequestHeader(name = "Accept-Language", required = false) Locale locale
            , @RequestParam(name = "key") String key){
        String greetingMessage = messageSource.getMessage(key, null, locale);
        return ResponseEntity.ok(greetingMessage);
    }

}
