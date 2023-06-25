package org.example.models;

public class TrelloBoard {

    public String id;
    public String name;
    public String url;

    @Override
    public String toString() {
        return "TrelloBoard{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
