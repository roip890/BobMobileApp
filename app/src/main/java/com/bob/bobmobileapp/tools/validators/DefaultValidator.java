package com.bob.bobmobileapp.tools.validators;

/**
 * Created by user on 05/09/2017.
 */

public class DefaultValidator implements Validator {

    private String errorMessage;

    public DefaultValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DefaultValidator() {
        this("Please Enter Valid text!");
    }

    @Override
    public boolean isValid(String text) {

        if (text.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
