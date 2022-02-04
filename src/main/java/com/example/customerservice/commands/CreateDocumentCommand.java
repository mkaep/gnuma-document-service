package com.example.customerservice.commands;

import com.example.customerservice.DataField;
import com.example.customerservice.Sentence;
import com.example.customerservice.Task;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CreateDocumentCommand {
    //Does not need the Aggregate Identifier because it is a create command
    private final String title;
    private final String domain;
    private final String source;
    private final String contributor;
    private final String citationInformation;
    private final List<DataField> dataFields;
    private final List<Task> tasks;
    private final File data;
    private final boolean augmented;
    private final UUID rootDocument;

    public CreateDocumentCommand(String title, String domain, String source, String contributor, String citationInformation,
                                 List<DataField> dataFields, List<Task> tasks, File data,
                                 boolean augmented, UUID rootDocument) {
        this.title = title;
        this.domain = domain;
        this.source = source;
        this.contributor = contributor;
        this.citationInformation = citationInformation;
        this.dataFields = dataFields;
        this.tasks = tasks;
        this.data = data;
        this.augmented = augmented;
        this.rootDocument = rootDocument;
    }

    public String getTitle() {
        return title;
    }

    public String getDomain() {
        return domain;
    }

    public String getSource() {
        return source;
    }

    public String getContributor() {
        return contributor;
    }

    public String getCitationInformation() {
        return citationInformation;
    }

    public List<DataField> getDataFields() {
        return dataFields;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public File getData() {
        return data;
    }

    public boolean isAugmented() {
        return augmented;
    }

    public UUID getRootDocument() {
        return rootDocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateDocumentCommand that = (CreateDocumentCommand) o;
        return augmented == that.augmented && Objects.equals(title, that.title) && Objects.equals(domain, that.domain) && Objects.equals(source, that.source) && Objects.equals(contributor, that.contributor) && Objects.equals(citationInformation, that.citationInformation) && Objects.equals(dataFields, that.dataFields) && Objects.equals(tasks, that.tasks) && Objects.equals(data, that.data) && Objects.equals(rootDocument, that.rootDocument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, domain, source, contributor, citationInformation, dataFields, tasks, data, augmented, rootDocument);
    }

    @Override
    public String toString() {
        return "CreateDocumentCommand{" +
                "title='" + title + '\'' +
                ", domain='" + domain + '\'' +
                ", source='" + source + '\'' +
                ", contributor='" + contributor + '\'' +
                ", citationInformation='" + citationInformation + '\'' +
                ", dataFields=" + dataFields +
                ", tasks=" + tasks +
                ", data=" + data +
                ", augmented=" + augmented +
                ", rootDocument=" + rootDocument +
                '}';
    }
}