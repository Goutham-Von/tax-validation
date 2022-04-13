package com.txnvalidation;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.txnvalidation.validators.TemplateValidator;

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

        List<String> validator =  validatorService.validator(countryName, txnNumber);
        TemplateValidator ok = (TemplateValidator) Class.forName("com.txnvalidation.validators.TaxIDProValidtor").newInstance();
        return ok.isValid(txnNumber, "in");
    }

}
