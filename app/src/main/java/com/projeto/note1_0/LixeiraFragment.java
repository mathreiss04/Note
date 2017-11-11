package com.projeto.note1_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LixeiraFragment extends Fragment{

    CriaBanco helpher;
    List<ModelLixeira> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutMananger;

    public LixeiraFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_lixeira, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabLixeira);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });

        helpher = new CriaBanco(getContext());

        dbList = new ArrayList<ModelLixeira>();
        dbList = helpher.getDataFromBDLixeira();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycleLixeira);
        mRecyclerView.setHasFixedSize(true);

        mLayoutMananger = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutMananger);

        mAdapter = new RecyclerAdapterLixeira(getActivity(), dbList);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
