package com.sysops.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/okta")
public class OktaController {
    @GetMapping("/secured")
    public ResponseEntity<String> getStringByPassingToken() {
        return new ResponseEntity<>("Congrats! Your access token is valid", HttpStatus.OK);

    }

}
