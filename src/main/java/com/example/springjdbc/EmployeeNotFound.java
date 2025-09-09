package com.example.springjdbc;

public class EmployeeNotFound extends  RuntimeException {
    public EmployeeNotFound(String message){
        super(message);
    }
}
