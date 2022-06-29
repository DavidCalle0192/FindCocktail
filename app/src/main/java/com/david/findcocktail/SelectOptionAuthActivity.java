package com.david.findcocktail;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class SelectOptionAuthActivity extends AppCompatActivity {

    Button btn_login;
    Button btn_register;
    Button btn_list_coctels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorBar));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);

        btn_login = findViewById(R.id.btnLogin);
        btn_register = findViewById(R.id.btnRegister);
        btn_list_coctels = findViewById(R.id.btn_listCoc);

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                goToLogin();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

        btn_list_coctels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToList();
            }
        });
    }

    private void goToRegister() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void goToLogin() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToList() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, ListCocktailActivity.class);
        startActivity(intent);
    }

    /*private void goToCocktail() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, CocktailActivity.class);
        intent.putExtra("idCocktail", 2);
        startActivity(intent);
    }*/

    private void goToCocktail() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, CocktailActivity.class);
        intent.putExtra("idCocktail", 10);
        startActivity(intent);
    }
}
