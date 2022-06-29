package com.david.findcocktail;

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
import com.david.findcocktail.interfaces.CallBackItemTouch;
import com.david.findcocktail.models.Item;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListCocktailActivity extends AppCompatActivity implements CallBackItemTouch {

    RecyclerView recyclerView;
    MyAdapterRecyclerView myAdapterRecyclerView;
    ArrayList<Item> list;
    RelativeLayout layout;

    /*private int image[] = new int[]{
            R.drawable.c01,
            R.drawable.c02,
            R.drawable.c03,
            R.drawable.c04,
            R.drawable.c05,
            R.drawable.c06,
            R.drawable.c07,
            R.drawable.c08,
            R.drawable.c09,
            R.drawable.c10,
    };

    private String names[] = new String[]{
            "test01",
            "test02",
            "test03",
            "test04",
            "test05",
            "test06",
            "test07",
            "test08",
            "test09",
            "test10",
    };

    String description = "holi";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cocktail);
        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setAdapter(myAdapterRecyclerView);
        layout = findViewById(R.id.RLM);
        list = new ArrayList<>();
        initList();
        viewData();
    }

    private void initList() {

        list.add(new Item(R.drawable.c01,"test","desctest"));
        list.add(new Item(R.drawable.c02,"test","desctest"));
        list.add(new Item(R.drawable.c03,"test","desctest"));
        list.add(new Item(R.drawable.c04,"test","desctest"));
        list.add(new Item(R.drawable.c05,"test","desctest"));
        list.add(new Item(R.drawable.c06,"test","desctest"));
        list.add(new Item(R.drawable.c07,"test","desctest"));
        list.add(new Item(R.drawable.c01,"test","desctest"));
        list.add(new Item(R.drawable.c02,"test","desctest"));
        list.add(new Item(R.drawable.c03,"test","desctest"));
        list.add(new Item(R.drawable.c04,"test","desctest"));
        list.add(new Item(R.drawable.c05,"test","desctest"));
        list.add(new Item(R.drawable.c06,"test","desctest"));
        list.add(new Item(R.drawable.c07,"test","desctest"));
        list.add(new Item(R.drawable.c01,"test","desctest"));
        list.add(new Item(R.drawable.c02,"test","desctest"));
        list.add(new Item(R.drawable.c03,"test","desctest"));
        list.add(new Item(R.drawable.c04,"test","desctest"));
        list.add(new Item(R.drawable.c05,"test","desctest"));
        list.add(new Item(R.drawable.c06,"test","desctest"));
        list.add(new Item(R.drawable.c07,"test","desctest"));
        list.add(new Item(R.drawable.c01,"test","desctest"));
        list.add(new Item(R.drawable.c02,"test","desctest"));
        list.add(new Item(R.drawable.c03,"test","desctest"));
        list.add(new Item(R.drawable.c04,"test","desctest"));
        list.add(new Item(R.drawable.c05,"test","desctest"));
        list.add(new Item(R.drawable.c06,"test","desctest"));
        list.add(new Item(R.drawable.c07,"test","desctest"));
        /*for (int i=0; i < names.length; i++){
            list.add(new Item(image[i],names[i],description));
        }

        //stup recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapterRecyclerView = new MyAdapterRecyclerView(list);
        recyclerView.setAdapter(myAdapterRecyclerView);
        ItemTouchHelper.Callback callback = new MyItemTuchHelperCallBack(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);*/
    }

    public void viewData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapterRecyclerView = new MyAdapterRecyclerView(list);
        recyclerView.setAdapter(myAdapterRecyclerView);
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
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
        Snackbar snackbar = Snackbar.make(layout,name+"Remove..!",Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapterRecyclerView.restoreItem(deletedItem,deletedIndex);
            }
        });
        snackbar.setActionTextColor(Color.GREEN);
        snackbar.show();
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