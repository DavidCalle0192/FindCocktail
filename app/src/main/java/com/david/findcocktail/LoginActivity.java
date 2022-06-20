package com.david.findcocktail;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity{

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;

    TextInputEditText txt_email;
    TextInputEditText txt_password;
    Button btn_login;
    ImageButton btn_login_google;

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //AlertDialog mDialog;
    // [START declare_auth]
    //private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorBar));
        }

        //campos
        txt_email = findViewById(R.id.txtEmail);
        txt_password = findViewById(R.id.txtPassword);
        btn_login = findViewById(R.id.btn_Login);
        btn_login_google = findViewById(R.id.imgBtn_google);

        //bd firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Progres dialog
        // [START initialize_auth]
        // Initialize Firebase Auth
        //mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

       /* btn_login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingreso();
            }
        });

              //Gmail
        /*signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END config_signin]*/
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


    // [START on_start_check_user]
   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Ingresado(currentUser);
    }
    // [END on_start_check_user]

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Ingresado(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Ingresado(null);
                        }
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    private void ingreso() {
       Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]

    private void Ingresado(FirebaseUser user) {

        // [START check_current_user]
        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
        if (users != null) {
            // User is signed in
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            Toast.makeText(LoginActivity.this, "Ingreso", Toast.LENGTH_SHORT).show();
        } else {

            // No user is signed in
        }
        // [END check_current_user]
    }*/
}