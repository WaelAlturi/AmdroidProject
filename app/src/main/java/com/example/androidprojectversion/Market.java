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

    public String getInfo() {
        return info;
    }

    public void setInfo(String id) {
        this.info = id;
    }

    private String info;

    public Market(String title, String image, String price, String info) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.info = info;
    }
}
