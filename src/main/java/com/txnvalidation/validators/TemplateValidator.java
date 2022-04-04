package com.txnvalidation.validators;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.txnvalidation.HttpClient;
import com.txnvalidation.ValidationStatus;

import java.io.IOException;

public abstract class TemplateValidator {
    protected String baseUrl;
    protected String accessKey;

    TemplateValidator(String baseUrl, String key) {
        this.baseUrl = baseUrl;
        this.accessKey = key;
    }

    TemplateValidator(String baseUrl) {
        this(baseUrl, null);
    }

    abstract public ValidationStatus isValid(String txn_number, String countryCode);

    public Response getResponse(Request request) throws IOException {
        Response response = HttpClient.request.newCall(request).execute();
        return response;
    }
}
