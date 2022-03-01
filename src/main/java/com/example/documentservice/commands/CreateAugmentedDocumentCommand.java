package com.example.documentservice.commands;

import com.example.documentservice.AugmentationInfo;
import com.example.documentservice.DataField;
import com.example.documentservice.Sentence;
import com.example.documentservice.Task;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CreateAugmentedDocumentCommand {
    //Does not need the Aggregate Identifier because it is a create command
    private final String title;
    private final String domain;
    private final String source;
    private final String contributor;
    private final String citationInformation;
    private final List<DataField> dataFields;
    private final List<Task> tasks;
    private final List<Sentence> sentences;
    private final boolean augmented;
    private final List<AugmentationInfo> augmentationInfos;
    private final UUID rootDocument;


    public CreateAugmentedDocumentCommand(String title, String domain, String source, String contributor, String citationInformation,
                                          List<DataField> dataFields, List<Task> tasks, List<Sentence> sentences, boolean augmented, List<AugmentationInfo> augmentationInfos, UUID rootDocument) {
        this.title = title;
        this.domain = domain;
        this.source = source;
        this.contributor = contributor;
        this.citationInformation = citationInformation;
        this.dataFields = dataFields;
        this.tasks = tasks;
        this.sentences = sentences;
        this.augmented = augmented;
        this.augmentationInfos = augmentationInfos;
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

    public List<Sentence> getSentences() {
        return sentences;
    }

    public boolean getAugmented() {
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
        CreateAugmentedDocumentCommand that = (CreateAugmentedDocumentCommand) o;
        return augmented == that.augmented && title.equals(that.title) && domain.equals(that.domain) && source.equals(that.source) && contributor.equals(that.contributor) && citationInformation.equals(that.citationInformation) && dataFields.equals(that.dataFields) && tasks.equals(that.tasks) && sentences.equals(that.sentences) && augmentationInfos.equals(that.augmentationInfos) && rootDocument.equals(that.rootDocument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, domain, source, contributor, citationInformation, dataFields, tasks, sentences, augmented, augmentationInfos, rootDocument);
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
                ", sentences=" + sentences +
                ", augmented=" + augmented +
                ", augmentationInfos=" + augmentationInfos +
                ", rootDocument=" + rootDocument +
                '}';
    }
}
