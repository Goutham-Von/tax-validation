package com.txnvalidation;

public enum ValidationStatus {
    VALID,
    INVALID,
    REVALIDATE;
    // if needed we can add _UNKNOWN because some api services are not
    // returning the response immediately calling the endpoint.
}
