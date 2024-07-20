package com.cryptomarket.cryptoexchange.exceptions;

import lombok.ToString;

@ToString
public class ApiRequestRuntimeException extends RuntimeException {

    public ApiRequestRuntimeException(String message) {
        super(message);
    }
}
