package com.pankaj.userresponce.model;

import java.util.ArrayList;

public class LogoutUserResponce {
    private int code;
    private String message;
    private boolean status;
    private ArrayList<LogoutUserResponce> data = null;
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

    public ArrayList<LogoutUserResponce> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }
}
