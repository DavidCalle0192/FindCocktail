package com.david.findcocktail.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
