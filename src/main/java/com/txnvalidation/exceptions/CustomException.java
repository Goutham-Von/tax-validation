package com.txnvalidation.exceptions;

import java.util.List;

/**
 * General exceptions send by the system. It might or might not contain a list of error codes
 *
 * Created by rvt on 1/5/15.
 */
public class CustomException extends TxnValidationException {
    private Integer responseCode=null;
    public CustomException(List<ErrorReport> errors) {
        super(errors);
    }

    public CustomException(String s, List<ErrorReport> errors) {
        super(s, errors);
    }
    public CustomException(String s) {
        super(s, null);
    }

    public CustomException(String s, Throwable throwable, List<ErrorReport> errors) {
        super(s, throwable, errors);
    }

    public CustomException(Throwable throwable, List<ErrorReport> errors) {
        super(throwable, errors);
    }
    public CustomException(Throwable throwable) {
        super(throwable, null);
    }

    public CustomException(String s, Integer responseCode) {
        this(s);
        this.responseCode = responseCode;
    }

    public CustomException(String s, int responseCode, List<ErrorReport> errorReport) {
        super(s, errorReport);
        this.responseCode = responseCode;
    }

    public Integer getResponseCode() {
        return responseCode;
    }
}
