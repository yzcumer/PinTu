package com.hello.fxf.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    LinearLayout llmain;
    ImageView iv;
    Spinner sp;
    int chooseImgId;
    int chooseDif;
    int[] imgs = {R.mipmap.bingguo, R.mipmap.changge, R.mipmap.maotou, R.mipmap.wenhuaji, R.mipmap.zhentan, R.mipmap.zipai};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        llmain = (LinearLayout) findViewById(R.id.llmain);
        iv = (ImageView) findViewById(R.id.iv);
        sp = (Spinner) findViewById(R.id.sp);

        for (int i = 0; i < imgs.length; i++) {
            final int j = i;
            //图像处理
            Bitmap bitmp = BitmapFactory.decodeResource(getResources(),
                    imgs[i]);
            bitmp = Bitmap.createScaledBitmap(bitmp, 100, 150, true);
            //动态生成ImageView
            ImageView ivShow = new ImageView(Main3Activity.this);
            ivShow.setImageBitmap(bitmp);
            ivShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(imgs[j]);
                    chooseImgId = imgs[j];
                }
            });
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(Main3Activity.this, "The seleted is " + (position + 3), Toast.LENGTH_LONG).show();
                    chooseDif = position + 3;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Main3Activity.this, GameActivity.class);
                    intent.putExtra("chooseImgId", chooseImgId);
                    intent.putExtra("chooseDif", chooseDif);
                    startActivity(intent);
                    finish();
                }
            });
            //在控件的父类容器中设置间距
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(-1, -2);
            llp.setMargins(10, 20, 10, 20);
            ivShow.setLayoutParams(llp);
            llmain.addView(ivShow);

        }
    }
}
