package com.gormal.gormalonetask.productList.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Books implements Serializable {
ArrayList<Results>  results;

    public Books(ArrayList<Results> results) {
        this.results = results;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Books{" +
                "results=" + results +
                '}';
    }
}
