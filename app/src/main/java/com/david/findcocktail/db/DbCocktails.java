package com.david.findcocktail.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.david.findcocktail.models.Cocktail;
import com.david.findcocktail.models.Ingredient;
import com.david.findcocktail.util.Mapper;
import com.david.findcocktail.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DbCocktails  extends DbHelper{

    private Context context;

    public DbCocktails(@Nullable Context context ){
        super(context);
        this.context = context;
    }

    public List<Cocktail> getAll() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Cocktail> cocktailList = new ArrayList<>();
        Cursor cursorCocktail = db.rawQuery(Util.QUERY_COCKTAIL, null);
        if(cursorCocktail.moveToFirst()){
            do{
                Cocktail cocktail = Mapper.mapCursorToCocktail(cursorCocktail);
                cocktail.setIngredients(getAllIngredients(db, cocktail.getId()));
                cocktailList.add(cocktail);
            } while (cursorCocktail.moveToNext());
        }
        cursorCocktail.close();
        return  cocktailList;
    }

    public Cocktail getById(int id){
        List<Cocktail> cocktailList = getAll();
        for (Cocktail cocktail : cocktailList)
            if(cocktail.getId() == id)
                return cocktail;
        return null;
    }

    public List<Cocktail> getByIngredients(String ingredients){
        List<Cocktail> cocktailList = getAll();
        if(ingredients.trim().isEmpty()) {
            return cocktailList;
        }
        String[] ingredientArray = ingredients.trim().split(",");
        List<Cocktail> result = new ArrayList<>();
        for(Cocktail cocktail : cocktailList)
            for(Ingredient ingredient: cocktail.getIngredients())
                for (String search : ingredientArray)
                    if(ingredient.getName().contains(search.trim().toLowerCase()))
                        result.add(cocktail);
        return result;
    }

    private List<Ingredient> getAllIngredients(SQLiteDatabase db, int id_cocktail){
        List<Ingredient> ingredientList = new ArrayList<>();
        Cursor cursorIngredient = db.rawQuery(Util.QUERY_INGREDIENTS + id_cocktail, null);
        if(cursorIngredient.moveToFirst()) {
            do {
                ingredientList.add(Mapper.mapCursorToIngredient(cursorIngredient));
            } while (cursorIngredient.moveToNext());
        }
        cursorIngredient.close();
        return ingredientList;
    }
}
