package com.txnvalidation.validators;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.txnvalidation.ValidationResponse;
import com.txnvalidation.ValidationStatus;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class TaxIDProValidator extends TemplateValidator {
    public TaxIDProValidator(String apiurlTaxidpro, String accesskeyTaxidpro) {
        super(apiurlTaxidpro, accesskeyTaxidpro);
    }

    public TaxIDProValidator() {
        super();
    }

    @Override
    public ValidationResponse isValid(String txnNumber, String countryCode) {
        Request request = requestBuilder(baseUrl, Method.GET,
                new HashMap<String, String>() {{
                    put("key", accessKey);
                    put("tin", txnNumber);
                    put("country", countryCode);
                    put("type", "vat");
                }},
                new HashMap<String, String>() {{
                    put("Accept", "application/json");
                }},
                null
        );
        ValidationResponse validationResponse = new ValidationResponse();
        try {
            Response response = getResponse(request);
            JSONObject body = new JSONObject(response.body().string());
            validationResponse.setReponseCode(response.code());
            validationResponse.setMessage(body);
            if (validationResponse.getReponseCode() == 200) {
                if (body.getBoolean("valid") == Boolean.TRUE) {
                    validationResponse.setStatus(ValidationStatus.VALID);
                } else if (body.getBoolean("valid") == Boolean.FALSE) {
                    validationResponse.setStatus(ValidationStatus.INVALID);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validationResponse;
    }
}
