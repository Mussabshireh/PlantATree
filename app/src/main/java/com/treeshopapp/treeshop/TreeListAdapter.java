package com.treeshopapp.treeshop;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TreeListAdapter extends ArrayAdapter<Tree>{

    private static final String TAG="TreeListAdapter";

    Context context;
    int layoutResourceId;
    ArrayList<Tree> data=null;

    private static class ViewMolder{
        TextView name;
        TextView price;
    }

    public TreeListAdapter(Context context, int resource, ArrayList<Tree> objects){
        super(context, resource, objects);

        this.layoutResourceId=resource;
        this.context=context;
        this.data=objects;
    }

    static class DataHolder
    {
        ImageView treeImg;
        TextView treeName;
        TextView price;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataHolder holder=null;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){

            convertView=inflater.inflate(R.layout.itemrow,null);

            holder=new DataHolder();
            holder.treeImg=(ImageView)convertView.findViewById(R.id.TheTreeImage);
            holder.treeName=(TextView)convertView.findViewById(R.id.TheNameOfTree);
            holder.price=(TextView)convertView.findViewById(R.id.ThePrice);

            convertView.setTag(holder);
        }
        else{
            holder=(DataHolder)convertView.getTag();
        }

        Tree treeItem=data.get(position);
        holder.treeName.setText(treeItem.getTheNameOfTree());
        holder.treeImg.setImageResource(treeItem.getImgURL());

        return convertView;
    }
}






