package com.txnvalidation;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        TaxValidation.txnValidate("07AAACR4849R1ZN",
                "India");
        DbConnectionService.close(DbConnectionService.connection);
    }
}
