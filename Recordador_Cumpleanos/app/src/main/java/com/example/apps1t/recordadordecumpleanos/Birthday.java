package com.example.apps1t.recordadordecumpleanos;

public class Birthday {
    String name;
    String image;
    long date;

    public Birthday(String name, String image, long date) {
        this.name = name;
        this.image = image;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public long getDate() {
        return date;
    }
}


