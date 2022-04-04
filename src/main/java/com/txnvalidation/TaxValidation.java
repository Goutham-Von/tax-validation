package com.txnvalidation;

import com.txnvalidation.validators.TemplateValidator;

public class TaxValidation {
    private static ValidationService validatorService = ValidationService.service;

    public static ValidationStatus txnValidate(String txn_number,String countryCode) {

        TemplateValidator validator =  validatorService.validator(countryCode);
        return validator.isValid(txn_number, countryCode);
    }

}
