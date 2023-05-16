package com.example.androidprojectversion;

public class Market {
    private String title;
    private String image;
    private String price;

    public String getTitle() { return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Market(String title, String image, String price, String id) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.id = id;
    }
}
