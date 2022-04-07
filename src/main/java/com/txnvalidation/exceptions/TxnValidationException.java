package com.txnvalidation.exceptions;


import java.util.List;

/**
  * https://github.com/messagebird/java-rest-api/tree/master/api/src/main/java/com/messagebird/exceptions
  */
public class TxnValidationException extends Exception {
    private List<ErrorReport> errors;

    public TxnValidationException(List<ErrorReport> errors) {
        this.errors = errors;
    }

    public TxnValidationException(String s, List<ErrorReport> errors) {
        super(s);
        this.errors = errors;
    }

    public TxnValidationException(String s, Throwable throwable, List<ErrorReport> errors) {
        super(s, throwable);
        this.errors = errors;
    }

    public TxnValidationException(Throwable throwable, List<ErrorReport> errors) {
        super(throwable);
        this.errors = errors;
    }

    public List<ErrorReport> getErrors() {
        return errors;
    }
}
