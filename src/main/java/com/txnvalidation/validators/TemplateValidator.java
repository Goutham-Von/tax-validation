package com.txnvalidation.validators;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.txnvalidation.HttpUtil;
import com.txnvalidation.ValidationResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public abstract class TemplateValidator {
    protected String baseUrl;
    protected String accessKey;

    public enum Method {
        GET, POST;
    }

    TemplateValidator(String baseUrl, String key) {
        this.baseUrl = baseUrl;
        this.accessKey = key;
    }

    TemplateValidator() {
    }

    TemplateValidator(String baseUrl) {
        this(baseUrl, null);
    }

    /**
     * To construct request by
     * <li>adding parameters to baseUrl</li>
     * <li>adding headers to request</li>
     * <li>adding body to request</li>
     *
     * @param url        the endpoint / baseurl of api
     * @param method     to specify whether the request is GET or POST {@link Method}
     * @param parameters
     * @param headers
     * @param body
     * @return the fully loaded request with params, headers, body.
     */
    public Request requestBuilder
    (String url, TemplateValidator.Method method,
     Map<String, String> parameters,
     Map<String, String> headers,
     Map<String, String> body) {
        boolean start = true;
        if (parameters != null) {
            for (Map.Entry<String, String> parameter : parameters.entrySet()) {
                if (start) {
                    url = url.concat("?");
                    start = false;
                } else {
                    url = url.concat("&");
                }
                url = url.concat(parameter.getKey() + "=" + parameter.getValue());
            }
        }

        RequestBody requestBody = null;
        if (method != Method.GET) {
            requestBody = RequestBody.create(MediaType.parse("application/json"), JSONObject.valueToString(body));
        }
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .method(method.name(), requestBody);

        for (Map.Entry<String, String> header : headers.entrySet()) {
            requestBuilder.addHeader(header.getKey(), header.getValue());
        }
        return requestBuilder.build();
    }

    /**
     * This abstract method is needs to be overridden in child class to provide
     * validation pattern for that particular validator (for suppose different
     * validators have different headers, params and methods to make request and
     * capture response).
     *
     * @param txn_number
     * @param countryCode
     * @return
     */
    abstract public ValidationResponse isValid(String txn_number, String countryCode);


    /**
     * To make a request and to capture the response
     *
     * @param request
     * @return {@code Response}
     * @throws IOException
     */
    public Response getResponse(Request request) throws IOException {
        Response response = HttpUtil.request.newCall(request).execute();
        return response;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
