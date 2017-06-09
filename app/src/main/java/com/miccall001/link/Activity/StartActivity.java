package com.miccall001.link.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.miccall001.link.R;
import com.stylingandroid.prism.Prism;
import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.TintFilter;

/**
 *
 * Created by miccall on 2017/6/2 0002.
 */

public class StartActivity extends AppCompatActivity{

    private Button bt_setting;
    private Button bt_start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_hello);

        initUI();
        themeChange();
    }

    private void loadAnim(View v) {
        AnimationSet as = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.startanim);
        as.setDuration(2000);
        v.startAnimation(as);
    }

    @SuppressLint("ResourceAsColor")
    private void themeChange() {
        Filter tint = new TintFilter(TRIM_MEMORY_BACKGROUND);
        Prism prism = Prism.Builder.newInstance()
                .background(bt_setting,tint)
                .background(bt_start,tint)
                .color(bt_start)
                .color(bt_setting)
                .build();

        prism.setColor(getResources().getColor(R.color.color_a));
        Log.d("color","int+"+Color.RED);
        Log.d("color","int"+R.color.md_red_900);

    }

    private void initUI() {
        bt_setting = (Button) findViewById(R.id.setting);
        bt_start = (Button) findViewById(R.id.start);
        loadAnim(bt_setting);
        loadAnim(bt_start);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private Context getContext()
    {
        return getApplicationContext();
    }


}
