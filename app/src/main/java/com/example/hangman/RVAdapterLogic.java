package com.example.hangman;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Til løsning af dette er denne youtube video blevet brugt til at lære om RecyclerViews
// --- https://www.youtube.com/watch?v=Vyqz_-sJGFk
//-----------------------------------------------------------------------------------------------
public class RVAdapterLogic extends RecyclerView.Adapter<RVAdapterLogic.ViewHolder> {

    private ArrayList<String> ALDate = new ArrayList<>();
    private ArrayList<String> ALHS = new ArrayList<>();

    public RVAdapterLogic(ArrayList<String> ALDate, ArrayList<String> ALHS) {
        this.ALDate = ALDate;
        this.ALHS = ALHS;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.highscore_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.highscore.setText(ALHS.get(position));
        holder.date.setText(ALDate.get(position));
    }

    @Override
    public int getItemCount() {
        return ALHS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView highscore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.DateView);
            highscore = itemView.findViewById(R.id.HighScoreView);
        }
    }
}
