package com.desafio.modules.conta_api.exceptions;

public class ContaNotFoundException extends RuntimeException {

    public ContaNotFoundException() {
        super("Conta não encontrada");
    }
}
