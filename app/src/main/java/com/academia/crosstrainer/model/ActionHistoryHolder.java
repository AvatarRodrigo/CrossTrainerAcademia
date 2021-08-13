package com.academia.crosstrainer.model;

import android.view.View;
import android.widget.TextView;

import com.academia.crosstrainer.R;

import androidx.recyclerview.widget.RecyclerView;

public class ActionHistoryHolder extends RecyclerView.ViewHolder {

    public TextView tempo, data;

    public ActionHistoryHolder(View itemView) {
        super(itemView);
        tempo = (TextView) itemView.findViewById(R.id.tempo);
        data = (TextView) itemView.findViewById(R.id.data);
    }
}