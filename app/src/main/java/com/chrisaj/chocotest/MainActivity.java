package com.chrisaj.chocotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.chrisaj.chocotest.databinding.ActivityMainBinding;
import com.chrisaj.chocotest.https.apiresponse.DramaListResponse;
import com.chrisaj.chocotest.model.DramaModel;
import com.chrisaj.chocotest.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Binding物件
    private ActivityMainBinding mActivityMainBinding;
    private MainActivityViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得Binding實體
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // 呼叫API
        getDramaList();
    }

    private void getDramaList() {
        final Observer<DramaListResponse> observer = new Observer<DramaListResponse>() {
            @Override
            public void onChanged(DramaListResponse dramaModels) {
                Log.d("TAG","MainAcrtivity__onChange");
                //透過Binding將值設定給Recyclerview
            }
        };
        mViewModel.getDramaListData().observe(this, observer);   // 開始呼叫API
    }
}
