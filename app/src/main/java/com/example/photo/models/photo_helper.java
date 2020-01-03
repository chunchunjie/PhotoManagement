package com.example.photo.models;
/**
 * CS 213 Photo Project
 * <p>
 * photo_helper page
 *
 * @author Xiao Yan & Chunjie Yang
 */

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Calendar;


import java.io.*;
import java.util.List;

/**
 * This is a photo helper which will set and get the special property of a photo such as tags, captions and image.
 * This class will help us to manage the code better.
 */
@SuppressWarnings("serial")
public class photo_helper implements Serializable {

    /** a list of tags  */
    private ArrayList<Tag> tags = new ArrayList<Tag>();

    /** photo's data */
    private Calendar date;

    /** the string caption */
    private String caption = "";

    /** the photo's storing image  */
    public Bitmap image;


    /* (non-Javadoc)
     *
     * @see model.Tags#getTags()
     */
    public Tag[] getTags() {
        Tag[] temp = tags.toArray(new Tag[tags.size()]);
        return temp;
    }

    /**
     * a method that return the date of the photo
     *
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * set the image path for the new photo
     * @param image path of the photo
     */
    public photo_helper(Bitmap image) {
        this.image = image;
    }

    /**
     * Search for photos that match the tags we are looking for
     * @param comp a list of tags
     * @return if photos contain, return trues
     */
    public boolean searchTag(List<Tag> comp) {
        for (Tag temp : tags) {
            for (Tag c : comp) {
                // if they have same tag type
                if (c.tagType.equals(temp.tagType)) {
                    // temp is a temp
                    if (temp.tagValue.toLowerCase().contains(c.tagValue.toLowerCase()))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * setting the date.
     *
     * @param b the date
     */
    public void setDate(Calendar b) {
        date = b;
    }

    /**
     * Search the tags that match up with the target
     *
     * @param comptags the targeting tags
     * @return if contains, true
     */
    public boolean searchTag(Tag comptags) {
        for (Tag temp : tags) {
            if (comptags.tagType.equals(temp.tagType)) {
                if (temp.contains(comptags.tagValue))
                    return true;
            }

        }
        return false;
    }

    /* (non-Javadoc)
     * @see model.Tags#AddTag(java.lang.String, java.lang.String)
     */
    public boolean AddTag(String tagtype, String tagvalue) {
        for (Tag temp : tags) {
            if (temp.tagType.equals(tagtype)) {
                temp.AddTag(tagvalue);
                return true;
            }
        }
        tags.add(new Tag(tagtype, tagvalue));
        return true;
    }

    /**
//     * if a photo matches a list of target
//     *
//     * @param comptags the tags to compare with
//     * @param startdate startdatethe date of start
//     * @param enddate the date of end
//     * @return if match, return yes
//     */
    public boolean isMatch(List<Tag> comptags) {
        if (searchTag(comptags) || comptags.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the caption.
     *
     * @return the caption string
     */
    public String getCaption() {
        return caption;
    }

    /**
     * sets the caption
     *
     * @param cap caption input
     */
    public void setCaption(String cap) {
        caption = cap;
    }

    /**
     * set caption of the photo
     *
     * @param c the input string
     */
    public void setcaption(String c) {
        caption = c;

    }

    /**
     * Gets the value of the tag
     *
     * @return the tag values
     */
    public String getTagValues() {
        String temp = "";
        for (Tag tag : tags) {
            temp = temp + tag + " ";
        }
        return temp.trim();
    }


    /**
     * Gets the tag
     *
     * @return the tags of the photo
     */
    public ArrayList<Tag> getTag() {
        return tags;
    }

    @Override
    public int hashCode() {
        return image.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof photo_helper)){
            return false;
        }

        return this.image.equals(((photo_helper) obj).getImage());
    }

    /**
     * get the directory of the photo
     *
     * @return the directory
     */

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
