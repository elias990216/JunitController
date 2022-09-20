package com.minsait.JunitControllers.exceptions;

public class DineroInsuficienteException extends RuntimeException{
    public DineroInsuficienteException(String msj){
        super(msj);
    }
}
