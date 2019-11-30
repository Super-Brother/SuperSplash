package com.wenchao.supersplash;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class SplashActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParallaxContainer container = findViewById(R.id.parallax_container);
        container.setUp(new int[]{
                R.layout.view_intro_1,
                R.layout.view_intro_2,
                R.layout.view_intro_3,
                R.layout.view_intro_4,
                R.layout.view_intro_5,
                R.layout.view_login
        });
        ImageView iv_man = (ImageView) findViewById(R.id.iv_man);
        iv_man.setBackgroundResource(R.drawable.man_run);
        container.setIv_man(iv_man);
    }
}
