package com.pankaj.userresponce.model;

import java.util.ArrayList;

public class CreateUserResponce {
    private int code;
    private String message;
    private boolean status;
    private ArrayList<CreateUserResponce> data = null;
    private boolean error;
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public ArrayList<CreateUserResponce> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }
}
