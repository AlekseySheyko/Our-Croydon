package com.lbcinternal.sensemble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NavigationDrawerAdapter extends ArrayAdapter<String> {

    public NavigationDrawerAdapter(Context context) {
        super(context, 0, new String[]{
                "News", "Offers and events", "Shape Croygon"});
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drawer_list_item_main,
                parent, false);
        TextView textView = (TextView) view.findViewById(R.id.text1);
        textView.setText(getItem(position));
        return view;
    }
}
