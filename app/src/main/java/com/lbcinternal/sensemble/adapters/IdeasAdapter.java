package com.lbcinternal.sensemble.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.rest.model.Idea;

import java.util.List;

public class IdeasAdapter extends ArrayAdapter<Idea> {

    List<Idea> mEntries;

    public IdeasAdapter(Context context, List<Idea> entries) {
        super(context, 0, entries);
        mEntries = entries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.feed_list_item,
                parent, false);

        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        Idea i = getItem(position);
        titleTextView.setText(i.getTitle());

        TextView dateTextView = (TextView) view.findViewById(R.id.date);
        dateTextView.setText(i.getCreationDate());

        return view;
    }
}
