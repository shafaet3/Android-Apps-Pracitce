package com.example.shafaet.flagname;

public class Country {
    private String name;
    private int imageId;

    public Country(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String toString() {
        return this.name;
    }

    public static final Country[] countries = {
            new Country("Bangladesh", R.drawable.bd),
            new Country("Argentina", R.drawable.arg),
            new Country("Brazil", R.drawable.bra),
            new Country("France", R.drawable.fra),
            new Country("Mexico", R.drawable.mex)
    };
}
