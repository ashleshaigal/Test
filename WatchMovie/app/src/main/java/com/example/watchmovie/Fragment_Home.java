package com.example.watchmovie;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment implements ItemAdapter.OnItemListner,ItemAdapter3.OnSeriesListner,ItemAdapter4.OnMusicListner {

    View view;

    //____________________ RECYCLEVIEW AND ADAPTERS

    private RecyclerView recyclerView,recyclerView2,recyclerView3,recyclerView4;
    private ArrayList<ItemModel> imageModelArrayList;
    private ItemAdapter adapter;
    private ItemAdapter2 adapter2;
    private ItemAdapter3 adapter3;
    private ItemAdapter4 adapter4;

    //____________________ INITIALISATION FOR IMAGE SLIDESHOW USING VIEWPAGER

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] banner= {R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4,R.drawable.banner5};
    private ArrayList<Integer> BannerArray = new ArrayList<Integer>();

    //____________________ DATA SET OF IMAGES AND TITLES OF MOVIES, SERIES, MUSIC AND LANGUAGES

    private int[] myImageList = new int[]{R.drawable.movie1,R.drawable.movie2,R.drawable.movie3,R.drawable.movie4,R.drawable.movie5,R.drawable.movie6};
    private String[] myImageNameList = new String[]{"Us", "MIB", "Spiderman", "Uri", "Star Wars", "Mary Poppins"};
    
    private int[] myImageList2=new int[]{R.drawable.series1,R.drawable.series2,R.drawable.series3,R.drawable.series4};
    private String[] myImageNameList2 = new String[]{"Deewane", "Flash", "Colors", "Ships"};

    private int[] myImageList3=new int[]{R.drawable.song1,R.drawable.song2,R.drawable.song3,R.drawable.song4};
    private String[] myImageNameList3 = new String[]{"No Big Deal", "Starboy", "Adele", "Galiyan"};

    private int[] myImageList4=new int[]{R.drawable.lang1,R.drawable.lang2,R.drawable.lang3,R.drawable.lang4,R.drawable.lang5};
    private String[] myImageNameList4 = new String[]{"Hindi", "Bengali", "Malayalam","Punjabi","English"};

    //____________________ INITIALISATION  FOR SLIDE IMAGE CIRCLE INDICATOR

    CircleIndicator indicator;
    private Handler handler;
    private Runnable Update;
    Timer swipeTimer;

    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__home, container, false);
        init();

        //____________________ ALL THE RECYCLEVIEW FOR MOVIES,SERIRES,MUSIC AND LANGUAGES

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recycler2);
        recyclerView3 = (RecyclerView) view.findViewById(R.id.recycler3);
        recyclerView4 = (RecyclerView) view.findViewById(R.id.recycler4);


        //____________________ MOVIES RECYCLEVIEW

            ArrayList<ItemModel> list = new ArrayList<>();

            for (int i = 0; i < myImageList.length; i++) {
                ItemModel ItemModel = new ItemModel();
                ItemModel.setName(myImageNameList[i]);
                ItemModel.setImage_drawable(myImageList[i]);
                list.add(ItemModel);
            }

        imageModelArrayList = list;
        adapter = new ItemAdapter(getActivity(), imageModelArrayList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        //____________________ SERIES RECYCLEVIEW

        ArrayList<ItemModel> list2 = new ArrayList<>();

        for (int i = 0; i < myImageList2.length; i++) {
            ItemModel ItemModel = new ItemModel();
            ItemModel.setName(myImageNameList2[i]);
            ItemModel.setImage_drawable(myImageList2[i]);
            list2.add(ItemModel);
        }
        imageModelArrayList = list2;
        adapter3 = new ItemAdapter3(getActivity(), imageModelArrayList,this);
        recyclerView2.setAdapter(adapter3);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        //____________________ MUSIC RECYCLEVIEW

        ArrayList<ItemModel> list3 = new ArrayList<>();

        for (int i = 0; i < myImageList3.length; i++) {
            ItemModel ItemModel = new ItemModel();
            ItemModel.setName(myImageNameList3[i]);
            ItemModel.setImage_drawable(myImageList3[i]);
            list3.add(ItemModel);
        }
        imageModelArrayList = list3;
        adapter4 = new ItemAdapter4(getActivity(), imageModelArrayList,this  );
        recyclerView3.setAdapter(adapter4);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //____________________ LANGUAGE RECYCLEVIEW

        ArrayList<ItemModel> list4 = new ArrayList<>();

        for (int i = 0; i < myImageList4.length; i++) {
            ItemModel ItemModel = new ItemModel();
            ItemModel.setName(myImageNameList4[i]);
            ItemModel.setImage_drawable(myImageList4[i]);
            list4.add(ItemModel);
        }

        imageModelArrayList = list4;
        adapter2 = new ItemAdapter2(getActivity(), imageModelArrayList);
        recyclerView4.setAdapter(adapter2);
        recyclerView4.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        return view;
    }


    //____________________ METHOD FOR IMAGE SLIDE SHOW USING VIEWPAGER

    private void init() {

        for(int i=0;i<banner.length;i++)
            BannerArray.add(banner[i]);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new SlideAdapter(getActivity(),BannerArray));
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        handler = new Handler();
        Update = new Runnable() {
            public void run() {
                if (currentPage == banner.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);

            }
        };


        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(swipeTimer != null) {
            swipeTimer.cancel();
            BannerArray.clear();

        }
    }


    //____________________ ONCLICK FUNCTION FOR MOVIES RECYCLEVIEW

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick:"+position);

        Intent intent=new Intent(getActivity(),ItemActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("name",myImageNameList[position]);
        intent.putExtra("image",myImageList[position]);
        startActivity(intent);
    }

    //____________________ ONCLICK FUNCTION FOR SERIES RECYCLEVIEW

    @Override
    public void onSeriesClick(int position) {
        Log.d(TAG, "onItemClick:"+position);

        Intent intent=new Intent(getActivity(),ItemActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("name",myImageNameList2[position]);
        intent.putExtra("image",myImageList2[position]);
        startActivity(intent);
    }


    //____________________ ONCLICK FUNCTION FOR MUSIC RECYCLEVIEW

    @Override
    public void onMusicClick(int position) {
        Log.d(TAG, "onItemClick:"+position);

        Intent intent=new Intent(getActivity(),ItemActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("name",myImageNameList3[position]);
        intent.putExtra("image",myImageList3[position]);
        startActivity(intent);
    }

}