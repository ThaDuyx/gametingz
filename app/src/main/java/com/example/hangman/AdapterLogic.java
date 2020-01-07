package com.example.hangman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class AdapterLogic extends ArrayAdapter<String> {

    private Context context;
    private int resource;

    public AdapterLogic(@NonNull Context context, int resource, @NonNull String[] words) {
        super(context, resource, words);

        this.context = context;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(resource, parent, false);

        TextView tView = convertView.findViewById(R.id.textView16);

        tView.setText(getItem(position));

        return convertView;
    }
}
