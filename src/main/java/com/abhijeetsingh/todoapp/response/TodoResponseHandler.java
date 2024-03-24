package com.abhijeetsingh.todoapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class TodoResponseHandler {

    public static ResponseEntity<Object> generateSuccessResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("message", message);
        mp.put("status", status.value());
        mp.put("data", responseObj);
        mp.put("error", "");
        return new ResponseEntity<Object>(mp,status);
    }

    public static ResponseEntity<Object> generateErrorResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("message", message);
        mp.put("status", status.value());
        mp.put("data","");
        mp.put("error",responseObj);

        return new ResponseEntity<Object>(mp,status);
    }


}
