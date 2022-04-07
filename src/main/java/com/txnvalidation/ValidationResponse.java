package com.txnvalidation;

import org.json.JSONObject;

public class ValidationResponse {
    private int reponseCode;
    private JSONObject message;
    private ValidationStatus status = ValidationStatus.NOT_VALID;

    public int getReponseCode() {
        return reponseCode;
    }

    public void setReponseCode(int reponseCode) {
        this.reponseCode = reponseCode;
    }

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
