package com.txnvalidation;

import com.txnvalidation.validators.TemplateValidator;
import javafx.util.Pair;

import java.util.List;

/**
 * TaxValidation.class is used to make calls to know whether the tax number for
 * the given country is valid or invalid with use of Validators
 * {@link TemplateValidator}, which are obtained using the validation service
 * {@link ValidationServiceI}
 */

public class TaxValidation {

    private static ValidationService validatorService = ValidationService.service;

    public static ValidationResponse txnValidate(String txnNumber,String countryName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Pair<String, List<String>> validators =  validatorService.validator(countryName, txnNumber);
        validators.getValue().forEach(
                (validator)-> System.out.println(ValidationService.mappingValidators.get(validator)
                        .isValid(txnNumber, validators.getKey()).getStatus()));
        return null;
    }

}
