package com.projeto.note1_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    CriaBanco helpher;
    List<Model> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutMananger;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent novaNota = new Intent(getActivity(), NovaNota.class);
                getActivity().startActivity(novaNota);
            }
        });

        helpher = new CriaBanco(getContext());

        dbList = new ArrayList<Model>();
        dbList = helpher.getDataFromBD();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);

        mLayoutMananger = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutMananger);

        mAdapter = new RecyclerAdapter(getActivity(), dbList);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}

