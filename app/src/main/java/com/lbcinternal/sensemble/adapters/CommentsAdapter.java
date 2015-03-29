package com.lbcinternal.sensemble.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.lbcinternal.sensemble.rest.model.Comment;

import java.util.List;

public class CommentsAdapter extends ArrayAdapter<Comment> {

    public CommentsAdapter(Context context, List<Comment> comments) {
        super(context, 0, comments);
    }
}
