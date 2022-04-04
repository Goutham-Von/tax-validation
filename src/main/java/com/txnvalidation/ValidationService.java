package com.txnvalidation;

import com.txnvalidation.validators.FonoaValidator;
import com.txnvalidation.validators.TaxIDProValidtor;
import com.txnvalidation.validators.TemplateValidator;

import java.net.Proxy;

public class ValidationService {
    private static final String BASEURL_TAXIDPRO = "https://api.taxid.pro/validate";
    private static final String ACCESSKEY_TAXIDPRO = "B8jMAFrNdC3kzcpYJRWmgzDAEXyRTK9G";
    private static final String BASEURL_FONOA = "https://api-demo.fonoa.com/Lookup/v1/Validations";
    private static final String ACCESSKEY_FONOA = "8b144782eec54b159917c8c4a367d694";


    private static TemplateValidator taxIDProValidator;
    private static TemplateValidator fonoaValidator;
    public static  ValidationService service = new ValidationService();

    private Proxy proxy = null;

    public ValidationService() {
        taxIDProValidator = new TaxIDProValidtor(BASEURL_TAXIDPRO, ACCESSKEY_TAXIDPRO);
        fonoaValidator = new FonoaValidator(BASEURL_FONOA, ACCESSKEY_FONOA);
    }

    public static TemplateValidator validator(String countryCode) {
        if(countryCode.equalsIgnoreCase("EN")) {
            return fonoaValidator;
        }
        return taxIDProValidator;
    }
}


