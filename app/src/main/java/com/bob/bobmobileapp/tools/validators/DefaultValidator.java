package com.bob.bobmobileapp.tools.validators;

/**
 * Created by user on 04/10/2017.
 */

public class DefaultValidator extends Validator {

    @Override
    public String validate(String text) {
        if (text.isEmpty()) {
            return "Please enter a text!";
        }
        return null;
    }

}
