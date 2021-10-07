package com.academia.crosstrainer.adapter;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.academia.crosstrainer.R;
import com.academia.crosstrainer.model.Train;

import java.util.List;

/**
 * Created by Jamilton Damasceno
 */

public class AdapterTrain extends RecyclerView.Adapter<AdapterTrain.MyViewHolder> {

    List<Train> trainings;
    Context context;

    public AdapterTrain(List<Train> trainings, Context context) {
        this.trainings = trainings;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Train training = trainings.get(position);

        holder.titulo.setText(training.getCircuit());
        holder.valor.setText(String.valueOf(training.getMedal() + "       " + training.getTime()));
        holder.categoria.setText("I - "+training.getInterval()+" E - "+training.getEnergy()+" P - "+training.getWeight()+" -- "+training.getDate());
        /* Criar a lógica para a imagem do treino
        if (training.getTipo() == "d" || training.getTipo().equals("d")) {
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.valor.setText("-" + training.getValor());
        } */
    }


    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, valor, categoria;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
        }

    }

}
