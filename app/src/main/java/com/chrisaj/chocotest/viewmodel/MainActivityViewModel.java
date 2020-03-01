package com.chrisaj.chocotest.viewmodel;

import android.app.Application;

import com.chrisaj.chocotest.https.DramaRepository;
import com.chrisaj.chocotest.https.apiresponse.DramaListResponse;
import com.chrisaj.chocotest.model.DramaModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private DramaRepository mDramaRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mDramaRepository = new DramaRepository();
    }

    public LiveData<DramaListResponse> getDramaListData() {
        return mDramaRepository.getMutableLiveData();
    }

}
