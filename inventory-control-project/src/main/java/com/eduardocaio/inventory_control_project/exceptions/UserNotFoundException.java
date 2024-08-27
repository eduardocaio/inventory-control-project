package com.eduardocaio.inventory_control_project.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Usuário não encontrado");
    }

    public UserNotFoundException(String msg){
        super(msg);
    }

}
