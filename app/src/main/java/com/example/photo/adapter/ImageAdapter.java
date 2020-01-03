package com.example.photo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.photo.R;
import com.example.photo.models.Album;
import com.example.photo.models.photo_helper;

import java.io.InputStream;

public class ImageAdapter extends BaseAdapter {
    private Context context; //context
    private Album album; //data source of the list adapter

    //public constructor
    public ImageAdapter(Context context, Album album) {
        this.context = context;
        this.album = album;
    }

    @Override
    public int getCount() {
        return album.getPhotos().size(); //returns total of album in the list
    }

    @Override
    public Object getItem(int position) {
        return album.getPhotos().get(position); //returns list item at the specified position
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
                    inflate(R.layout.listviewimageadapter, parent, false);
            ((ImageView) convertView.findViewById(R.id.imageView))
                    .setImageBitmap(album.getPhotos().get(position).getImage());
        }

        return convertView;
    }

    public void addImages(InputStream is) {
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        album.getPhotos().add(new photo_helper(bitmap));
        notifyDataSetChanged();
    }
}