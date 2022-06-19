package com.david.findcocktail.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.david.findcocktail.util.Files;

public class DbHelper extends SQLiteOpenHelper {
    private static final String NAME_DATABASE = "find_cocktail";
    private static final int VERSION_DATABASE = 10;

    private  Context context;

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String[] scriptsSQL = Files.getSQLScripts(context);
        for(String script : scriptsSQL){
            sqLiteDatabase.execSQL(script);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
