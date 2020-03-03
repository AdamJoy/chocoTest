package com.chrisaj.chocotest;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.chrisaj.chocotest.adapter.DramaListAdapter;
import com.chrisaj.chocotest.databinding.ActivityMainBinding;
import com.chrisaj.chocotest.https.apiresponse.DramaListResponse;
import com.chrisaj.chocotest.model.DramaModel;
import com.chrisaj.chocotest.viewmodel.MainActivityViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    //Binding物件
    private ActivityMainBinding mActivityMainBinding;
    private MainActivityViewModel mViewModel;

    private DramaListAdapter mDramaListAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //取得Binding實體
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mRecyclerView = mActivityMainBinding.rvDramaList;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);


        mDramaListAdapter = new DramaListAdapter(this);
        mRecyclerView.setAdapter(mDramaListAdapter);

        setListener();  // UI Listener
        getDramaList(); // 呼叫API
    }

    private void getDramaList() {
        // API 返回資料
        final Observer<DramaListResponse> observer = new Observer<DramaListResponse>() {
            @Override
            public void onChanged(DramaListResponse dramaModels) {
                Log.d("TAG","MainAcrtivity__onChange");
                //透過Binding將值設定給Recyclerview
                mDramaListAdapter.setDramaList(dramaModels.getDramaList());
            }
        };
        mViewModel.getDramaListData().observe(this, observer);   // 開始呼叫API
    }

    private void setListener() {
        // EditText搜尋事件
        mActivityMainBinding.etDramaListSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable != null ) {
                    mDramaListAdapter.searchDramaByKeyWord(editable.toString());
                }
            }
        });

        // 列表Item click Listener
        DramaListAdapter.ItemClick itemClick = new DramaListAdapter.ItemClick() {
            @Override
            public void onClicked(View view, DramaModel dramaData) {
                   Log.d("TAG","戲劇列表點擊事件 = " + dramaData.getName());
            }
        };
        mDramaListAdapter.setItemClick(itemClick);
    }
}
