package com.txnvalidation;

import org.json.JSONObject;

import java.util.Date;

public class ValidationResponse {
    private int reponseCode;
    private JSONObject message;
    private ValidationStatus status = ValidationStatus.INVALID;
    private Date timeStamp;

    public ValidationResponse() {
        timeStamp = new Date();
    }

    public int getReponseCode() {
        return reponseCode;
    }

    public void setReponseCode(int reponseCode) {
        this.reponseCode = reponseCode;
    }

    public void setTimeStamp() { timeStamp = new Date(); }

    public String getTimeStamp() { return timeStamp.toString(); }

    public JSONObject getMessage() {
        return message;
    }

    public void setMessage(JSONObject message) {
        this.message = message;
    }

    public ValidationStatus getStatus() {
        return status;
    }

    public void setStatus(ValidationStatus status) {
        this.status = status;
    }
}
