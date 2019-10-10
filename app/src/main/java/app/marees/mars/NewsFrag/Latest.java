package app.marees.mars.NewsFrag;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import app.marees.NewsAdapter;
import app.marees.mars.R;
import app.marees.mars.Singletons.Myapp;


public class Latest extends Fragment {

    RecyclerView recycler;
   ArrayList<String> late = new ArrayList<>();
    ArrayList<String> late2 = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View myNewsView = inflater.inflate(R.layout.news_one,container,false);

        recycler= myNewsView.findViewById(R.id.trends);
        LinearLayoutManager myLine = new LinearLayoutManager(Myapp.getAppContext());
        myLine.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(myLine);


        late = Myapp.getLatest();
        late2 = Myapp.getLatestu();
        if(late.size()>0){

            NewsAdapter l = new NewsAdapter(late,late2,getActivity());
            recycler.setAdapter(l);

        }

        return myNewsView;

    }


}
