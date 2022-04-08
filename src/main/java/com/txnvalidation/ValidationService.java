package com.txnvalidation;

import com.txnvalidation.validators.FonoaValidator;
import com.txnvalidation.validators.TaxIDProValidtor;
import com.txnvalidation.validators.TemplateValidator;

import java.io.IOException;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class ValidationService {
    public static  ValidationService service = new ValidationService();
    private static TemplateValidator taxIDProValidator;
    private static TemplateValidator fonoaValidator;

    private static final String ACCESSKEY_FILENAME = "accesskeys.properties";
    private static Properties accesskeys;

    private Proxy proxy = null;

    public static Properties loadPropTest(String filename) {

        ClassLoader classLoader = ValidationService.class.getClassLoader();
        URL resource = classLoader.getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + filename);
        }
        Properties accesskeys = new Properties();
        try {
            accesskeys.load(resource.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accesskeys;
    }

    public ValidationService() {
        accesskeys = loadPropTest(ACCESSKEY_FILENAME);
        taxIDProValidator = new TaxIDProValidtor
                (accesskeys.getProperty("BASEURL.TAXIDPRO"), accesskeys.getProperty("KEY.TAXIDPRO"));
        fonoaValidator = new FonoaValidator
                (accesskeys.getProperty("BASEURL.FONOA"), accesskeys.getProperty("KEY.FONOA"));
    }

    public static TemplateValidator validator(String countryCode) {
        if(countryCode.equalsIgnoreCase("EN")) {
            return fonoaValidator;
        }
        return taxIDProValidator;
    }
}


