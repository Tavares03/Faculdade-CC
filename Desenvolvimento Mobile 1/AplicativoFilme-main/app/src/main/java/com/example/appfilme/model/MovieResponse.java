package com.example.appfilme.model;

import java.util.List;

public class MovieResponse {

    private List<Filme> results;

    public List<Filme> getResults() {
        return results;
    }

    public void setResults(List<Filme> results) {
        this.results = results;
    }
}