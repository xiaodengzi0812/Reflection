package com.dengzi.reflectiontest.bean;

import android.util.Log;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/8/4.
 * @Version:1.0.0
 */

public class MyView {
    private int id;
    protected String name;
    public int age;

    private void setId(int id) {
        this.id = id;
    }

    protected String getName() {
        return name;
    }

    public void getAge() {
        Log.e("dengzi", "age=" + age);
    }

}
