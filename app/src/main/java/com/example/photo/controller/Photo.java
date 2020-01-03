package com.example.photo.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.photo.MainActivity;
import com.example.photo.R;
import com.example.photo.adapter.CustomAdapter;
import com.example.photo.models.Album;
import com.example.photo.models.Tag;
import com.example.photo.models.User;
import com.example.photo.models.photo_helper;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.photo.MainActivity.manager;

/**
 * CS 213 Photo Project
 * <p>
 * Photo page Controller
 *
 * @author Xiao Yan & Chunjie Yang
 */

// 1. 80 don't know how to present the tag
// 2. prev and next not finished
// 3. get selectedTag/ 62 do we need to create a file here?


public class Photo extends AppCompatActivity {
    ArrayList<Tag> tags;
    Tag selectedTag;
    ListView testList;

    photo_helper selectedPhoto;
    ArrayAdapter<Tag> itemsAdapter;

    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        findViewById(R.id.addTagAction).setOnClickListener(v -> {
            addtag();
        });
        findViewById(R.id.removeTagAction).setOnClickListener(v -> {
            deletetag();
        });
        findViewById(R.id.prevAction).setOnClickListener(v -> {
            prevAction();
        });
        findViewById(R.id.nextAction).setOnClickListener(v -> {
            nextAction();
        });

        selectedPhoto = Albumpage.selectedPhoto;


        imageView = ((ImageView) findViewById(R.id.imageView));
        imageView.setImageBitmap(selectedPhoto.getImage());


        // get selectedTag
        selectedTag = null;
        testList = (ListView) findViewById(R.id.tagListView);

        setList();


        // do we need to create a file here?????????

        // get the selected Tag
        testList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //set onclicked album as current album
                Tag tmptag = tags.get(i);


                selectedTag = tmptag;
            }
        });

    }

    private void setList() {
        tags = selectedPhoto.getTag();
        itemsAdapter =
                new ArrayAdapter<Tag>(this, android.R.layout.simple_list_item_1, tags);
        testList.setAdapter(itemsAdapter);
    }

    public void prevAction() {
        Album album = MainActivity.selectedAlbum;

        if (Albumpage.selectedIndex <= 0) {
            return;
        }

        Albumpage.selectedIndex--;
        selectedPhoto = album.getPhotos().get(Albumpage.selectedIndex);
        imageView.setImageBitmap(selectedPhoto.getImage());
        setList();
    }

    public void nextAction() {
        Album album = MainActivity.selectedAlbum;

        if (Albumpage.selectedIndex >= album.getPhotos().size() - 1) {
            return;
        }

        Albumpage.selectedIndex++;
        selectedPhoto = album.getPhotos().get(Albumpage.selectedIndex);
        imageView.setImageBitmap(selectedPhoto.getImage());
        setList();
    }

    public void addtag() {
        EditText tagtype = (EditText) findViewById(R.id.editText4);
        Editable v = tagtype.getText();
        String ttype = v.toString().trim();

        EditText tagvalue = (EditText) findViewById(R.id.editText5);
        Editable v2 = tagvalue.getText();
        String tvalue = v2.toString().trim();

        if (!ttype.equalsIgnoreCase("person") && !ttype.equalsIgnoreCase("location")) {
            Toast.makeText(this, "Please enter a person or location tag", Toast.LENGTH_SHORT).show();
            return;
        } else if (!ttype.isEmpty() && tvalue.isEmpty()) {
            Toast.makeText(this, "Please enter a valid tag value", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < tags.size(); i++) {
                if ((tags.get(i).tagType.equals(ttype)) && (tags.get(i).tagValue.equals(tvalue))) {
                    Toast.makeText(this, "Please enter a not duplicated tag", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // add the tag
            tags.add(new Tag(ttype, tvalue));

            // present the tag: I want to show tagtype+ tagvalue
            itemsAdapter.notifyDataSetChanged();

            try {
                User.serialize(manager);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tagtype.getText().clear();
            tagvalue.getText().clear();

        }
    }

    public void deletetag() {

        if (!tags.isEmpty()) {

            tags.remove(selectedTag);
            try {
                User.serialize(manager);
            } catch (IOException e) {
                e.printStackTrace();
            }

            itemsAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "There is no tag for you to delete", Toast.LENGTH_SHORT).show();
            return;
        }
    }


}