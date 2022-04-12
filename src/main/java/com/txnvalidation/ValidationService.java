package com.txnvalidation;

import com.txnvalidation.validators.FonoaValidator;
import com.txnvalidation.validators.TaxIDProValidtor;
import com.txnvalidation.validators.TemplateValidator;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ValidationService {
    public static  ValidationService service = new ValidationService();
    private static TemplateValidator taxIDProValidator;
    private static TemplateValidator fonoaValidator;

    private static final String ACCESSKEY_FILENAME = "accesskeys.properties";
    private static Properties accesskeys;
    private static DbConnectionService dbService = DbConnectionService.dbService;

    private Proxy proxy = null;

    /**
     * To provide accesskeys
     * @param filename the name of the .properties file which contains
     *        accesskeys {@code String}
     * @return an properties which contains accesskey-value pairs obtained
     *         from the given file.
     */
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

    /**
     * constructor is being used to Loads properties from the given accesskey
     * filename and instantiate the different validators to use their static
     * methods.
     */
    public ValidationService() {
        accesskeys = loadPropTest(ACCESSKEY_FILENAME);
        taxIDProValidator = new TaxIDProValidtor
                (accesskeys.getProperty("BASEURL.TAXIDPRO"), accesskeys.getProperty("KEY.TAXIDPRO"));
        fonoaValidator = new FonoaValidator
                (accesskeys.getProperty("BASEURL.FONOA"), accesskeys.getProperty("KEY.FONOA"));
    }

    /**
     * returns an suitable Validator {@link TemplateValidator} by querying the
     * database.
     * @param countryName
     * @return {@code TemplateValidator}
     */
    public static TemplateValidator validator(String countryName) {
//        List<String> daoService = Arrays.asList("in", "TaxIDProValidator", "regex");
        List<String> daoService = DbConnectionService.getResults(countryName);
        if(daoService.get(1).equalsIgnoreCase("fonoaValidator")) {
            return fonoaValidator;
        } else if(daoService.get(1).equalsIgnoreCase("TaxIDProValidator"))
            return taxIDProValidator;
        return null;
    }
}


