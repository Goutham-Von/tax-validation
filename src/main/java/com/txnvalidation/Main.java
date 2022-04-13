package com.txnvalidation;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println(TaxValidation.txnValidate("07AAACR4849R1ZN", "india"));
        DbConnectionService.close(DbConnectionService.connection);
    }
}
