package com.txnvalidation;

import com.txnvalidation.validators.TemplateValidator;

/**
 * TaxValidation.class is used to make calls to know whether the tax number for
 * the given country is valid or invalid with use of Validators
 * {@link TemplateValidator}, which are obtained using the validation service
 * {@link ValidationServiceI}
 */

public class TaxValidation {

    private static ValidationService validatorService = ValidationService.service;

    public static ValidationResponse txnValidate(String txn_number,String countryCode) {

        TemplateValidator validator =  validatorService.validator(countryCode);
        return validator.isValid(txn_number, countryCode);
    }

}
