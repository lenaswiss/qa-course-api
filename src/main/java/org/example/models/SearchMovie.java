package org.example.models;


import java.util.List;

public class SearchMovie {
    public String expression;
    public List<Results> results;
    public String errorMessage;

    @Override
    public String toString() {
        return "Search{" +
                "expression='" + expression + '\'' +
                ", results=" + results +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
