package com.chrisaj.chocotest.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.chrisaj.chocotest.https.DramaRepository;
import com.chrisaj.chocotest.https.apiresponse.DramaListResponse;
import com.chrisaj.chocotest.model.DramaModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private DramaRepository mDramaRepository;
    private View mView;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mDramaRepository = new DramaRepository();
    }

    public LiveData<DramaListResponse> getDramaListData() {
        return mDramaRepository.getMutableLiveData();
    }

    public void setView(View view) {
        mView = view;
        mDramaRepository.setView(view);
    }
    public View getView() {
        return mView;
    }

}
