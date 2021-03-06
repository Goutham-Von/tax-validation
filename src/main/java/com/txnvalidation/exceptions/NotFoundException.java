package com.txnvalidation.exceptions;

import java.util.List;

/**
 * Created by rvt on 1/9/15.
 */
public class NotFoundException extends TxnValidationException {
    public NotFoundException(List<ErrorReport> errors) {
        super(errors);
    }

    public NotFoundException(String s, List<ErrorReport> errors) {
        super(s, errors);
    }

    public NotFoundException(String s, Throwable throwable, List<ErrorReport> errors) {
        super(s, throwable, errors);
    }

    public NotFoundException(Throwable throwable, List<ErrorReport> errors) {
        super(throwable, errors);
    }
}
