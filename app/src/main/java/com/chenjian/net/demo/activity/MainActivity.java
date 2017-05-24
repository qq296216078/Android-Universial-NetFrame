package com.chenjian.net.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chenjian.net.R;
import com.chenjian.net.demo.activity2.CustomActivity2;
import com.chenjian.net.demo.activity2.GetActivity2;
import com.chenjian.net.demo.activity2.OtherJsonActivity2;
import com.chenjian.net.demo.activity2.PostActivity2;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GetActivity2.class));
            }
        });

        findViewById(R.id.btn_main_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostActivity2.class));
            }
        });

        findViewById(R.id.btn_main_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomActivity2.class));
            }
        });

        findViewById(R.id.btn_main_otherjson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OtherJsonActivity2.class));
            }
        });
    }
}
