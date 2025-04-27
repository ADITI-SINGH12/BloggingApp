package com.platform.blogging.exceptions;

public class ApiExceptionHandler extends RuntimeException {
    public ApiExceptionHandler(String s) {
        System.out.println(s);
    }
}
