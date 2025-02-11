package com.example.laakintapaivakirja;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;

/**
 * Mukautettu adapteri listviewin tarpeisiin.
 * @author Aksel Manns
 * @version 11/12/2020
 */
public class ReminderListAdapter extends ArrayAdapter<Reminder> {
    private Context mContext; //Adapterin muuttujat määritetään.
    int adResource;

    /**
     * Adapterin syntyessä sen muuttujien arvot liitetään superiin.
     * @param context Nykyinen context.
     * @param resource Resurssi Id, jotta textview:n voi löytää.
     * @param objects Listviewin objektit.
     */
    public ReminderListAdapter(Context context, int resource, ArrayList<Reminder> objects) {
        super(context, resource, objects);
        mContext = context;
        adResource = resource;
    }

    @NonNull
    @Override
    /**
     * Haetaan yksittäiset listview:in solut ja liitetään haluttu data niiden tekstikenttiin.
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String time = getItem(position).getTime();
        String type = getItem(position).getType();
        String date = getItem(position).getDate();
        int typeindex = getItem(position).getTypeIndex();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(adResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvTime = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvDate = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvType = (TextView) convertView.findViewById(R.id.textView4);


        tvName.setText(name);
        tvTime.setText(time);
        tvType.setText(type);
        tvDate.setText(date);

        //Säädetään solujen taustaväri valitun lääketyypin mukaan tyyppi-indexin perusteella.
        if(typeindex == 1){
            convertView.findViewById(R.id.LayoutH).setBackgroundColor(Color.rgb(255, 99, 71));
            convertView.findViewById(R.id.LayoutL).setBackgroundColor(Color.rgb(255, 99, 71));
        }else if(typeindex == 2){
            convertView.findViewById(R.id.LayoutH).setBackgroundColor(Color.rgb(52, 244, 255));
            convertView.findViewById(R.id.LayoutL).setBackgroundColor(Color.rgb(52, 244, 255));
        }else if(typeindex == 3) {
            convertView.findViewById(R.id.LayoutH).setBackgroundColor(Color.rgb(204, 51, 51));
            convertView.findViewById(R.id.LayoutL).setBackgroundColor(Color.rgb(204, 51, 51));
        }

        return convertView;
    }
}