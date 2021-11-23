package com.ebf.employee.controllers.exceptions;

public class CompanyNotFoundException extends Exception {
    public CompanyNotFoundException(String message){
        super(message);
    }
}
