package com.david.findcocktail.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.david.findcocktail.R;
import com.david.findcocktail.models.Item;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapterRecyclerView extends RecyclerView.Adapter<MyAdapterRecyclerView.MyViewHolder> {

     ArrayList<Item> mList;

    public MyAdapterRecyclerView(ArrayList<Item> mList){
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = inflater.inflater(R.id.layout.fragment_drawer, container, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    //bind your sdata here
        holder.tvName.setText(mList.get(position).getName());
        holder.tvDescription.setText(mList.get(position).getDescription());
        holder.ivUser.setImageResource(mList.get(position).getIdImage());
    }

    @Override
    public int getItemCount() {

        if (mList == null){
            return 0;
        }else{
            return mList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView ivUser;
        TextView tvName, tvDescription;
        public RelativeLayout viewF,viewB;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //typecast
            ivUser = itemView.findViewById(R.id.ivUser);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            viewF = itemView.findViewById(R.id.rl);
            viewB = itemView.findViewById(R.id.background);
        }
    }

    //here we create two methods for remove item and restore item when we swipe or drage ok
    public void removeItem(int position){
        mList.remove(position);
        //this will update recyclerview means refresh it
        notifyItemRemoved(position);
    }
    public void restoreItem(Item item, int position){
        mList.add(position,item);
        notifyItemInserted(position);
    }
}
