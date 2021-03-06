package com.example.documentservice.commands;

import com.example.documentservice.AugmentationInfo;
import com.example.documentservice.DataField;
import com.example.documentservice.Task;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UpdateDocumentCommand {
    @TargetAggregateIdentifier
    private final UUID id;
    private final String title;
    private final String domain;
    private final String source;
    private final String contributor;
    private final String citationInformation;
    private final List<DataField> dataFields;
    private final List<Task> tasks;
    private final File data;
    private final boolean augmented;
    private final List<AugmentationInfo> augmentationInfos;
    private final UUID rootDocument;

    public UpdateDocumentCommand(UUID id, String title, String domain, String source, String contributor, String citationInformation,
                                 List<DataField> dataFields, List<Task> tasks, File data,
                                 boolean augmented, List<AugmentationInfo> augmentationInfos, UUID rootDocument) {
        this.id = id;
        this.title = title;
        this.domain = domain;
        this.source = source;
        this.contributor = contributor;
        this.citationInformation = citationInformation;
        this.dataFields = dataFields;
        this.tasks = tasks;
        this.data = data;
        this.augmented = augmented;
        this.augmentationInfos = augmentationInfos;
        this.rootDocument = rootDocument;
    }

    public UUID getId() {
        return id;
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

    public List<AugmentationInfo> getAugmentationInfos() {
        return augmentationInfos;
    }

    public UUID getRootDocument() {
        return rootDocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateDocumentCommand that = (UpdateDocumentCommand) o;
        return augmented == that.augmented && id.equals(that.id) && title.equals(that.title) && domain.equals(that.domain) && source.equals(that.source) && contributor.equals(that.contributor) && citationInformation.equals(that.citationInformation) && dataFields.equals(that.dataFields) && tasks.equals(that.tasks) && data.equals(that.data) && Objects.equals(augmentationInfos, that.augmentationInfos) && Objects.equals(rootDocument, that.rootDocument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, domain, source, contributor, citationInformation, dataFields, tasks, data, augmented, augmentationInfos, rootDocument);
    }

    @Override
    public String toString() {
        return "UpdateDocumentCommand{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", domain='" + domain + '\'' +
                ", source='" + source + '\'' +
                ", contributor='" + contributor + '\'' +
                ", citationInformation='" + citationInformation + '\'' +
                ", dataFields=" + dataFields +
                ", tasks=" + tasks +
                ", data=" + data +
                ", augmented=" + augmented +
                ", augmentationInfos =" + augmentationInfos +
                ", rootDocument=" + rootDocument +
                '}';
    }
}
