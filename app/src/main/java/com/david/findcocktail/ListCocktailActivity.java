package com.david.findcocktail;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.david.findcocktail.adapters.MyAdapterRecyclerView;
import com.david.findcocktail.callbacks.MyItemTouchHelperCallback;
import com.david.findcocktail.db.DbCocktails;
import com.david.findcocktail.interfaces.CallBackItemTouch;
import com.david.findcocktail.models.Cocktail;
import com.david.findcocktail.models.Item;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListCocktailActivity extends AppCompatActivity implements CallBackItemTouch {

    RecyclerView recyclerView;
    MyAdapterRecyclerView myAdapterRecyclerView;
    ArrayList<Item> list;
    RelativeLayout layout;
    MyAdapterRecyclerView.RecyclerViewClickListener listener;
    DbCocktails dbCocktails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cocktail);
        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setAdapter(myAdapterRecyclerView);
        layout = findViewById(R.id.RLM);
        list = new ArrayList<>();
        dbCocktails = new DbCocktails(ListCocktailActivity.this);
        initList();
        viewData();
    }

    private void initList() {
        List<Cocktail> cocktailList = dbCocktails.getAll();
        for(Cocktail cocktail : cocktailList) {
            int idImage = getResources().getIdentifier(cocktail.getImage(), "drawable", getPackageName());
            list.add(new Item(idImage, cocktail.getId(), cocktail.getName(), "Pulsa para ver cóctel"));
        }
    }

    public void viewData(){
        setOnClickListener();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapterRecyclerView = new MyAdapterRecyclerView(list, listener);
        recyclerView.setAdapter(myAdapterRecyclerView);
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void setOnClickListener() {
        listener = new MyAdapterRecyclerView.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                goToCocktail(list.get(position).getIdCocktail());
            }
        };
    }

    @Override
    public void itemTouchOnMode(int oldPosition, int newPosition) {
        list.add(newPosition,list.remove(oldPosition));
        myAdapterRecyclerView.notifyItemMoved(oldPosition,newPosition);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int position) {

        //we will deleted and also we want to undo
        String name = list.get(viewHolder.getAdapterPosition()).getName();
        //backup of removed item for undo
        final Item deletedItem = list.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();

        //remove the item from recyclerview
        myAdapterRecyclerView.removeItem(viewHolder.getAdapterPosition());

        //showing snaclbar for undo
        Snackbar snackbar = Snackbar.make(layout,name+" Eliminado",Snackbar.LENGTH_LONG);
        snackbar.setAction("DESHACER", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapterRecyclerView.restoreItem(deletedItem,deletedIndex);
            }
        });
        snackbar.setActionTextColor(Color.GREEN);
        snackbar.show();
    }

    private void goToCocktail(int id) {
        Intent intent = new Intent(ListCocktailActivity.this, CocktailActivity.class);
        intent.putExtra("idCocktail", id);
        startActivity(intent);
    }

    /*
    @Override
    public void itemTuchOnMove(int oldPosition, int newPosition) {
        list.add(newPosition,list.remove(oldPosition));
        myAdapterRecyclerView.notifyItemMoved(oldPosition,newPosition);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int position) {
        //we will deleted and also we want to undo
        String name = list.get(viewHolder.getAdapterPosition()).getName();
        //backup of removed item for undo
        final Item deletedItem = list.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();

        //remove the item from recyclerview
        myAdapterRecyclerView.removeItem(viewHolder.getAdapterPosition());

        //showing snaclbar for undo
        Snackbar snackbar = Snackbar.make(layout,name+"Remove..!",Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapterRecyclerView.restoreItem(deletedItem,deletedIndex);
            }
        });
        snackbar.setActionTextColor(Color.GREEN);
        snackbar.show();
    }*/
}