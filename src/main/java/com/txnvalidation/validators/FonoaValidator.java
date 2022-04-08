package com.txnvalidation.validators;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.txnvalidation.ValidationResponse;
import com.txnvalidation.ValidationStatus;
import com.txnvalidation.exceptions.ErrorReport;
import com.txnvalidation.exceptions.NotFoundException;
import com.txnvalidation.exceptions.UnauthorizedException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FonoaValidator extends TemplateValidator {
    public FonoaValidator(String baseurlFonoa, String accesskeyFonoa) {
        super(baseurlFonoa, accesskeyFonoa);
    }

    @Override
    public ValidationResponse isValid(String txn_number, String countryCode) {
        Request request = requestBuilder(baseUrl, "POST",
                null,
                new HashMap<String, String>() {{
                    put("Accept","application/json");
                    put("Ocp-Apim-Subscription-Key", accessKey);
                }},
                new HashMap<String, String>() {{
                    put("country_iso",countryCode);
                    put("tin", txn_number);
                    put("check_tin_online", "true");
                }}
                );
        ValidationResponse validationResponse = new ValidationResponse();
        try {
            Response response = getResponse(request);
            JSONObject body = new JSONObject(response.body().string());
            validationResponse.setReponseCode(response.code());
            validationResponse.setMessage(body);
            if (response.code()<=299 && response.code()>=200) {
                if (body.getString("status").equalsIgnoreCase("completed")) {
                    if (body.getJSONObject("validation").getBoolean("tin_exists_online")) {
                        validationResponse.setStatus(ValidationStatus.VALID);
                    } else {
                        validationResponse.setStatus(ValidationStatus.NOT_VALID);
                    }
                } else {
                    validationResponse.setStatus(ValidationStatus.REVALIDATE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validationResponse;
    }
}
