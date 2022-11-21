package com.example.springbootapp.model;

import java.util.ArrayList;

public class ErrorResponse {
    public ArrayList<Error> errors;

    public ArrayList<Error> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<Error> errors) {
        this.errors = errors;
    }

	@Override
	public String toString() {
		return "AuthenticationErrorResponse [errors=" + errors + "]";
	}
    
    

}
