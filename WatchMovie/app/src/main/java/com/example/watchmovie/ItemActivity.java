package com.example.watchmovie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ItemActivity extends AppCompatActivity {


   TextView t;
   ImageView i;
   Spinner spinner;

   //____________________ INITIALISATION FOR CAST RECYCLEVIEW

    private RecyclerView recyclerView4;
    private ArrayList<ItemModel> imageModelArrayList;
    //private ItemAdapter adapter;
    private ItemAdapter2 adapter2;

    //____________________ INITIALISATION FOR EPISODE LIST RECYCLEVIEW

    private RecyclerView recyclerView;
    private ArrayList<EpisodeModel> ArrayList;
    private RecAdapter adapter3;
    ListView listView;

    //____________________ DATA SET FOR CAST WITH IMAGE AND NAME

    private int[] myImageList4=new int[]{R.drawable.cast1,R.drawable.cast2,R.drawable.cast3,R.drawable.cast4,R.drawable.cast5,R.drawable.cast6};
    private String[] myImageNameList4 = new String[]{"Robert", "Chris", "Mark","Chris","Sacrlet","Jeremy"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //____________________ RETRIEVING INTENT DATA FROM ONCLICK FROM FRAGMENT_HOME PAGE

        Intent intent = getIntent();
        int position=intent.getIntExtra("position",999);
        String name=intent.getStringExtra("name");
        int image=intent.getIntExtra("image",999);

        //____________________ ASSIGNING THE IMAGE AND NAME OF VIDEO

        t=(TextView)this.findViewById(R.id.name);
        i=(ImageView)this.findViewById(R.id.image);
        t.setText(name);
        i.setImageResource(image);

        Log.d(TAG, "clicked"+position);


        //____________________ READMORE INITIALISATION

        TextView text2 = findViewById(R.id.text2);
        text2.setText(getString(R.string.desc));

        //____________________ EPISODE LIST RECYCLEVIEW

        recyclerView = (RecyclerView) this.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);

        ArrayList<EpisodeModel> list = new ArrayList<>();

        List<EpisodeModel> movieList = new ArrayList<>();

        movieList.add(new EpisodeModel("Chapter One:", " The Vanishing of Will Byers", "15 Jul. 2016",R.drawable.thumbnail));
        movieList.add(new EpisodeModel("Chapter Two:", "The Weirdo on Maple Street", "15 Jul. 2016",R.drawable.thumbnail));
        movieList.add(new EpisodeModel("Chapter Three:", "Holly, Jolly", "15 Jul. 2016",R.drawable.thumbnail));
        movieList.add(new EpisodeModel("Chapter Four:", "The Body", "15 Jul. 2016",R.drawable.thumbnail));

        RecAdapter adapter = new RecAdapter(movieList);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);



        //____________________ CAST RECYCLEVIEW

        recyclerView4 = (RecyclerView) this.findViewById(R.id.recycler4);

        ArrayList<ItemModel> list4 = new ArrayList<>();

        for (int i = 0; i < myImageList4.length; i++) {
            ItemModel ItemModel = new ItemModel();
            ItemModel.setName(myImageNameList4[i]);
            ItemModel.setImage_drawable(myImageList4[i]);
            list4.add(ItemModel);
        }
        imageModelArrayList = list4;
        adapter2 = new ItemAdapter2(this, imageModelArrayList);
        recyclerView4.setAdapter(adapter2);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


    }

}
