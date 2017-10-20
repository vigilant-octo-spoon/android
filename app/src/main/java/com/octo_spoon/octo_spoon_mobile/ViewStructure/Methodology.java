package com.octo_spoon.octo_spoon_mobile.ViewStructure;

/**
 * Created by Pablo on 19-10-2017.
 */

public class Methodology {

    private int id;
    private String title;
    private String description;
    private String organization;
    private String category;
    private String video_link;

    public Methodology(int _id, String _title, String _description, String _organization, String _category, String _video_link) {
        this.id = _id;
        this.title = _title;
        this.description = _description;
        this.organization = _organization;
        this.category = _category;
        this.video_link = _video_link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

}
