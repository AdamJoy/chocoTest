package com.chrisaj.chocotest.https;

import com.chrisaj.chocotest.https.apiresponse.DramaListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 *  Retrofit API連結函數主頁
 */
public interface DramaDataService {

    @GET("interview/dramas-sample.json")
    Call<DramaListResponse> getDramaList(); // Retrofit 取得 Drama函數
}
