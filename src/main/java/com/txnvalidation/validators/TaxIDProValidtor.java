package com.txnvalidation.validators;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.txnvalidation.ValidationStatus;
import com.txnvalidation.exceptions.ErrorReport;
import com.txnvalidation.exceptions.NotFoundException;
import com.txnvalidation.exceptions.UnauthorizedException;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class TaxIDProValidtor extends TemplateValidator {
    public TaxIDProValidtor(String apiurlTaxidpro, String accesskeyTaxidpro) {
        super(apiurlTaxidpro, accesskeyTaxidpro);
    }

    @Override
    public ValidationStatus isValid(String txn_number, String countryCode) {
        String url = baseUrl;
        url = url.concat("?key="+accessKey);
        url = url.concat("&tin="+txn_number);
        url = url.concat("&country="+countryCode);
        url = url.concat("&type=vat");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "appplication/json")
                .build();
        try {
            Response response = getResponse(request);
            JSONObject body = new JSONObject(response.body().string());
            if (response.code()<=299 && response.code()>=200) {
                if(body.getBoolean("valid")==Boolean.TRUE) {
                    return ValidationStatus.VALID;
                } else if (body.getBoolean("valid")==Boolean.FALSE) {
                    return ValidationStatus.NOT_VALID;
                } else {
                    return ValidationStatus.REVALIDATE;
                }
            } else {
                throw new Exception("Something went wrong while processing validation.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ValidationStatus.REVALIDATE;
    }
}
