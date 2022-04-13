package com.txnvalidation.exceptions;

import java.util.List;

/**
 * General exceptions send by the system. It might or might not contain a list of error codes
 * <p>
 * Created by rvt on 1/5/15.
 */
public class InvalidDataTypeException extends TxnValidationException {
    private Integer responseCode = null;

    public InvalidDataTypeException(List<ErrorReport> errors) {
        super(errors);
    }

    public InvalidDataTypeException(String s, List<ErrorReport> errors) {
        super(s, errors);
    }

    public InvalidDataTypeException(String s) {
        super(s, null);
    }

    public InvalidDataTypeException(String s, Throwable throwable, List<ErrorReport> errors) {
        super(s, throwable, errors);
    }

    public InvalidDataTypeException(Throwable throwable, List<ErrorReport> errors) {
        super(throwable, errors);
    }

    public InvalidDataTypeException(Throwable throwable) {
        super(throwable, null);
    }

    public InvalidDataTypeException(String s, Integer responseCode) {
        this(s);
        this.responseCode = responseCode;
    }

    public InvalidDataTypeException(String s, int responseCode, List<ErrorReport> errorReport) {
        super(s, errorReport);
        this.responseCode = responseCode;
    }

    public Integer getResponseCode() {
        return responseCode;
    }
}
