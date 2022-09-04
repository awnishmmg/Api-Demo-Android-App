package com.pankaj.userresponce.model;

import java.util.ArrayList;

public class AddCustomerResponse {
    private int code;
    private String message;
    private boolean status;
    private ArrayList<AddCustomerResponse> data = null;
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

    public ArrayList<AddCustomerResponse> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }
}
