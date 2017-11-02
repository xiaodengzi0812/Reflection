package com.dengzi.reflectiontest.bean;

import android.util.Log;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/8/4.
 * @Version:1.0.0
 */

public class MyTextView extends MyView {

    private static final String TAG = "dengzi";

    // -------------属性-------------
    private int width;
    protected float height = 250;
    public String title;

    // -------------构造器-------------
    public MyTextView() {
        Log.e(TAG, "MyTextView()");
    }

    public MyTextView(int width) {
        this.width = width;
        Log.e(TAG, "MyTextView(int width)");
    }

    public MyTextView(float height, String title) {
        this.height = height;
        this.title = title;
        Log.e(TAG, "MyTextView(float height, String title)");
    }

    // -------------方法-------------
    private void setWidth(int width) {
        this.width = width;
    }

    protected float getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
