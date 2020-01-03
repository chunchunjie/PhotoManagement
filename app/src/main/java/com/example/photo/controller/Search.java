package com.example.photo.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.photo.MainActivity;
import com.example.photo.R;
import com.example.photo.adapter.ImageAdapter;
import com.example.photo.models.Album;
import com.example.photo.models.Tag;
import com.example.photo.models.User;
import com.example.photo.models.photo_helper;

import java.util.ArrayList;
import java.util.List;

/**
 * CS 213 Photo Project
 *
 * Search Controller
 *
 * @author Xiao Yan & Chunjie Yang
 */
// line 73



public class Search extends AppCompatActivity {

    boolean searchboo=false;
    List<Tag> searchlist = new ArrayList<>();
    User user;

    private  ImageAdapter adapter;

    EditText tagtype ;
    String ttype ;

    EditText tagvalue;
    String tvalue;
    ListView testList;
    Album album = new Album("search");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        user = MainActivity.manager;
        adapter = new ImageAdapter(this, album);

        testList = (ListView) findViewById(R.id.listview_userpage);
        tagtype =  (EditText) findViewById(R.id.editText4);
        tagvalue =   (EditText) findViewById(R.id.editText5);
        testList.setAdapter(adapter);
    }

    public void searchaction(View view) {

        ttype = tagtype.getText().toString().trim();
        tvalue = tagvalue.getText().toString().trim();

        searchboo=true;
        if(!ttype.equalsIgnoreCase("person") && !ttype.equalsIgnoreCase("location")){
            Toast.makeText(this, "Please enter a person or location tag", Toast.LENGTH_SHORT).show();
            return;
        }else if(ttype.isEmpty() || tvalue.isEmpty()){
            Toast.makeText(this, "Please enter a valid tag value", Toast.LENGTH_SHORT).show();
            return;
        }else{
            searchlist.add(new Tag(ttype,tvalue));
        }

        // do the search
            ArrayList<Album> finalalbum=user.getAlbums();

        for(Album al:finalalbum) {
            photo_helper[] photolist=al.searchPhotos(searchlist);
            for(photo_helper photocheck:photolist) {
                if(!album.contains(photocheck)) {
                    album.addPhoto(photocheck);
                }
            }
        }

        // end
        adapter.notifyDataSetChanged();
    }

    public void andsearch(View view) {
        ttype = tagtype.getText().toString().trim();
        tvalue = tagvalue.getText().toString().trim();
        if(searchboo==false || album.getSize()==0) {
            Toast.makeText(this, "you are not able to do the and search", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!ttype.equalsIgnoreCase("person") && !ttype.equalsIgnoreCase("location")){
            Toast.makeText(this, "Please enter a person or location tag", Toast.LENGTH_SHORT).show();
            return;
        }else if(ttype.isEmpty() || tvalue.isEmpty()){
            Toast.makeText(this, "Please enter a valid tag value", Toast.LENGTH_SHORT).show();
            return;
        }else{
            ArrayList<Tag> tags=new ArrayList<Tag>();
            ArrayList<Album> finalize=user.getAlbums();

            tags.add(new Tag(ttype,tvalue));
            ArrayList<photo_helper> finallist=select_helper(finalize, tags);
            ArrayList<photo_helper> minus=remove_helper(album.getPhotos(),finallist);

            for(photo_helper p: minus) {
                album.RemovePhoto(p);
            }

            adapter.notifyDataSetChanged();
        }

    }


    public void orsearch(View view) {
        ttype = tagtype.getText().toString().trim();
        tvalue = tagvalue.getText().toString().trim();
        if(searchboo==false || album.getSize()==0) {
            Toast.makeText(this, "you are not able to do the or search", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!ttype.equalsIgnoreCase("person") && !ttype.equalsIgnoreCase("location")){
            Toast.makeText(this, "Please enter a person or location tag", Toast.LENGTH_SHORT).show();
            return;
        }else if(ttype.isEmpty() ||tvalue.isEmpty()){
            Toast.makeText(this, "Please enter a valid tag value", Toast.LENGTH_SHORT).show();
            return;
        }else{
            ArrayList<Tag> tags=new ArrayList<Tag>();
            ArrayList<Album> finalize=user.getAlbums();

            tags.add(new Tag(ttype, tvalue));
            for(Album album1:finalize) {
                photo_helper[] p=album1.searchPhotos(tags);
                for(photo_helper p2:p) {
                    if(!album.contains(p2)) {
                        album.addPhoto(p2);
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }

    }

    public ArrayList<photo_helper> select_helper(ArrayList<Album> a, ArrayList<Tag> a2){
        ArrayList<photo_helper> returnarray=new ArrayList<photo_helper>();
        for(Album b:a) {
            photo_helper[] p=b.searchPhotos(a2);
            for(photo_helper p2:p) {
                if(!returnarray.contains(p2)) {
                    returnarray.add(p2);
                }
            }
        }
        return returnarray;
    }

    public ArrayList<photo_helper> remove_helper(List<photo_helper> p, ArrayList<photo_helper> p2){
        ArrayList<photo_helper> returnarray=new ArrayList<photo_helper>();
       // List<photo_helper> p3=album.getPhotos();
        for (photo_helper phot:p)
        {
            if (!p2.contains(phot))
                returnarray.add(phot);
        }

        return returnarray;
    }

}
