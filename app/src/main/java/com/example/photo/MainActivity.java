package com.example.photo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.photo.adapter.CustomAdapter;
import com.example.photo.controller.Albumpage;
import com.example.photo.controller.Search;
import com.example.photo.models.User;
import com.example.photo.models.Album;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    final Context c = this;

    //final EditText albumNameFromDialogBox = new EditText(this);
    String albumName;

    public static User manager = new User();

    File albumsfile = new File("/data/data/com.photo/files/albums.dat");
    ListView testList;
    private static List<String> albums = new ArrayList<String>();
    private CustomAdapter adapter;
//    private List<Album> albumList = new ArrayList<>();
    public static Album selectedAlbum;
    private Album album;
//    public static final String ALBUMLIST = "albumList";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/data/data/com.photo/files/albums.dat"));
            User userdata = (User) ois.readObject();
            ois.close();
            manager = userdata;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedAlbum = null;

        if (!albumsfile.exists()) {
            Context context = this;
            File file = new File(context.getFilesDir(), "albums.dat");
            try {
                file.createNewFile();
            } catch (IOException e) {

            }
        }

        albums.clear();

        for (int i = 0; i < manager.getAlbums().size(); i++) {
            albums.add(manager.getAlbums().get(i).getAlbumName());
        }

        testList = (ListView) findViewById(R.id.listview_userpage);
//        adapter = new ArrayAdapter<String>(this, R.layout.listviewadapter, R.id.text, albums);
        adapter = new CustomAdapter( this, manager.getAlbums());
        testList.setAdapter(adapter);


//        testList.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Context context = getApplicationContext();
//                int duration = Toast.LENGTH_SHORT;
//                    Toast toast = Toast.makeText(context, "i m here", duration);
//                    toast.show();
//
//                //selectedAlbum = (Albumpage) parent.getItemAtPosition(position);
//                album = manager.getAlbums().get(position);
//                selectedAlbum = album;
//                    Toast toast2 = Toast.makeText(context, "hello", duration);
//                    toast2.show();
//                v.setSelected(!v.isSelected());
//            }
//        });


        testList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //set onclicked album as current album
                Album album = manager.getAlbums().get(i);


                selectedAlbum = album;
            }
        });


    }


    public void viewAlbumAction(View view) {
        if(selectedAlbum == null) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "No Albums are selected", duration);
            toast.show();
            return;
        }

        //Create intent
        Intent intent = new Intent(MainActivity.this, Albumpage.class);
        //Start details activity
        startActivity(intent);
    }

    public void deleteAlbumAction(View view)
    {
            if(selectedAlbum !=null ) {
                manager.deleteAlbum(selectedAlbum);
                try {
                    User.serialize(manager);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }else{
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, "No Albums are selected", duration);
                toast.show();
                return;
            }
    }



    public void recaptionAlbumAction(View view)
    {
        EditText input = (EditText) findViewById(R.id.newalbumname);
        Editable value = input.getText();
        String str = value.toString();
        str = str.trim();

        if(str.isEmpty()){
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Not Valid, Please don't enter an empty string", duration);
            toast.show();
            return;
        }

        if(selectedAlbum == null) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "No Albums are selected", duration);
            toast.show();
            return;
        }

        if(manager.getAlbums().contains(new Album(str))){
            Toast.makeText(this, "No Duplication Name", Toast.LENGTH_SHORT).show();
            return;
        }

        selectedAlbum.setAlbumName(str);
        adapter.notifyDataSetChanged();

    }

    public void searchAction(View view)
    {
        if(selectedAlbum == null) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "No Albums are selected", duration);
            toast.show();
            return;
        }
        //Create intent
        Intent intent = new Intent(MainActivity.this, Search.class);
        //intent.putExtra("albumName", selectedAlbum.getAlbumName());

        //Start details activity
        startActivity(intent);
    }

    public void newalbumname(View view){

    }

    public void create(View view){
        EditText input = (EditText) findViewById(R.id.newalbumname);
        Editable value = input.getText();
        String str = value.toString();
        str = str.trim();

        if(str.isEmpty()){
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Not Valid, Please don't enter an empty string", duration);
            toast.show();
            return;
        }else if(manager.createAlbum(str)){
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

//            albumList.add(new Album(str));

            Toast toast = Toast.makeText(context, "Create new album successfully", duration);
            toast.show();
            albums.clear();

            for(int i = 0; i < manager.getAlbums().size(); i++) {
                albums.add(manager.getAlbums().get(i).getAlbumName());
            }

            adapter.notifyDataSetChanged();
//            testList.setAdapter(adapter);
          //  testList.getSelectionModel().select(manager.getAlbum(manager.getAlbums().size()-1));
            try {
                User.serialize(manager);
            } catch (IOException e) {
                e.printStackTrace();
            }
            input.getText().clear();
        }else{
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Not valid, There is already an album with same name which you want to use.\")", duration);
            toast.show();
            return;

        }

    }



}