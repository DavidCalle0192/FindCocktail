package com.david.findcocktail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


//import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText txt_email;
    TextInputEditText txt_password;
    Button btn_login;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //campos
        txt_email = findViewById(R.id.txtEmail);
        txt_password = findViewById(R.id.txtPassword);
        btn_login = findViewById(R.id.btn_Login);

        //bd firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Progres dialog


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();

        //Validaciones:
        // 1. que el campo email no se encuentre vacio
        // 2. password mayor a 6

        if(!email.isEmpty() && !password.isEmpty()){
            if(password.length() >= 6){
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Bienvenido!!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else{
                Toast.makeText(this, "La contraseña debe contener mínimo 6 caracteres.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "El usuario y contraseña son obligatorios",Toast.LENGTH_SHORT).show();
        }
    }
}