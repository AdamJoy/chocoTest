package com.chrisaj.chocotest.https;

import android.util.Log;

import com.chrisaj.chocotest.https.apiresponse.DramaListResponse;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DramaRepository {

    private MutableLiveData<DramaListResponse> mMutableLiveData = new MutableLiveData<>();

    public DramaRepository() {

    }

    // API:取得戲劇列表
    public MutableLiveData<DramaListResponse> getMutableLiveData() {

        final DramaDataService dramaDataService = RetrofitClient.getService();
        Call<DramaListResponse> call = dramaDataService.getDramaList();

        call.enqueue(new Callback<DramaListResponse>() {
            @Override
            public void onResponse(Call<DramaListResponse> call, Response<DramaListResponse> response) {
                //Log.d("TAG","Api__細綠列表結果" + new Gson().toJson(response));
                DramaListResponse dramaListApiResponse = response.body();
                if (dramaListApiResponse != null) {
                    Log.d("TAG","戲劇列表API___長度 = " + dramaListApiResponse.getDramaList().size());
                    mMutableLiveData.setValue(dramaListApiResponse);      // setValue後 呼叫MainActivity的 onChanged
                }
            }
            @Override
            public void onFailure(Call<DramaListResponse> call, Throwable t) {
                Log.d("TAG","API__結果__error = " +  t.getMessage());
                call.cancel();
            }
        });
        return mMutableLiveData;
    }
}
