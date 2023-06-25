package org.example.models;

public class TrelloCards {

    public String id;
    public String idBoard;
    public String idList;

    @Override
    public String toString() {
        return "TrelloCards{" +
                "id='" + id + '\'' +
                ", idBoard='" + idBoard + '\'' +
                ", idList='" + idList + '\'' +
                '}';
    }
}
