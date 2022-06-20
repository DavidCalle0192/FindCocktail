package com.david.findcocktail.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Files {
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(" ");
            }
            reader.close();
        }catch (Exception ex){
            System.err.println(ex);
        }
        return sb.toString();
    }

    public static  String[] getSQLScripts(Context context){
        List<String> sentences = new ArrayList<>();
        try {
            InputStream is = context.getResources().getAssets().open("script.sql");
            String[] scriptSQL = Files.convertStreamToString(is).split(";");
            for(String script : scriptSQL) {
                if(!script.trim().isEmpty()){
                    sentences.add(script.replaceAll("\t", "").trim());
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR READING SCRIPT SQL: " + e);
        }
        return sentences.toArray(new String[]{});
    }
}
