package com.hello.kaiser.searchbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    //元件
    private android.support.v7.widget.SearchView searchView;
    private RecyclerView recyclerView;
    private ArrayList<FruitsData> datalists = new ArrayList<>();
    private String[] urlList = null;
    private String[] titleList = null;
    private DataAdapter dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSet();
    }

    /**
     *  這方法在activity所有畫面都完成之後，才會被call。
     *  之前我做的專案，有很多Crash案例都是因為畫面還沒完成，點擊事件就先放行，點擊後導致Crash。
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initListener();
    }

    /**
     *  init畫面
     */
    private void initView() {
        searchView = (android.support.v7.widget.SearchView) findViewById(R.id.search_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    /**
     *  init設定
     */
    private void initSet() {
        //RecyclerView設定
        dataAdapter = new DataAdapter(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(false); //是否要點選搜尋圖示後再打開輸入框
        searchView.setFocusable(false);
        searchView.requestFocusFromTouch();      //要點選後才會開啟鍵盤輸入
        searchView.setSubmitButtonEnabled(false);//輸入框後是否要加上送出的按鈕
        searchView.setQueryHint("請輸入android搜尋"); //輸入框沒有值時要顯示的提示文字

        Toast.makeText(this,searchView.getQuery().toString(),Toast.LENGTH_LONG);

        //假資料設定
        urlList = new String[]{
                "http://www.jrtstudio.com/sites/default/files/ico_android.png",
                "https://developer.android.com/_static/images/android/touchicon-180.png",
                "http://cdn.bgr.com/2012/11/android-icon.jpg?quality=98&strip=all",
                "http://www.fastbooting.com/uploads/5/5/9/9/55994511/1474140_orig.png",
                "http://www.pendidikan-diy.go.id/dinas_v4/img/up_down/Android.png",
                "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
                "http://zdnet2.cbsistatic.com/hub/i/2016/04/22/a1ced73f-6628-4930-96f4-d224e1d3a707/8a43709ccee674396403ee99472e38f3/android-security-1.jpg",
                "https://unwire.pro/wp-content/uploads/2017/05/android-kotlin.png",
                "http://www.jrtstudio.com/sites/default/files/ico_android.png",
                "https://developer.android.com/_static/images/android/touchicon-180.png",
                "http://cdn.bgr.com/2012/11/android-icon.jpg?quality=98&strip=all",
                "http://www.fastbooting.com/uploads/5/5/9/9/55994511/1474140_orig.png",
                "http://www.pendidikan-diy.go.id/dinas_v4/img/up_down/Android.png",
                "https://www.android.com/static/2016/img/one/a1_andy_1x.png",
                "http://zdnet2.cbsistatic.com/hub/i/2016/04/22/a1ced73f-6628-4930-96f4-d224e1d3a707/8a43709ccee674396403ee99472e38f3/android-security-1.jpg",
                "https://unwire.pro/wp-content/uploads/2017/05/android-kotlin.png",
        };
        titleList = new String[]{
                "手機：iphone7",
                "手機殼：犀牛盾",
                "手機：htc",
                "電腦：戴爾",
                "手機：samsung",
                "電腦：hp惠普",
                "電腦：惠普",
                "電腦：蘋果電腦apple",
                "手機：sony",
                "手機：samsung s8",
                "手機：asus",
                "電腦：Aser宏碁電腦",
                "手機、電腦：微軟",
                "手機：oppo 蕩漾款",
                "手機：小米",
                "電腦：聯想",
        };

        //兩筆假資料size()一樣才進入迴圈
        if (titleList.length == urlList.length)
            for (int i = 0; i < (urlList.length); i++) {
                FruitsData data = new FruitsData();//製作單筆資料
                data.setTitle(titleList[i]);
                data.setImageUrl(urlList[i]);
                datalists.add(data);//把單筆資料加入陣列
            }

        dataAdapter.setData(datalists);//把陣列塞入adapter
        recyclerView.setAdapter(dataAdapter);//把adapter塞入recyclerview
    }


    /**
     *  點擊事件
     */
    private void initListener() {

    }

    /**
     *  複寫implements SearchView.OnQueryTextListener 的方法
     */
    @Override
    public boolean onQueryTextSubmit(final String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        //塞選 我們寫在adapter內
        dataAdapter.getFilter().filter(newText);
        return false;
    }

}
