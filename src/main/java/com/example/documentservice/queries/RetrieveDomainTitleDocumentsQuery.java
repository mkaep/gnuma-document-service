package com.example.documentservice.queries;

public class RetrieveDomainTitleDocumentsQuery {
    private String domain;
    private String title;

    public RetrieveDomainTitleDocumentsQuery(String domain, String title) {
        this.domain = domain;
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public String getTitle() {
        return title;
    }
}
