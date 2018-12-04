package com.dengzi.reflectiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dengzi.reflectiontest.bean.MyTextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "dengzi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 三种获取反射类型的方法
     */
    public Class getClazz() {
        /*获取配置文件信息*/
        Properties pro = new Properties();
        InputStream is = null;
        try {
            is = getAssets().open("test.properties");
            pro.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String clazzName = pro.get("className").toString();
        // String clazzName = "com.dengzi.reflectiontest.bean.MyTextView";

        /*第一种*/
        Class clazz1 = null;
        try {
            clazz1 = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
        }

        /*第二种*/
        // Class clazz2 = MyTextView.class;

        /*第三种*/
        // MyTextView myTextView = new MyTextView();
        // Class clazz3 = myTextView.getClass();

        return clazz1;
    }

    /**
     * 构造测试类
     *
     * @param view
     * @throws Exception
     */
    public void test1(View view) throws Exception {

        // 获取 "public MyTextView(int width)" 这个构造器
        Constructor con = getClazz().getDeclaredConstructor(int.class);
        // 通过 "public MyTextView(int width)" 来创建MyTextView
        MyTextView mtv = (MyTextView) con.newInstance(12);


        // 获取所有的构造器
        Constructor[] Constructors = getClazz().getDeclaredConstructors();
        for (Constructor constructor : Constructors) {
            constructor.setAccessible(true);
            // 获取构造函数对应的参数类型
            Class<?>[] types = constructor.getParameterTypes();
            String logTemp = constructor.getName() + "(";
            if (types != null) {
                for (int i = 0; i < types.length; i++) {
                    if (i < types.length - 1) {
                        logTemp = logTemp + types[i].toString() + ",";
                    } else {
                        logTemp = logTemp + types[i].toString();
                    }
                }
            }
            Log.e(TAG, "构造函数： " + logTemp + ")");
            Log.e(TAG, "-------------------------");
        }


        /* 输出示例：
                构造函数： com.dengzi.reflectiontest.bean.MyTextView()
                -------------------------
                构造函数： com.dengzi.reflectiontest.bean.MyTextView(float,class java.lang.String)
                -------------------------
                构造函数： com.dengzi.reflectiontest.bean.MyTextView(int)
                -------------------------
        * */
    }

    /**
     * 属性测试类
     *
     * @param view
     * @throws Exception
     */
    public void test2(View view) throws Exception {
        // 获取某个属性
        Field titleField = getClazz().getDeclaredField("title");
        Log.e(TAG, titleField.getName());

        // 获取所有的属性
        Field[] fields = getClazz().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Log.e(TAG, field.getName());
            Log.e(TAG, field.getGenericType().toString());

            Log.e(TAG, "-------------------------");
        }
    }

    /**
     * 方法测试类
     *
     * @param view
     * @throws Exception
     */
    public void test3(View view) throws Exception {
        // 获取某个方法
        Method method = getClazz().getDeclaredMethod("setWidth", int.class);
        Log.e(TAG, method.getName() + "-->" + method.getParameterTypes()[0].toString());

        Object obj = getClazz().newInstance();
        method.setAccessible(true);
        method.invoke(obj, 11);
        Field widthField = obj.getClass().getDeclaredField("width");
        widthField.setAccessible(true);
        widthField.set(obj, 13);
        Object widthObj = widthField.get(obj);
        Log.e(TAG, widthObj.toString());

//        Method[] methods = getClazz().getMethods();// 获取所有的共有方法的集合(包含父类的方法)
//        for (Method method1 : methods) {
//            method1.setAccessible(true);
//            Log.e(TAG, method1.getName());
//        }

//        Method[] methods = getClazz().getDeclaredMethods();// 获取所有的方法(不包含父类的方法)
//        for (Method method1 : methods) {
//            method1.setAccessible(true);
//            Log.e(TAG, method1.getName());
//        }
    }
}
