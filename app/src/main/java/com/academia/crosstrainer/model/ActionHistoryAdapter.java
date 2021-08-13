package com.academia.crosstrainer.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.academia.crosstrainer.R;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ActionHistoryAdapter extends RecyclerView.Adapter<ActionHistoryHolder> {

    private final List<ActionHistory> histories;

    public ActionHistoryAdapter(List<ActionHistory> clientes) {
        this.histories = clientes;
    }

    public void addHistory(ActionHistory history){
        histories.add(0, history);
        notifyDataSetChanged();
    }

    @Override
    public ActionHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActionHistoryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_action_history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ActionHistoryHolder holder, int position) {
        ActionHistory history = histories.get(position);
        holder.data.setText(history.getData());
        holder.tempo.setText(history.getTempo());
    }

    @Override
    public int getItemCount() {
        return histories != null ? histories.size() : 0;
    }
}