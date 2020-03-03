package com.chrisaj.chocotest;

import android.os.Bundle;

import com.chrisaj.chocotest.databinding.ActivityDramaDetailBinding;
import com.chrisaj.chocotest.model.DramaModel;
import com.chrisaj.chocotest.tool.Key;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class DramaDetailActivity extends AppCompatActivity {

    private ActivityDramaDetailBinding mActivityDramaDetailBinding;

    private DramaModel mDramaDetailData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 取得dataBinding
        mActivityDramaDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_drama_detail);

        // 取得相關資料 DramaModel Serializable
        if(getIntent() != null) {
            mDramaDetailData = (DramaModel) getIntent().getSerializableExtra(Key.KEY_BUNDLE_DRAMA_MODEL);
        }
        // 呈現資料於畫面
        mActivityDramaDetailBinding.setDramaDetailModel(mDramaDetailData);
    }
}
