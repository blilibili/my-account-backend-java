package com.account.account.model;

public class HttpResult {

    private int code;
    private String body;

    public HttpResult(int statusCode, String s) {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
