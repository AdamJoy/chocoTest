package com.chrisaj.chocotest.model;

public class DramaModel {

    /**
     * drama_id : 3
     * name : 如果有妹妹就好了
     * total_views : 2931180
     * created_at : 2017-10-21T12:34:41.000Z
     * thumb : https://i.pinimg.com/originals/32/c1/7a/32c17af1c085be75657e965954f8d601.jpg
     * rating : 4.0647
     */

    private int drama_id;
    private String name;
    private int total_views;
    private String created_at;
    private String thumb;
    private double rating;

    public int getDrama_id() {
        return drama_id;
    }

    public void setDrama_id(int drama_id) {
        this.drama_id = drama_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal_views() {
        return total_views;
    }

    public void setTotal_views(int total_views) {
        this.total_views = total_views;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
