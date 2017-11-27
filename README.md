### 效果图
![img](https://raw.githubusercontent.com/android-notes/ProportionView/dev/proportion.gif)


### 使用方式

```html


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_horizontal"
    android:orientation="vertical">


    <com.wanjian.view.ProportionView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#e1e1"
        android:padding="10dp"
        app:proportion="0.5">


        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_margin="10dp"
            android:background="#e1e"
            android:orientation="vertical">

            <EditText
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="1234d11111fdfdfdf11" />

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:src="@mipmap/ic_launcher" />

        </LinearLayout>

    </com.wanjian.view.ProportionView>


    <com.wanjian.view.ProportionView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20px"
        android:background="#e1e1"
        android:padding="20px"
        app:proportion="1">


        <EditText
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#e1e1e1"
            android:text="1ddddgg1" />


    </com.wanjian.view.ProportionView>

    <LinearLayout
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:layout_marginTop="20px"
        android:background="#e1e1e1">

        <com.wanjian.view.ProportionView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#e1e1"
            android:padding="20px"
            app:proportion="0.618">


            <EditText
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:background="#e1e1e1"
                android:text="1ffffffffffd" />


        </com.wanjian.view.ProportionView>
    </LinearLayout>

</LinearLayout>


```