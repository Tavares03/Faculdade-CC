package com.example.appfilme.model;

import java.util.List;

public class MovieResponse {
    private List<Filme> results;
    private int total_pages; // Adicione esta linha

    public List<Filme> getResults() {
        return results;
    }

    public void setResults(List<Filme> results) {
        this.results = results;
    }


    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}