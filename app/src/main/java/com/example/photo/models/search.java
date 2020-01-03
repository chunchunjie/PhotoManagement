package com.example.photo.models;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * CS 213 Photo Project
 *
 * Search page
 *
 * @author Xiao Yan & Chunjie Yang
 */


public interface search {
    /**
     * to seach a bunch of photos that matches with the target date and tags
     *
     * @param tags the tags list

     * @return all photos matched
     */
    photo_helper[] searchPhotos(List<Tag> tags);


    /**
     * to do the and operations of search, this need to be different from the or operations since
     * the logic is different
     *
     * @param newtag the tag to check photos for
     * @param startdate date of the start
     * @param enddate date of end
     * @return all photos and matched
     */
    public photo_helper[] andPhotos(Tag newtag, Calendar startdate, Calendar enddate);

    /**
     * to do the or operations of searching based on user's choice
     *
     * @param newtag the tag to check photos for
     * @param startdate date of the start
     * @param enddate date of end
     * @return all photos or matched
     */
    public photo_helper[] orPhotos(Tag newtag, Calendar startdate, Calendar enddate);

}
