package com.eduardocaio.inventory_control_project.exceptions;

public class VerificationUserException extends RuntimeException{

    public VerificationUserException(){
        super("Erro ao verificar código!");
    }

    public VerificationUserException(String msg){
        super(msg);
    }

}
