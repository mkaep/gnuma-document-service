package com.example.customerservice.queries;

public class RetrieveSourceDomainDocumentsQuery {
    private String domain;
    private String source;

    public RetrieveSourceDomainDocumentsQuery(String source, String domain) {
        this.domain = domain;
        this.source = source;
    }

    public String getDomain() {
        return domain;
    }

    public String getSource() {
        return source;
    }
}
