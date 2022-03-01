package com.example.documentservice;

import java.util.HashMap;
import java.util.Map;

public class AugmentationInfo {
    private String name;
    private Map<String, Object> configuration;

    public AugmentationInfo() {

    }

    public AugmentationInfo(String name, Map<String, Object> configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getConfiguration() {
        return configuration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConfiguration(Map<String, Object> configuration) {
        this.configuration = configuration;
    }

    public void addConfigurationElement(String name, Object value) throws Exception {
        if(!configuration.containsKey(name)) {
            configuration.put(name, value);
        }
        else {
            throw new Exception("Configuration Element already exists!");
        }
    }

    @Override
    public String toString() {
        return "AugmentationInfo{"
                + "name='" + name + "'" +
                ", configuration=" + configuration + "}";
    }
}
