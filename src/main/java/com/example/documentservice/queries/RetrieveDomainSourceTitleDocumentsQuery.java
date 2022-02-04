package com.example.documentservice.queries;

public class RetrieveDomainSourceTitleDocumentsQuery {
    private String domain;
    private String source;
    private String title;

    public RetrieveDomainSourceTitleDocumentsQuery(String domain, String source, String title) {
        this.domain = domain;
        this.source = source;
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }
}
