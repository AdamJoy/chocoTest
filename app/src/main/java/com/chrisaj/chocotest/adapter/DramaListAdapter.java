package com.chrisaj.chocotest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import com.chrisaj.chocotest.R;
import com.chrisaj.chocotest.databinding.ItemDramaListBinding;
import com.chrisaj.chocotest.model.DramaModel;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DramaListAdapter extends RecyclerView.Adapter<DramaListAdapter.DramaViewHolder> {

    private ArrayList<DramaModel> mDramaList;
    private ArrayList<DramaModel> mDramaSearchResultList;
    private ItemClick mItemClick;
    private Context mContext;

    public DramaListAdapter(Context context) {
        this.mContext = context;
        //this.mItemClick = itemClick;   // 外面傳itemClick事件進來
        this.mDramaSearchResultList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DramaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemDramaListBinding dramaListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),    // ItemDramaListBinding會自動生成
                        R.layout.item_drama_list, viewGroup, false);       // 根據Layout名子  item_drama_list 自動產生 ItemUserListBinding

        return new DramaViewHolder(dramaListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DramaViewHolder holder, int position) {
        DramaModel dramaData;
        if(mDramaSearchResultList.size() > 0) {
            dramaData = mDramaSearchResultList.get(position);
        } else {
            dramaData = mDramaList.get(position);
        }
        holder.dramaListItemBinding.setItemClick(mItemClick);  // 設定ItemClick Listener給xml
        holder.dramaListItemBinding.setDramaModel(dramaData);  // 設定dramaModel給xml
    }

    @Override
    public int getItemCount() {
        if(mDramaSearchResultList != null && mDramaSearchResultList.size() > 0 ) {
            return mDramaSearchResultList.size();
        } else {
            if (mDramaList != null) {
                return mDramaList.size();
            } else {
                return 0;
            }
        }
    }
    // 新增多筆Item
    public void setDramaList(ArrayList<DramaModel> dramaList) {
        if(mDramaList == null) {
            mDramaList = new ArrayList<>();
        }
        if(mDramaSearchResultList == null) {
            mDramaSearchResultList = new ArrayList<>();
        }
        this.mDramaList.addAll(dramaList);
        notifyDataSetChanged();
    }

    // 插入多筆Item
    public void addUserList(ArrayList<DramaModel> dramaList) {
        mDramaList.addAll(dramaList);
        Log.d("TAG","插入資料數量 = " + dramaList.size());
        Log.d("TAG","插入資料數量_總數量 = " + mDramaList.size());
        notifyDataSetChanged();
    }
    // 增加單一Item
    public void addSingleDramaItem(DramaModel dramaItem) {
        if(mDramaList != null) {
            mDramaList.add(dramaItem);
        }
    }
    // 取得目前最後一筆Drama_Id
    public int getLastUserId() {
        if(mDramaList != null) {
            return mDramaList.get(mDramaList.size() - 1).getDrama_id();
        }
        return 0;
    }

    // Drama 搜尋處理
    public void searchDramaByKeyWord(String keyword) {
        if(mDramaSearchResultList == null) {
            mDramaSearchResultList = new ArrayList<>();
        }

        mDramaSearchResultList.clear();
        if(keyword.length() == 0) {
            notifyDataSetChanged();
            return;
        }
        for(int i = 0 ; i < mDramaList.size(); i++) {
            if(mDramaList.get(i).getName().contains(keyword)) {
                mDramaSearchResultList.add(mDramaList.get(i));
            }
        }
        notifyDataSetChanged();
    }

    // ViewHolder
    class DramaViewHolder extends RecyclerView.ViewHolder {

        private ItemDramaListBinding dramaListItemBinding;

        public DramaViewHolder(@NonNull ItemDramaListBinding dramaListItemBinding) {
            super(dramaListItemBinding.getRoot());
            this.dramaListItemBinding = dramaListItemBinding;
        }
        public void bind(ItemClick itemClick) {
            dramaListItemBinding.setItemClick(itemClick);
        }

    }

    public void setItemClick(ItemClick itemclick) {
        this.mItemClick = itemclick;
    }
    public interface ItemClick {
        void onClicked(View view, DramaModel dramaData);
    }
}
