package com.hello.fxf.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int cnt = 0;
    int[] imgs = {R.mipmap.bingguo,R.mipmap.changge,R.mipmap.zipai};
    ImageView ivShow;
    boolean isOK = true;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (cnt == 3) {
                    isOK = false;
                } else {
                    ivShow.setImageResource(imgs[(cnt++)%imgs.length]);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ivShow = (ImageView) findViewById(R.id.ivShow);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isOK) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(1);

                        }
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                        finish();
                    }
                }).start();

    }
}
