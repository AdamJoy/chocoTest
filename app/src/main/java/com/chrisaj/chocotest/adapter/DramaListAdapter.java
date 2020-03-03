package com.chrisaj.chocotest.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chrisaj.chocotest.R;
import com.chrisaj.chocotest.databinding.ItemDramaListBinding;
import com.chrisaj.chocotest.model.DramaModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DramaListAdapter extends RecyclerView.Adapter<DramaListAdapter.DramaViewHolder> {

    private ArrayList<DramaModel> mDramaList;
    private ItemClick mItemClick;

    public DramaListAdapter(ItemClick itemClick) {
        this.mItemClick = itemClick;   // 外面傳itemClick事件進來
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
        DramaModel dramaData = mDramaList.get(position);

        //if(currentUser.getType().equals("User")) {
        //    currentUser.setOrganize(false);
        //} else {
        //    currentUser.setOrganize(true);
        //}

        holder.dramaListItemBinding.setItemClick(mItemClick);  // 設定ItemClick Listener給xml
        holder.dramaListItemBinding.setDramaModel(dramaData);  // 設定dramaModel給xml
    }

    @Override
    public int getItemCount() {
        if (mDramaList != null) {
            return mDramaList.size();
        } else {
            return 0;
        }
    }
    // 新增多筆Item
    public void setDramaList(ArrayList<DramaModel> dramaList) {
        if(mDramaList == null) {
            mDramaList = new ArrayList<>();
        }
        this.mDramaList = dramaList;
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

    // ViewHolder
    class DramaViewHolder extends RecyclerView.ViewHolder {

        private  ItemDramaListBinding dramaListItemBinding;

        public DramaViewHolder(@NonNull ItemDramaListBinding dramaListItemBinding) {
            super(dramaListItemBinding.getRoot());
            this.dramaListItemBinding = dramaListItemBinding;
        }
        public void bind(ItemClick itemClick) {
            dramaListItemBinding.setItemClick(itemClick);
        }

    }

    public interface ItemClick {
        void onClicked(View view, DramaModel dramaData);
    }
}
