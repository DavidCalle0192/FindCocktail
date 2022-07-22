package com.david.findcocktail.models;

public class Item {

    private int idImage;
    private int idCocktail;
    private String name;
    private String description;


    public Item() {
    }

    public Item(int idImage,int idCocktail, String name, String description) {
        this.idCocktail = idCocktail;
        this.idImage = idImage;
        this.name = name;
        this.description = description;
    }

    public int getIdCocktail() {
        return idCocktail;
    }

    public void setIdCocktail(int idCocktail) {
        this.idCocktail = idCocktail;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
