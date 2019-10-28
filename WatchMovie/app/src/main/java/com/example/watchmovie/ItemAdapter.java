package com.example.watchmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//____________________ ADAPTER FOR MOVIES RECYCLEVIEW



public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ItemModel> imageModelArrayList;
    private OnItemListner mOnItemListner;

    public ItemAdapter(Context ctx, ArrayList<ItemModel> imageModelArrayList,OnItemListner onItemListner){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
        this.mOnItemListner=onItemListner;

    }

    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view,mOnItemListner);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.MyViewHolder holder, int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        holder.time.setText(imageModelArrayList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView time;
        ImageView iv;
        OnItemListner onItemListner;

        public MyViewHolder(View itemView,OnItemListner onItemListner) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            this.onItemListner=onItemListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListner.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemListner{
        void onItemClick(int position);
    }
}
