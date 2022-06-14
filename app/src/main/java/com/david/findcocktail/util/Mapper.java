package com.david.findcocktail.util;

import android.database.Cursor;

import com.david.findcocktail.models.Cocktail;
import com.david.findcocktail.models.Ingredient;

public class Mapper {
    public static Cocktail mapCursorToCocktail(Cursor cursor){
        Cocktail cocktail = new Cocktail();
        cocktail.setId(cursor.getInt(0));
        cocktail.setName(cursor.getString(1));
        cocktail.setAuthor(cursor.getString(2));
        cocktail.setImage(cursor.getString(3));
        cocktail.setInfo(cursor.getString(4));
        cocktail.setPreparation(cursor.getString(5));
        return cocktail;
    }

    public static Ingredient mapCursorToIngredient(Cursor cursor){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(cursor.getString(0));
        ingredient.setAmount(cursor.getString(1));
        return  ingredient;
    }
}
