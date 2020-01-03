package com.example.photo.models;

/**
 * CS 213 Photo Project
 *
 * Tags page
 *
 * @author Xiao Yan & Chunjie Yang
 */

import com.example.photo.models.Tag;
/**
 * The Interface Tags. To be used with any model object that utilizes and manipulates multiple Tag objects.
 */
public interface Tags {

    /**
     * Adds a tag.
     * @param tagtype the type of tag
     * @param tagvalue the value of tag
     * @return true if successful
     */
    boolean AddTag(String tagtype,String tagvalue);

    /**
     * Removes the tag.
     *
     * @param tagtype the tagtype
     */
    void removeTag(String tagtype);

    /**
     * Gets all tags.
     *
     * @return the tags
     */
    Tag[] getTags();
}
