package com.coderhouse.java.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generate(Object message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);

        return new ResponseEntity<>(map, status);
    }

}