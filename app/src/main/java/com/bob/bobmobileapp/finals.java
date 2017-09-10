package com.bob.bobmobileapp;

import java.util.HashMap;

/**
 * Created by user on 11/09/2017.
 */

public class finals {

    public static final HashMap<String,Integer> inputTypes;
    static {
        inputTypes = new HashMap<String, Integer>();
        inputTypes.put("date", 0x14);
        inputTypes.put("datetime", 0x4);
        inputTypes.put("none", 0x0);
        inputTypes.put("number", 0x2);
        inputTypes.put("numberDecimal", 0x2002);
        inputTypes.put("numberPassword", 0x12);
        inputTypes.put("numberSigned", 0x1002);
        inputTypes.put("phone", 0x3);
        inputTypes.put("text", 0x1);
        inputTypes.put("textAutoComplete", 0x10001);
        inputTypes.put("textAutoCorrect", 0x8001);
        inputTypes.put("textCapCharacters", 0x1001);
        inputTypes.put("textCapSentences", 0x4001);
        inputTypes.put("textCapWords", 0x2001);
        inputTypes.put("textEmailAddress", 0x21);
        inputTypes.put("textEmailSubject", 0x31);
        inputTypes.put("textFilter", 0xb1);
        inputTypes.put("textImeMultiLine", 0x40001);
        inputTypes.put("textLongMessage", 0x51);
        inputTypes.put("textMultiLine", 0x20001);
        inputTypes.put("textNoSuggestions", 0x80001);
        inputTypes.put("textPassword", 0x81);
        inputTypes.put("textPersonName", 0x61);
        inputTypes.put("textPhonetic", 0xc1);
        inputTypes.put("textPostalAddress", 0x71);
        inputTypes.put("textShortMessage", 0x41);
        inputTypes.put("textUri", 0x11);
        inputTypes.put("textVisiblePassword", 0x91);
        inputTypes.put("textWebEditText", 0xa1);
        inputTypes.put("textWebEmailAddress", 0xd1);
        inputTypes.put("textWebPassword", 0xe1);
        inputTypes.put("time", 0x24);


    }

}
