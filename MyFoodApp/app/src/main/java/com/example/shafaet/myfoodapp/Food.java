package com.example.shafaet.myfoodapp;

public class Food {
    private String name;
    private String description;

    public Food(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static final Food[] foods = {
            new Food("Pizza","Italian pizza with tomato catchup"),
            new Food("Pasta", "Mexican pasta with chease"),
            new Food("Burger", "American burger with beef")
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                '}';
    }
}
