package com.example.documentservice;

import java.util.Map;

public class Token {
    private String token;
    private String nerTag;
    private Map<String, Object> metaTags;

    public Token(String token, String nerTag, Map<String, Object> metaTags) {
        this.token = token;
        this.nerTag = nerTag;
        this.metaTags = metaTags;
    }

    public String getToken() {
        return token;
    }

    public String getNerTag() {
        return nerTag;
    }

    public Map<String, Object> getMetaTags() {
        return metaTags;
    }

    public void addMetaTag(DataField dataField, Object value) {
        metaTags.put(dataField.getFieldName(), value);
    }

    @Override
    public String toString() {
        return "Token{"
                + "token='" + token + "'" +
                ", nerTag='" + nerTag + "'" +
                ", metaTags=" + metaTags +
                "}";
    }
}
