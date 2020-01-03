package com.example.photo.models;

import android.nfc.Tag;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable {
    /**
     * user's name
     */
    private String user_name;
    /**
     * albums belong to the user
     */
    private ArrayList<Album> albums=new ArrayList<Album>();

    /**
     * a helper for copy function in album
     */
    private photo_helper copy;
    /**
     * preset tags of the user
     */
    private ArrayList<Tag> preTags=new ArrayList<Tag>();

    /**
     * get user's name
     * @return user name
     */
    public String getName() {
        return this.user_name;
    }

    /**create a user with nothing*/
    public User() {
        albums = new ArrayList<Album>();
    }

    /**
     * add a preset tag
     * @param tag tag to add
     */
    public void addPreTag(Tag tag){
        preTags.add(tag);
    }

    /**
     * add preset tags
     * @return preset tags
     */
    public ArrayList<Tag> getPreTags(){
        return preTags;
    }

    /**
     * create a user with its name
     * @param username user name
     */
    public User(String username) {
        this.user_name=username;
    }

    /**
     * change user name
     * @param user_name new user name
     */
    public void setName(String user_name) {
        this.user_name=user_name;
    }

    /**
     * get copy
     * @return copy
     */
    public photo_helper getCopy() {
        return this.copy;
    }

    /**
     * set copy
     * @param per copy
     */
    public void setCopy(photo_helper per) {
        this.copy=per;
    }






    /**
     * get albums of one user
     * @return albums
     */
    public ArrayList<Album> getAlbums() {
        return this.albums;
    }

    /**
     * get an album with its index
     * @param i the idnex of the album
     * @return album
     */
    public Album getAlbum(int i) {
        return this.albums.get(i);
    }

    /**
     * create an album and add it to the list with its name
     * @param album_name album name
     * @return false if the album has existed, else add album and return true
     */
    public boolean createAlbum(String album_name) {
        for (Album a:albums) {
            System.out.println(a.getAlbumName());
            if(a.getAlbumName().equals(album_name)) {
                return false;
            }
        }
        Album tmp=new Album(album_name);
        System.out.println("temp:"+tmp);
        System.out.println("temp.getname:"+tmp.getAlbumName());
        albums.add(tmp);

        return true;
    }

    /**
     * create an album and add it to the list with album
     * @param al1 album name
     * @return false if the album has existed, else add album and return true
     */
    public boolean createAlbum(Album al1){
        for (Album a:albums)
        {
//			System.out.println("create"+a.getAlbumName());
//			System.out.println("new create"+al1.getAlbumName());
            if (a.getAlbumName().equals(al1.getAlbumName())) {
                return false;
            }
        }
        albums.add(al1);
        return true;
    }

    /**
     * delete the album if it exists in the list
     * @param album album which need to be deleted
     */
    public void deleteAlbum(Album album) {
        int len=albums.size();
        for (int a = 0; a < len; a++) {
            if(albums.get(a).equals(album)) {
                albums.remove(a);
                return;
            }
        }
    }

    public static void serialize(User userdata) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/data/data/com.photos_android.photos_android43/files/albums.dat"));
        oos.writeObject(userdata);
        oos.close();
    }
    /**
     * rename the valbum
     * @param name1 original name
     * @param name2 supposed name
     * @return false if the album has existed, else rename album and return true
     */
    public Boolean renameAlbum(String name1, String name2) {
        boolean returnboo=true;
        int len=albums.size();
        for (int i = 0; i < len; i++) {
            if(albums.get(i).getAlbumName().equals(name2)) {
                returnboo=false;
                return returnboo;
            }
        }
        for (int j = 0; j < len; j++) {
            if(albums.get(j).getAlbumName().equals(name1)) {
                if(name1!=name2) {
                    albums.get(j).setAlbumName(name2);
                    //System.out.println("i m at rename and i think i m done");
                    return returnboo;
                }
            }
        }
        return returnboo;
    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     * don't change in final version, because it is the content shown in the list view of users
     */
    public String toString() {
        return this.getName();
    }

    /**
     * save user
     */
    public void saveUser() {

    }
}
