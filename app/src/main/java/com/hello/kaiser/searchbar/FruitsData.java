package com.hello.kaiser.searchbar;

/**
 * Created by kaiser on 2017/7/17.
 */

public class FruitsData {
    private String title;
    private String imageUrl;

//    public FruitsData(String title, String imageUrl) {
//        this.title = title;
//        this.imageUrl = imageUrl;
//        Log.d("ItemData", "CheckPoint imageUrl=" + imageUrl);
//    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
