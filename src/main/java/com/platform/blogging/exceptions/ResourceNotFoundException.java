package com.platform.blogging.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        System.out.println(s);
    }
}
