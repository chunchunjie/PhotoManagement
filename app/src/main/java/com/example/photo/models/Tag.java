package com.example.photo.models;

/**
 * CS 213 Photo Project
 *
 * Tag page
 *
 * @author Xiao Yan & Chunjie Yang
 */
import java.io.*;
// TODO: Auto-generated Javadoc
//import java.awt.List;
//import java.util.ArrayList;

/**
 * The Abstract Class Tag. All types of tags to be used by the program are inherently Tag.
 */
@SuppressWarnings("serial")
public class Tag implements Serializable{

    /** The type of tag. */
    public String tagType;

    /** The value of tag. */
    public String tagValue;
    /**
     * Gets the tag type.
     *
     * @return the tag type
     */
    //ArrayList tagvalues=new ArrayList<String>();
    public String getTagType()
    {
        return tagType;
    }

    /**
     * Tag Constructor
     * @param type type of the tag
     * @param value value of the tag
     */
    public Tag(String type, String value){
        this.tagType = type;
        this.tagValue = value;
    }

    /**
     * Search tag for a keyword and see if it is present in tag values.
     *\
     *
     * @param keyword the keyword to look for
     * @return true, if present in tag
     */
    boolean contains(String keyword) {
        return keyword.equals(tagValue);
    };

    /**
     * Search tag for keywords and see if any are present in tag values.
     *
     * @param keywords the keywords to look for
     * @return true, if any are present in tag
     */
    boolean contains(String[] keywords) {
        for (int i=0;i<keywords.length;i++)
        {
            if (tagValue.equals(keywords[i]))
                return true;
        }
        return false;
    }

    /**
     * Adds a value to a tag (overwriting the current value if the tag can only hold one value).
     *
     * @param keyword the value of the tag
     */
    void AddTag(String keyword) {
        tagValue=keyword;
    }


    /**
     * Gets the tag values.
     *
     * @return the tag values
     */
    String[] getTagValues(){
        String[] temp= {tagValue};
        return temp;
    }

    @Override
    public String toString() {
        return tagType + " : " + tagValue;
    }
}
