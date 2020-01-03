package com.example.photo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.photo.R;
import com.example.photo.models.Album;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Album> albums; //data source of the list adapter

    //public constructor
    public CustomAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @Override
    public int getCount() {
        return albums.size(); //returns total of albums in the list
    }

    @Override
    public Object getItem(int position) {
        return albums.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.listviewadapter, parent, false);
        }

        // get current item to be displayed
        Album currentItem = (Album) getItem(position);

        // get the TextView for item name and item description
        TextView itemText = (TextView)
                convertView.findViewById(R.id.text);

        //sets the text for item name and item description from the current item object
        itemText.setText(albums.get(position).getAlbumName());

        // returns the view for the current row
        return convertView;
    }
}