package com.api.gateway.api.gateway.Exception;


public class TokenExpireException  extends RuntimeException{

    public TokenExpireException(String message) {
        super(message);
    }
}
