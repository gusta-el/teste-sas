package com.teste.sas.testeSas.handler;

import java.util.Objects;

public class BusinessExpection extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessExpection(String message) {
        super(Objects.requireNonNull(message));
    }
}
