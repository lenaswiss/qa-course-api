package org.example.models;

public class Results {
    public String id;
    public String resultType;
    public String image;
    public String description;
    public String title;
    public String genres;

    @Override
    public String toString() {
        return "Results{" +
                "id='" + id + '\'' +
                ", resultType='" + resultType + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", genres='" + genres + '\'' +
                '}';
    }
}
