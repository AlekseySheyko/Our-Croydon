package com.lbcinternal.sensemble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lbcinternal.sensemble.views.FeedEntry;

import java.util.List;

public class FeedListAdapter extends ArrayAdapter<FeedEntry> {

    public FeedListAdapter(Context context, List<FeedEntry> entries) {
        super(context, 0, entries);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.feed_entry,
                parent, false);

        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setText(getItem(position).getTitle());

        return view;
    }
}
