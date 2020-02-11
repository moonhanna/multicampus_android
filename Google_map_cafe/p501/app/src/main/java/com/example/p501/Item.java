package com.example.p501;

public class Item {

    String image;
    String name;
    int star;
    double leti;
    double longti;
    String description;

    public Item() {
    }

    public Item(String image, String name, int star, double leti, double longti, String description) {
        this.image = image;
        this.name = name;
        this.star = star;
        this.leti = leti;
        this.longti = longti;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public double getLeti() {
        return leti;
    }

    public void setLeti(double leti) {
        this.leti = leti;
    }

    public double getLogti() {
        return longti;
    }

    public void setLogti(double logti) {
        this.longti = logti;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
