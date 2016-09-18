package com.example.administrator.myapplication17;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final static String SharedPreferencesFileName="config";

    private final static String Key_UserName="UserName";
    private final static String Key_LoginDate="LoginDate";
    private final static String Key_UserType="UserType";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private Button write;
    private Button read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(SharedPreferencesFileName, MODE_PRIVATE);
        editor = preferences.edit();

        write = (Button)findViewById(R.id.btn_write);
        read = (Button)findViewById(R.id.btn_read);

        write.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strLoginDate = simpleDateFormat.format(new Date());

                editor.putString(Key_UserName, "liyang");
                editor.putString(Key_LoginDate, strLoginDate);
                editor.putString(Key_UserType, "1");

                editor.apply();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = preferences.getString(Key_UserName, null);
                String date = preferences.getString(Key_LoginDate, null);
                String type = preferences.getString(Key_UserType, "0");

                if(name != null && date != null){
                    Toast.makeText(MainActivity.this,
                            "用户名:" + name + "\n" + "日期:" + date,
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,
                            "无数据",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}