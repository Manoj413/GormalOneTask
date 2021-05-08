package com.gormal.gormalonetask.core;

public class Validation {
    public boolean validText(String text){
        return !text.isEmpty() && !text.equals("") && !text.equals("null") && text!=null;
    }
}
