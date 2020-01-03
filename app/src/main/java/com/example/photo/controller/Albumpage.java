package com.example.photo.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.photo.MainActivity;
import com.example.photo.R;
import com.example.photo.adapter.ImageAdapter;
import com.example.photo.models.Album;
import com.example.photo.models.photo_helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * CS 213 Photo Project
 * <p>
 * Albumpage Page Controller
 *
 * @author Xiao Yan & Chunjie Yang
 */

// 1. you add a photo but you did not sore it=> have to do something like album.add()
// 2. 109 not sure if it is right
// 3. how to get selected photo rather than select url
// 4. how to keep data consistence in the application

public class Albumpage extends AppCompatActivity {

    public static photo_helper selectedPhoto;
    public static int selectedIndex;
    ImageAdapter imageAdapter;


    Album album;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo_album);

        album = MainActivity.selectedAlbum;

        imageAdapter = new ImageAdapter(this, album);
        ListView imageList = ((ListView) findViewById(R.id.listOfImages));

        imageList.setAdapter(imageAdapter);
        imageList.setOnItemClickListener((adapterView, view, index, l) ->{
            selectedPhoto = album.getPhotos().get(index);
            selectedIndex = index;
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
//            ImageView i = new ImageView(this);
            InputStream inputStream = getContentResolver().openInputStream(data.getData());
            imageAdapter.addImages(inputStream);
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            i.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addPhotoAction(View view) {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
            } else {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_FROM_GALLERY);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deletePhotoAction(View view) {
        if(selectedPhoto !=null ) {
            album.RemovePhoto(selectedPhoto);
            imageAdapter.notifyDataSetChanged();
        }else{
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "No Photo is selected", duration);
            toast.show();
            return;
        }
    }

    private static final int PICK_FROM_GALLERY = 1;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }


    public void viewPhotoAction(View view) {
       // System.out.println(selectedPhoto.getImage());
        if (selectedPhoto == null) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "No Photo are selected", duration);
            toast.show();
            return;
        }

        Intent intent = new Intent(Albumpage.this, Photo.class);
        startActivity(intent);
    }

    public void movePhotoAction(View view) {

    }
}
