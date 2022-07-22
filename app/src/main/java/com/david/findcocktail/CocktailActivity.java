package com.david.findcocktail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import com.david.findcocktail.db.DbCocktails;
import com.david.findcocktail.models.Cocktail;
import com.david.findcocktail.models.Ingredient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CocktailActivity extends AppCompatActivity {

    private Cocktail infoCocktail;
    private int idCocktail;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    TextView nombre,info,autor,preparacion,ingredientes;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail);

        nombre = (TextView) findViewById(R.id.name_c);
        info = (TextView) findViewById(R.id.info_c);
        autor = (TextView) findViewById(R.id.author_c);
        preparacion = (TextView) findViewById(R.id.preparation_c);
        ingredientes = (TextView) findViewById(R.id.ingre_c);
        imagen = (ImageView) findViewById(R.id.image_c);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        this.setCocktailId();
        this.getInfoById(idCocktail);
        this.loadInfoCocktail();
    }

    private void setCocktailId(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            this.idCocktail = extras.getInt("idCocktail");
        }
    }

    public void loadInfoCocktail(){
        if (this.infoCocktail != null) {
            nombre.setText(this.infoCocktail.getName());
            info.setText(this.infoCocktail.getInfo());
            autor.setText("@" + this.infoCocktail.getAuthor());
            preparacion.setText(this.infoCocktail.getPreparation());
            //String nameImage = this.infoCocktail.getImage().replace(".png", "");
            //String nameImage = this.infoCocktail.getImage();
            int idImage = getResources().getIdentifier(this.infoCocktail.getImage(), "drawable", getPackageName());
            imagen.setImageResource(idImage);
            String ingredientsText = transformIngredientes(this.infoCocktail.getIngredients());
            ingredientes.setText(ingredientsText);
        }
    }

    private String transformIngredientes(List<Ingredient> ingredientList){
        StringBuilder str = new StringBuilder();
        for(Ingredient ing : ingredientList){
            String text = ing.getAmount() + " " + ing.getName() + "\n";
            str.append(text);
        }
        return str.toString();
    }

    public void getInfoById (int id){
      DbCocktails db = new DbCocktails(CocktailActivity.this);
      this.infoCocktail = db.getById(id);
    }


}
