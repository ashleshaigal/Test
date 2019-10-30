package com.example.watchmovie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//____________________ ADAPTER FOR EPISODE LIST RECYCLEVIEW


public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {

    private List<EpisodeModel> list;
    Context context;

    public RecAdapter(List<EpisodeModel> list) {
        this.list = list;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_episode, parent, false);
        context=parent.getContext();

        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, final int position) {
        final EpisodeModel movie = list.get(position);

        holder.bind(movie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expanded = movie.isExpanded();
                movie.setExpanded(!expanded);
                RecAdapter.this.notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView genre;
        private TextView year;
        private View subItem;
        private ImageView imageView;

        public RecViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            genre = itemView.findViewById(R.id.sub_item_genre);
            year = itemView.findViewById(R.id.sub_item_year);
            imageView =itemView.findViewById(R.id.videothumbnail);
            subItem = itemView.findViewById(R.id.sub_item);
        }

        private void bind(EpisodeModel movie) {
            boolean expanded = movie.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);


            title.setText(movie.getTitle());
            genre.setText("Name: " + movie.getGenre());
            year.setText("Date: " + movie.getYear());
            imageView.setImageResource(movie.getVideo());

            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // your code here
                   // Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,VideoActivity.class);
                    context.startActivity(intent);

                }
            });



            }

        }
    }
