package com.example.customerservice;

import java.util.List;

public class Sentence {
    private int id;
    private List<Token> tokens;

    public Sentence(int id, List<Token> tokens) {
        this.id = id;
        this.tokens = tokens;
    }

    public int getId() {
        return id;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "Sentence{"
                + "id='" + id + "'" +
                ", tokens=" + tokens +
                "}";
    }
}
