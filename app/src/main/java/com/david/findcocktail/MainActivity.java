package com.david.findcocktail;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer time = new Timer();
        time.schedule(task, 2000);


        /*protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            MyOpenHelper dbHelper = new MyOpenHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                // Insert con execSQL
                db.execSQL("INSERT INTO comments (user, comment) VALUES ('Digital Learning','Esto es un comentario insertado usando el método execSQL()')");

                // Insert con ContentValues
                ContentValues cv = new ContentValues();
                cv.put("user", "Detail");
                cv.put("comment", "Esto es un comentario insertado usando el método insert()");
                db.insert("comments", null, cv);

                // Update con execSQL
                db.execSQL("UPDATE comments SET comment='Esto es un comentario actualizado por el método execSQL()' WHERE user='Digital Learning'");

                // Update con ContentValues
                cv = new ContentValues();
                cv.put("comment", "Esto es un comentario actualizado por el método update()");
                String[] args = new String []{ "Academia Android"};
                db.update("comments", cv, "user=?", args);


            }

        }


         */
    }


}