package com.bob.bobmobileapp.tools.validators;

/**
 * Created by user on 05/09/2017.
 */

public interface Validator {

    boolean isValid(String text);

    String getErrorMessage();

}
