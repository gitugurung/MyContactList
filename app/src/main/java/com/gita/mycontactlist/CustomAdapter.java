package com.gita.mycontactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adv on 4/10/2017.
 */
public class CustomAdapter  extends ArrayAdapter<MyData>{
    ArrayList<MyData>  list;
    Context cv;

    public CustomAdapter(ArrayList<MyData> list,Context context) {
        super(context,R.layout.list_viewdesign,list);
        this.list=list;
        this.cv=context;



    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyData data=list.get(position);

        final View result;
        ViewHolder holder = new ViewHolder();

        if (convertView == null) {



            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_viewdesign, parent, false);
            holder.tname = (TextView) convertView.findViewById(R.id.name);
            holder.tphone = (TextView) convertView.findViewById(R.id.num);
            holder.tadd = (TextView) convertView.findViewById(R.id.address);



            result=convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        holder.tname.setText(data.getName());
        holder.tphone.setText(data.getNumber());
        holder.tadd.setText(data.getAddress());


        // Return the completed view to render on screen
        return convertView;
    }


    public static class ViewHolder {
        TextView tname,tphone,tadd;
    }
}
