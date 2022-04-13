package com.txnvalidation;

import com.txnvalidation.validators.TemplateValidator;
import javafx.util.Pair;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class ValidationService {
    public static ValidationService service = new ValidationService();
    public static Map<String, TemplateValidator> mappingValidators;

    private static final String ACCESSKEY_FILENAME = "accesskeys.properties";
    private static Properties accesskeys;
    private static DbConnectionService dbService = DbConnectionService.dbService;

    private Proxy proxy = null;

    /**
     * To provide accesskeys
     *
     * @param filename the name of the .properties file which contains
     *                 accesskeys {@code String}
     * @return an properties which contains accesskey-value pairs obtained
     * from the given file.
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
        mappingValidators = new HashMap<>();
        accesskeys = loadPropTest(ACCESSKEY_FILENAME);
        DbConnectionService.getAllValidators()
                .forEach((validator) -> {
                    try {
                        TemplateValidator validatorInstance = (TemplateValidator) Class.forName
                                ("com.txnvalidation.validators." + validator).newInstance();
                        validatorInstance.setAccessKey(
                                accesskeys.getProperty("KEY." + validator));
                        validatorInstance.setBaseUrl(
                                accesskeys.getProperty("BASEURL." + validator));
                        mappingValidators.put(validator, validatorInstance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * returns an suitable Validator {@link TemplateValidator} by querying the
     * database.
     *
     * @param countryName
     * @return {@code TemplateValidator}
     */
    public static Pair<String, List<String>> validator(String countryName,
                                                        String txnNumber) {
        Pair<String, String> codeAndRegex =
                DbConnectionService.getCountry(countryName);
        String countryCode = codeAndRegex.getKey();
        String regex = codeAndRegex.getValue();
        if (regex != null && regex.length() != 0) {
            Pattern regexPattern = Pattern.compile(regex);
            if (!regexPattern.matcher(txnNumber).matches()) { return null; }
        }
        return new Pair<String, List<String>>(countryCode,
                DbConnectionService.getValidators(countryCode));
    }
}


