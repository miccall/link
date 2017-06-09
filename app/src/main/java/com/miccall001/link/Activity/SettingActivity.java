package com.miccall001.link.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.miccall001.link.R;
import com.miccall001.link.Utils.SharedPreferencesUtils;

/**
 *
 * Created by miccall on 2017/6/2 0002.
 */

public class SettingActivity extends AppCompatActivity {

    private Button OK_bt;
    private EditText ip_et;
    private  String HOST;
    private NumberPicker Np1,Np2,Np3,Np4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_view);

        //初始化UI
        initUI();

        //绑定监听
        myOnclickLinstener myOnclickLinstener = new myOnclickLinstener();
        OK_bt.setOnClickListener(myOnclickLinstener);

    }

    private void initUI() {
        OK_bt = (Button) findViewById(R.id.bt);
        ip_et = (EditText) findViewById(R.id.ip);
        Np1 = (NumberPicker) findViewById(R.id.np1);
        Np2 = (NumberPicker) findViewById(R.id.np2);
        Np3 = (NumberPicker) findViewById(R.id.np3);
        Np4 = (NumberPicker) findViewById(R.id.np4);

        Np1.setMaxValue(254);
        Np1.setMinValue(0);
        Np1.setValue(192);

        Np2.setMaxValue(254);
        Np2.setMinValue(0);
        Np2.setValue(168);

        Np3.setMaxValue(254);
        Np3.setMinValue(0);
        Np3.setValue(1);

        Np4.setMaxValue(254);
        Np4.setMinValue(0);
        Np4.setValue(155);

        //设置回显
        ip_et.setText( (String) SharedPreferencesUtils.getParam(getApplicationContext(),"HOST","192.168.115"));

    }

    private class myOnclickLinstener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId()==OK_bt.getId())
            {
                HOST = String.valueOf(ip_et.getText());

                //TODO: Host trim

                SharedPreferencesUtils.setParam(getcontext(), "HOST", HOST);
            }
        }
    }





    public Context getcontext()
    {
        return getApplicationContext();
    }
}
