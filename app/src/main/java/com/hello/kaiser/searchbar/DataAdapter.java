package com.hello.kaiser.searchbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


class DataAdapter extends RecyclerView.Adapter<DataAdapter.dataHolder> implements Filterable {

    private final static String TAG = DataAdapter.class.getSimpleName();

    //元件
    private Context context;
    private ArrayList<FruitsData> datalists;
    private ArrayList<FruitsData> filterDatas;
    private MyFliter myFliter;//讓mainActivity能使用adapter呼叫篩選功能

    //建構子
    public DataAdapter(Context context) {
        this.context = context;
    }

    //implements Filterable的必定覆寫的方法，讓我們在activity內能呼叫他
    @Override
    public Filter getFilter() {
        if (myFliter == null) {
            myFliter = new MyFliter();
        }
        return myFliter;
    }

    class dataHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public dataHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }

    //讓我們在activity內能讀取或更新adapter內data的資料
    public void setData(ArrayList<FruitsData> datalists) {
        this.datalists = datalists;
        filterDatas = datalists;
        notifyDataSetChanged();
    }

    @Override
    public DataAdapter.dataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        dataHolder dh = new dataHolder(view);
        return dh;
    }

    @Override
    public void onBindViewHolder(DataAdapter.dataHolder holder, int position) {
        Glide.with(context)
                .load(datalists.get(position).getImageUrl())
                .placeholder(R.drawable.default_image)
                .into(holder.imageView);

        if (!TextUtils.isEmpty(datalists.get(position).getTitle()))
            holder.textView.setText(datalists.get(position).getTitle());
        else
            holder.textView.setText("暫無資料");
    }

    @Override
    public int getItemCount() {
        return datalists == null ? 0 : datalists.size();
    }

    //實作自己的篩選Filter
    class MyFliter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence filterContent) {
            ArrayList<FruitsData> filterData = new ArrayList<>();
            //先判斷filterContent是不是null才進入
            if (filterContent != null && filterContent.toString().trim().length() > 0) {
                Log.d(TAG, "確認是否為空值。 D.size = " + filterData.size());
                //這回圈是在判斷你輸入的文字（filterContent）是否有在filterDatas陣列內有相關的文字，逐條搜尋。
                for (int i = 0; i < filterDatas.size(); i++) {
                    String content = filterDatas.get(i).getTitle();
                    Log.d(TAG, "確認是否進入for迴圈 content = " + content);
                    if (content.contains(String.valueOf(filterContent))) {
                        Log.d(TAG, "確認輸入文字是否相同。");
                        FruitsData data = new FruitsData();
                        data.setImageUrl(filterDatas.get(i).getImageUrl());
                        data.setTitle(filterDatas.get(i).getTitle());
                        filterData.add(data);
                    }
                }
            } else {
                filterData = filterDatas;
                Log.d(TAG, "確認什麼都沒打 filterDatas = datalists = " + filterData.size());

            }
            FilterResults filterResults = new FilterResults();
            filterResults.count = filterData.size();
            filterResults.values = filterData;
            Log.d(TAG, "final size = " + filterResults.count);
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            datalists = (ArrayList<FruitsData>) filterResults.values;
            if (filterResults.count > 0) {
                notifyDataSetChanged();
            } else {
                FruitsData data = new FruitsData();
                data.setTitle("沒有結果");
                datalists.add(data);
                notifyDataSetChanged();
            }
        }
    }
}
