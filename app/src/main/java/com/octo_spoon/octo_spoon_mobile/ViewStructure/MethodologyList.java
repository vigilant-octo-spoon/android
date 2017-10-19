package com.octo_spoon.octo_spoon_mobile.ViewStructure;

/**
 * Created by Pablo on 19-10-2017.
 */

public class MethodologyList {

    public int id;
    public String title;
    public String description;
    public String organization;
    public String category;
    public String video_link;

    public MethodologyList(int _id, String _title, String _description, String _organization, String _category, String _video_link) {
        this.id = _id;
        this.title = _title;
        this.description = _description;
        this.organization = _organization;
        this.category = _category;
        this.video_link = _video_link;
    }
}
