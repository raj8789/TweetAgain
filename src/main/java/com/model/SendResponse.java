package com.model;

public class SendResponse {
    private  int statusCode;
    private String message;
    public SendResponse(String message,int statusCode)
    {
        this.message=message;
        this.statusCode=statusCode;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
