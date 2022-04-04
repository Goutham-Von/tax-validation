package com.txnvalidation.exceptions;


import java.util.List;

/**
 * Base class for MessageBird exceptions that can contain Error codes from the REST service
 *
 * Created by rvt on 1/6/15.
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
