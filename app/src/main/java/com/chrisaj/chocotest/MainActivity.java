package com.chrisaj.chocotest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chrisaj.chocotest.adapter.DramaListAdapter;
import com.chrisaj.chocotest.databinding.ActivityMainBinding;
import com.chrisaj.chocotest.https.apiresponse.DramaListResponse;
import com.chrisaj.chocotest.model.DramaModel;
import com.chrisaj.chocotest.tool.DramaSP;
import com.chrisaj.chocotest.tool.Key;
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


        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        Log.d("TAG","設定View給ViewModel = " + viewGroup);
        mViewModel.setView(viewGroup);

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
                Log.d("TAG","MainActivity__onChange");
                //透過Binding將值設定給Recyclerview
                mDramaListAdapter.setDramaList(dramaModels.getDramaList());
                // 回覆上次搜尋狀態
                restoreLastTimeSearchStatus(DramaSP.getInstances().getString(Key.KEY_SP_SEARCH_DRAMA_RESULT, ""));
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
                    // 搜尋結果暫存至 SP

                    DramaSP.getInstances().setString(Key.KEY_SP_SEARCH_DRAMA_RESULT, editable.toString());
                }
            }
        });

        // 列表Item click Listener
        DramaListAdapter.ItemClick itemClick = new DramaListAdapter.ItemClick() {
            @Override
            public void onClicked(View view, DramaModel dramaData) {
                   Log.d("TAG","戲劇列表點擊事件 = " + dramaData.getName());
                   Intent intent = new Intent(MainActivity.this, DramaDetailActivity.class);
                   intent.putExtra(Key.KEY_BUNDLE_DRAMA_MODEL, dramaData);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);
            }
        };
        mDramaListAdapter.setItemClick(itemClick);
    }

    private void restoreLastTimeSearchStatus(final String searchedRecord) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivityMainBinding.etDramaListSearch.setText(searchedRecord);
            }
        }, 200);

    }
}
