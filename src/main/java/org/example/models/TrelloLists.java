package org.example.models;

public class TrelloLists {

    public String name;
    public String id;
    public String idBoard;

    @Override
    public String toString() {
        return "TrelloLists{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", idBoard='" + idBoard + '\'' +
                '}';
    }
}
