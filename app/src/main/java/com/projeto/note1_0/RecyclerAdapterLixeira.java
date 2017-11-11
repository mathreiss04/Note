package com.projeto.note1_0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheus on 01/06/2017.
 */

public class RecyclerAdapterLixeira extends RecyclerView.Adapter<RecyclerAdapterLixeira.ViewHolder> {


    static   List<ModelLixeira> dbList;
    static  Context context;

    RecyclerAdapterLixeira(Context context, List<ModelLixeira> dbList ){
        this.dbList = new ArrayList<ModelLixeira>();
        this.context = context;
        this.dbList = dbList;

    }

    @Override
    public RecyclerAdapterLixeira.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterLixeira.ViewHolder holder, int position) {

        holder.titulo.setText(dbList.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titulo;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            titulo = (TextView) view.findViewById(R.id.rvTitulo);
        }

        @Override
        public void onClick(View view){
            Intent nota = new Intent(context, NotaExcluida.class);

            Bundle extras = new Bundle();
            extras.putInt("position", getAdapterPosition());
            nota.putExtra("", extras);

            context.startActivity(nota);
        }
    }
}

