package com.example.shafaet.fragmenttest;

public class Menu {
    private String name;
    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        return this.name;
    }

    public static final Menu[] menus = {
            new Menu("BreakFast"," 2 Eggs\n Bread\n Coffee"),
            new Menu("Lunch"," 3 Eggs\n Beef\n Coke"),
            new Menu("Dinner"," 1 Egg\n Brocoli\n Sprit")
    };
}
