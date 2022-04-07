package com.txnvalidation.validators;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.txnvalidation.HttpClient;
import com.txnvalidation.ValidationResponse;
import com.txnvalidation.ValidationStatus;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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

    public Request requestBuilder(String url, HashMap<String, String> parameters, HashMap<String, String> headers) {
//        boolean start = true;
//        for( : parameters) {
//            if(start) {
//                url = url.concat("?")
//                start = false
//            }
//        }
//        Request request = new Request.Builder();
//        return
        return null;
    }

    abstract public ValidationResponse isValid(String txn_number, String countryCode);

    public Response getResponse(Request request) throws IOException {
        Response response = HttpClient.request.newCall(request).execute();
        return response;
    }
}
