package com.desafio.modules.conta_api.exceptions;

public class UserFoundException extends RuntimeException {

    public UserFoundException() {
        super("Usuário já existe");
    }
}
