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
import java.util.List;

public class FonoaValidator extends TemplateValidator {
    public FonoaValidator(String baseurlFonoa, String accesskeyFonoa) {
        super(baseurlFonoa, accesskeyFonoa);
    }

    @Override
    public ValidationResponse isValid(String txn_number, String countryCode) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{}");
        Request request = new Request.Builder()
                .url(baseUrl)
                .post(requestBody)
                .addHeader("Accept", "application/json")
                .addHeader("Ocp-Apim-Subscription-Key", "8b144782eec54b159917c8c4a367d694")
                .build();
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
//        try {
//            Response response = getResponse(request);
//            JSONObject body = new JSONObject(response.body().toString());
//            if (response.code()<=299 && response.code()>=200) {
//                if (body.getString("status").equalsIgnoreCase("completed")) {
//                    if (body.getJSONObject("validation").getBoolean("tin_exists_online")) {
//                        return ValidationStatus.VALID;
//                    } else {
//                        return ValidationStatus.NOT_VALID;
//                    }
//                } else {
//                    return ValidationStatus.REVALIDATE;
//                }
//            } else if(response.code()==404) {
//                List<ErrorReport> errors = new ArrayList();
//                errors.add(new ErrorReport(response.code(), body.getString("errors"), response.message()));
//                throw new NotFoundException(body.getString("errors"), errors);
//            } else if(response.code()==401) {
//                List<ErrorReport> errors = new ArrayList();
//                errors.add(new ErrorReport(response.code(), "Unauthorized", response.message()));
//                throw new UnauthorizedException(body.getString("errors"), errors);
//            } else {
//                throw new Exception("Something went wrong while processing validation.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
