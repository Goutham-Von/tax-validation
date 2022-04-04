package com.txnvalidation;

public interface ValidationServiceI {
    <T> T requestByTxn(String txn_no, String countryCode);
}
