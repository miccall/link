package com.miccall001.link.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miccall001.link.R;
import com.miccall001.link.Utils.SharedPreferencesUtils;
import com.miccall001.link.Utils.UDPtools;

import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    UDPtools udPtools ;
    // 发送数据缓存
    private String CONTENT = "";
    // UI
    private TextView text;
    private Button OK_bt;
    private EditText port_et;
    private EditText ip_et;
    private InetAddress serverAddress;
    private String host;
    private int port;
    private FloatingActionButton fab;


    @SuppressLint("ClickableViewAccessibility")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init UI
        initUI();
        initData();

        //执行坐标数据发送

        MyOnTouchListener myOnTouchListener = new MyOnTouchListener();
        text.setOnTouchListener(myOnTouchListener);
        fab.setOnTouchListener(myOnTouchListener);

    }

    private void initData() {
        port = 5200;
        host = (String) SharedPreferencesUtils.getParam(getApplicationContext(),"HOST","192.168.1.115");
        udPtools = new UDPtools(getApplicationContext(),port,host);
    }

    private void initUI() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        text = (TextView) findViewById(R.id.text);
        OK_bt = (Button) findViewById(R.id.bt);
        ip_et = (EditText) findViewById(R.id.ip);
    }

    public class  MyOnTouchListener implements View.OnTouchListener
    {
        int count  ;
        int x = 0 ;
        int startposX ;
        int startposY ;
        int mLastTime ;
        int mCurTime ;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(view.getId() == R.id.fab)
            {
                text.setText("fab click");
                return true;
            }
            else {
                udPtools.stop();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        text.setText("motion down ");
                        startposX = (int) motionEvent.getX();
                        startposY = (int) motionEvent.getY();

                        count = getcurrentSSS();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (getcurrentSSS() - count >= 16) {
                            count = getcurrentSSS();
                            x++;
                            int currentY = (int) (motionEvent.getY() - startposY);
                            int currentX = (int) (motionEvent.getX() - startposX);
                            startposX = (int) motionEvent.getX();
                            startposY = (int) motionEvent.getY();
                            CONTENT = currentX + "|" + currentY;
                            udPtools.setCONTENT(CONTENT);
                            udPtools.start();
                            text.setText(CONTENT);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        x = 0;
                        text.setText("motion up ");
                        mLastTime = mCurTime;
                        mCurTime = getcurrentSSS();


                        if (mCurTime - mLastTime < 200) {
                            CONTENT = "click" + "|" + "click";
                            udPtools.setCONTENT(CONTENT);
                            text.setText(CONTENT);
                            udPtools.start();
                            return true;
                        }
                        break;
                }
                udPtools.stop();
                return true;
            }
        }
    }

    private int getcurrentSSS()
    {
        return (int)System.currentTimeMillis();
    }


    private Context getcontext()
    {
        return getApplicationContext();
    }
}
