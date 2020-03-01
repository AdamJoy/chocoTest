package com.chrisaj.chocotest.https.apiresponse;

import com.chrisaj.chocotest.model.DramaModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DramaListResponse {

    @SerializedName("data")
    private ArrayList<DramaModel> dramaList;

    public ArrayList<DramaModel> getDramaList() {
        return dramaList;
    }

    public void setDramaList(ArrayList<DramaModel> data) {
        this.dramaList = data;
    }
}
