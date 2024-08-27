package com.eduardocaio.inventory_control_project.exceptions;

public class DataAlreadyRegisteredException extends RuntimeException{

    public DataAlreadyRegisteredException(){
        super("Dado já em uso!");
    }

    public DataAlreadyRegisteredException(String msg){
        super(msg);
    }
}
