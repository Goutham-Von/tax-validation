package com.txnvalidation;

import com.squareup.okhttp.OkHttpClient;

public class HttpClient {

    /**
     * OkHttpClient has the capability of executing multiple threads at the same time.
     * so singleton instance of OkHttpClient is sufficient to make multiple different api calls.
     * https://stackoverflow.com/questions/48532860/is-it-thread-safe-to-make-calls-to-okhttpclient-in-parallel
     */
    public static OkHttpClient request = new OkHttpClient();
}
