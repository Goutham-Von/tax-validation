package com.txnvalidation.validators;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.txnvalidation.HttpClient;
import com.txnvalidation.ValidationResponse;
import com.txnvalidation.ValidationStatus;
import javafx.util.Pair;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public Request requestBuilder
            (String url, Map<String, String> parameters, Map<String, String> headers, Map<String, String> body) {
        boolean start = true;
        for(Map.Entry<String, String> parameter : parameters.entrySet()) {
            if(start) {
                url = url.concat("?");
                start = false;
            } else {
                url = url.concat("&");
            }
            url = url.concat(parameter.getKey()+"="+parameter.getValue());
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSONObject.valueToString(body));
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(requestBody);
        for(Map.Entry<String, String> header : headers.entrySet()) {
            requestBuilder.addHeader(header.getKey(), header.getValue());
        }
        return requestBuilder.build();
    }

    abstract public ValidationResponse isValid(String txn_number, String countryCode);

    public Response getResponse(Request request) throws IOException {
        Response response = HttpClient.request.newCall(request).execute();
        return response;
    }
}
