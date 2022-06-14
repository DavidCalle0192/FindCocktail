package com.david.findcocktail.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.david.findcocktail.util.Files;
import com.david.findcocktail.util.Util;

import java.io.IOException;
import java.io.InputStream;

public class DbHelper extends SQLiteOpenHelper {
    private static final String NAME_DATABASE = "find_cocktail";
    private static final int VERSION_DATABASE = 1;

    private  Context context;

    public DbHelper(@Nullable Context context) {
        super(context, NAME_DATABASE, null, VERSION_DATABASE);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String[] scriptSQL = {};
        try {
            InputStream is = context.getResources().getAssets().open("script.sql");
            scriptSQL = Files.convertStreamToString(is).split(";");
            for(String script : scriptSQL) {
                String sentence = script.replaceAll("\t", "");
                if(!script.trim().isEmpty())
                    sqLiteDatabase.execSQL(sentence);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Util.TABLES_NAMES);
        onCreate(sqLiteDatabase);
    }
}
