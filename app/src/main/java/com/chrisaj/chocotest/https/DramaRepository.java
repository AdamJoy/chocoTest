package com.chrisaj.chocotest.https;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.chrisaj.chocotest.R;
import com.chrisaj.chocotest.https.apiresponse.DramaListResponse;
import com.google.android.material.snackbar.Snackbar;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DramaRepository {

    private MutableLiveData<DramaListResponse> mMutableLiveData = new MutableLiveData<>();
    private View mView;

    public DramaRepository() {

    }
    public void setView(View view) {
        mView = view;
    }

    // API:取得戲劇列表
    public MutableLiveData<DramaListResponse> getMutableLiveData() {

        final DramaDataService dramaDataService = RetrofitClient.getService();
        Call<DramaListResponse> call = dramaDataService.getDramaList();

        call.enqueue(new Callback<DramaListResponse>() {
            @Override
            public void onResponse(Call<DramaListResponse> call, Response<DramaListResponse> response) {
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
                // 資料撈取失敗 使用SnackBar提示
                if(mView != null) {
                    Snackbar.make(mView, mView.getContext().getString(R.string.snack_bar_api_error_title),
                                                Snackbar.LENGTH_LONG).setAction(mView.getContext().getString(R.string.snack_bar_confirm),
                            new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).setActionTextColor(Color.RED)
                      .show();
                }
            }
        });
        return mMutableLiveData;
    }
}
