package com.example.documentservice.commands;

import com.example.documentservice.AugmentationInfo;
import com.example.documentservice.DataField;
import com.example.documentservice.Task;

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


    public CreateDocumentCommand(String title, String domain, String source, String contributor, String citationInformation,
                                 List<DataField> dataFields, List<Task> tasks, File data) {
        this.title = title;
        this.domain = domain;
        this.source = source;
        this.contributor = contributor;
        this.citationInformation = citationInformation;
        this.dataFields = dataFields;
        this.tasks = tasks;
        this.data = data;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateDocumentCommand that = (CreateDocumentCommand) o;
        return title.equals(that.title) && domain.equals(that.domain) && source.equals(that.source) && contributor.equals(that.contributor) && citationInformation.equals(that.citationInformation) && dataFields.equals(that.dataFields) && tasks.equals(that.tasks) && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, domain, source, contributor, citationInformation, dataFields, tasks, data);
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
                '}';
    }
}
