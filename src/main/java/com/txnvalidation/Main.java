package com.txnvalidation;

public class Main {
    public static void main(String[] args) {
        System.out.println(TaxValidation.txnValidate("07AAACR4849R1ZN", "in").getStatus());
    }
}
