package com.example.photo.models;
/**
 * CS 213 Photo Project
 * <p>
 * Album_models
 *
 * @author Xiao Yan & Chunjie Yang
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import java.util.List;


/**
 * This page will display a list of albums that the login user has. And this page will
 * display the name of the selected album, the total number of photos in the album. And
 *  it will also display the date range of the album.
 */
@SuppressWarnings("serial")
public class Album implements search, Serializable {

    /** Arraylist of the photos that is inside of the album */
    private ArrayList<photo_helper> photos = new ArrayList<photo_helper>();

    /** the array list that we use to search through the photos  */
    private ArrayList<photo_helper> currSearch = new ArrayList<photo_helper>();

    /** the earlies date */
    private Calendar earliestPhoto;

    /** the latest date */
    private Calendar latestPhoto;

    /** The album name. */
    private String albumName;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }

        Album album = (Album) obj;

        return this.albumName.equalsIgnoreCase(((Album) obj).getAlbumName());
    }

    /** when the photo is being search or not */
    private boolean isSearching = false;

    @Override
    public int hashCode() {
        return this.albumName.hashCode();
    }

    /**
     * This method will return the earliest photo
     *
     * @return the date of the photo(earliest)
     */
    public Date getEarliestPhoto() {
        if (earliestPhoto == null)
            return null;
        return earliestPhoto.getTime();
    }

    /**
     * This method will return the latest photo
     *
     * @return the date of the photo(latest)
     */
    public Date getLatestPhoto() {
        if (latestPhoto == null)
            return null;
        return latestPhoto.getTime();
    }

    /**
     * Will return the number of pics inside the photos
     *
     * @return size of photo
     */
    public int getSize() {
        return photos.size();
    }

    /* (non-Javadoc)
     *
     * return the size of the photo
     *
     * @see java.lang.ClassLoader#loadClass(java.lang.String)
     */
    public Date getPhoto() {
        if (latestPhoto == null)
            return null;
        return latestPhoto.getTime();
    }

    /**
     * This method returns the name of the album
     *
     * @param name the album name
     */
    public void setAlbumName(String name) {
        this.albumName = name;
    }


    /**
     * initialize a new album based on the input name
     *
     * @param newname the new name
     */
    public Album(String newname) {
        this.albumName = newname;
    }


    /**
     * add the photo to the list
     *
     * @param thephoto the photo to add
     */
    public void addPhoto(photo_helper thephoto) {
        photos.add(thephoto);

        //if only inserting one photo
        if (photos.size() == 1) {
            earliestPhoto = thephoto.getDate();
            latestPhoto = thephoto.getDate();
        } else if (thephoto.getDate() != null) {
            if (thephoto.getDate().compareTo(earliestPhoto) <= 0) {
                earliestPhoto = thephoto.getDate();
            }
            if (thephoto.getDate().compareTo(latestPhoto) >= 0) {
                latestPhoto = thephoto.getDate();
            }
        }
    }


    /**
     * Adds an array of photos.
     *
     * @param thephotos the photos to add
     */
    public void AddPhoto(photo_helper[] thephotos) {

        photos.addAll(Arrays.asList(thephotos));

        if (photos.size() == 0) {
            earliestPhoto = null;
            latestPhoto = null;
            return;
        }
        earliestPhoto = photos.get(0).getDate();
        latestPhoto = photos.get(0).getDate();
        int length = photos.size();
        for (int i = 1; i < length; i++) {
            photo_helper newp = photos.get(i);
            if (newp.getDate().compareTo(earliestPhoto) <= 0) {
                earliestPhoto = newp.getDate();
            }
            if (newp.getDate().compareTo(latestPhoto) >= 0) {
                latestPhoto = newp.getDate();
            }
        }
    }


    /**
     * remove the photo
     *
     * @param index the index of the photo being removed.
     */
    public void RemovePhoto(int index) {
        Calendar temp = photos.get(index).getDate();
        photos.remove(index);
        if (temp.compareTo(earliestPhoto) == 0 || temp.compareTo(latestPhoto) == 0)
            if (photos.size() == 0) {
                earliestPhoto = null;
                latestPhoto = null;
                return;
            }
        earliestPhoto = photos.get(0).getDate();
        latestPhoto = photos.get(0).getDate();
        int length = photos.size();
        for (int i = 1; i < length; i++) {
            photo_helper phot = photos.get(i);
            if (phot.getDate().compareTo(earliestPhoto) <= 0) {
                earliestPhoto = phot.getDate();
            }
            if (phot.getDate().compareTo(latestPhoto) >= 0) {
                latestPhoto = phot.getDate();
            }
        }
    }

    /**
     * Removes a photo if present.
     *
     * @param phot the photo to remove
     */
    public void RemovePhoto(photo_helper phot) {
        photos.remove(phot);

//        Calendar temp = phot.getDate();
//        if (temp.compareTo(earliestPhoto) == 0 || temp.compareTo(latestPhoto) == 0)
//            if (photos.size() == 0) {
//                earliestPhoto = null;
//                latestPhoto = null;
//                return;
//            }
//        earliestPhoto = photos.get(0).getDate();
//        latestPhoto = photos.get(0).getDate();
//        int length = photos.size();
//        for (int i = 1; i < length; i++) {
//            photo_helper phot1 = photos.get(i);
//            if (phot1.getDate().compareTo(earliestPhoto) <= 0) {
//                earliestPhoto = phot1.getDate();
//            }
//            if (phot1.getDate().compareTo(latestPhoto) >= 0) {
//                latestPhoto = phot1.getDate();
//            }
//        }
    }

    /**
     * Gets the photos.
     *
     * @return the photos
     */
    public List<photo_helper> getPhotos() {
        return photos;
    }

    /**
     * Gets the photo at a certain index.
     *
     * @param index the index
     * @return the photo
     */
    public photo_helper getPhoto(int index) {
        return photos.get(index);
    }

    /**
     * Gets the album name.
     *
     * @return the album name
     */
    public String getAlbumName() {
        return this.albumName;
    }

    /**
     * Search through the list and check if there is any photo that matches the date
     * @param tags the tags that we are searching for
     * @return a list of tags that match the date range
     */

    public photo_helper[] searchPhotos(List<Tag> tags) {
        clearSearch();
        for (int i = 0; i < photos.size(); i++) {
          if (photos.get(i).isMatch(tags))
            {
                currSearch.add(photos.get(i));
                //	System.out.println("wtf");
            }
        }
        photo_helper[] temp = currSearch.toArray(new photo_helper[currSearch.size()]);
        isSearching = true;
        return temp;
    }


    /* (non-Javadoc)
     *
     * @see model.SearchPhotos#ClearSearch()
     */
    public void clearSearch() {
        currSearch.clear();
        isSearching = false;
    }

    /**
     * check of the list contains the photo that we are looking for
     *
     * @param phot the target photo
     * @return true, if successful
     */
    public boolean contains(photo_helper phot) {
        for (photo_helper temp : photos) {
            if (temp.getImage().equals(phot.getImage()))
                return true;
        }
        return false;
    }


    /* (non-Javadoc)
     *
     * @see model.SearchPhotos#andPhotos(model.Tag)
     */
    public photo_helper[] andPhotos(Tag newtag, Calendar startdate, Calendar enddate) {
        ArrayList<Tag> temptaglist = new ArrayList<Tag>();
        temptaglist.add(newtag);
        ArrayList<photo_helper> toRemove = new ArrayList<photo_helper>();
        for (photo_helper temp : currSearch) {
//            if (!temp.isMatch(temptaglist))  --fix
            {
                toRemove.add(temp);
                //System.out.println("bop");
            }

        }
        for (photo_helper temp : toRemove) {
            currSearch.remove(temp);
        }
        photo_helper[] temp = currSearch.toArray(new photo_helper[currSearch.size()]);
        //	System.out.println(temp.length);
        return temp;

    }


    /* (non-Javadoc)
     *
     * @see model.SearchPhotos#orPhotos(model.Tag)
     */
    public photo_helper[] orPhotos(Tag newtag, Calendar startdate, Calendar enddate) {
        ArrayList<Tag> temptaglist = new ArrayList<Tag>();
        temptaglist.add(newtag);
        for (photo_helper temp : photos) {
            //System.out.println(temp.isMatch(temptaglist, startdate, enddate));
//            if (temp.isMatch(temptaglist)&&!currSearch.contains(temp)) --fix
            {
                currSearch.add(temp);
                //System.out.println("bop");
            }

        }
        photo_helper[] temp = currSearch.toArray(new photo_helper[currSearch.size()]);
        return temp;
    }


    /* (non-Javadoc)
     *
     * @see model.SearchPhotos#orPhotos(model.Tag)
     */
    public void recalculatedOldNewDates() {
        if (photos.size() == 0) {
            earliestPhoto = null;
            latestPhoto = null;
            return;
        }
        earliestPhoto = photos.get(0).getDate();
        latestPhoto = photos.get(0).getDate();
        int length = photos.size();
        for (int i = 1; i < length; i++) {
            photo_helper phot = photos.get(i);
            if (phot.getDate().compareTo(earliestPhoto) <= 0) {
                earliestPhoto = phot.getDate();
            }
            if (phot.getDate().compareTo(latestPhoto) >= 0) {
                latestPhoto = phot.getDate();
            }
        }

    }




}
