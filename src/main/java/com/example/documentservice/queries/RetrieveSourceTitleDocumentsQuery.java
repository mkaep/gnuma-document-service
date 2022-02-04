package com.example.documentservice.queries;

public class RetrieveSourceTitleDocumentsQuery {
    private String source;
    private String title;

    public RetrieveSourceTitleDocumentsQuery(String source, String title) {
        this.source = source;
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }
}
