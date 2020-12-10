package com.example.laakintapaivakirja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ReminderListAdapter extends ArrayAdapter<Reminder> {
    private Context mContext;
    int mResource;

    public ReminderListAdapter(Context context, int resource, ArrayList<Reminder> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String time = getItem(position).getTime();

        Reminder reminder = new Reminder(name, time);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvTime = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvType = (TextView) convertView.findViewById(R.id.textView3);

        tvName.setText(name);
        tvTime.setText(time);

        return convertView;

    }
}