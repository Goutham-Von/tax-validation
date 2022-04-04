package com.txnvalidation.exceptions;

import java.util.List;

/**
 * Exception send when you are not authorised to use this service
 *
 * Created by rvt on 1/5/15.
 */
public class UnauthorizedException extends TxnValidationException {
    public UnauthorizedException(List<ErrorReport> errors) {
        super(errors);
    }

    public UnauthorizedException(String s, List<ErrorReport> errors) {
        super(s, errors);
    }

    public UnauthorizedException(String s, Throwable throwable, List<ErrorReport> errors) {
        super(s, throwable, errors);
    }

    public UnauthorizedException(Throwable throwable, List<ErrorReport> errors) {
        super(throwable, errors);
    }
}
