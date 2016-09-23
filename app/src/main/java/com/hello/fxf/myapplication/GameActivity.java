package com.hello.fxf.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    int chooseImgId;
    int chooseDif;
    LinearLayout llMain;
    int width = 400;
    int height = 400;
    Bitmap[][] bmp;
    MyImageView[][] mivs;
    Bitmap[][] bmp_fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        chooseImgId = intent.getIntExtra("chooseImgId", R.mipmap.bingguo);
        chooseDif = intent.getIntExtra("chooseDif", 3);
        llMain = (LinearLayout) findViewById(R.id.llMain);
        bmp = new Bitmap[chooseDif][chooseDif];
        mivs = new MyImageView[chooseDif][chooseDif];
        //得到原图的Bitmap
        Bitmap bitmapRes = BitmapFactory.decodeResource(getResources(), chooseImgId);
        //转换成统一大小的图片
        bitmapRes = Bitmap.createScaledBitmap(bitmapRes, width, height, true);
        //生成平图的一个棋盘
        for (int i = 0; i < chooseDif; i++) {
            LinearLayout llTwo = new LinearLayout(GameActivity.this);
            llTwo.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < chooseDif; j++) {
                final MyImageView miv = new MyImageView(GameActivity.this);
                miv.X = i;
                miv.Y = j;
                //切图
                Bitmap bitmapTemp = Bitmap.createBitmap(bitmapRes, j * width / chooseDif, i * width / chooseDif,
                        width / chooseDif, height / chooseDif, null, true);
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(width / chooseDif, height / chooseDif);
                if (i == chooseDif - 1 && j == chooseDif - 1) {
                    bmp[i][j] = null;
                } else {
                    bmp[i][j] = bitmapTemp;
                }

                mivs[i][j] = miv;
                llp.setMargins(2, 2, 2, 2);
                miv.setLayoutParams(llp);
                miv.setImageBitmap(bmp[i][j]);
                llTwo.addView(miv);


                miv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyImageView mv = (MyImageView) v;
                        Toast.makeText(GameActivity.this, mv.X + "-" + mv.Y, Toast.LENGTH_LONG).show();
                        //上
                        if (mv.X - 1 >= 0 && bmp[mv.X - 1][mv.Y] == null) {
                            mivs[mv.X][mv.Y].setImageBitmap(bmp[mv.X - 1][mv.Y]);
                            mivs[mv.X - 1][mv.Y].setImageBitmap(bmp[mv.X][mv.Y]);
                            Bitmap bmpTemp;
                            bmpTemp = bmp[mv.X][mv.Y];
                            bmp[mv.X][mv.Y] = bmp[mv.X - 1][mv.Y];
                            bmp[mv.X - 1][mv.Y] = bmpTemp;
                        }
                        //下
                        if (mv.X + 1 < chooseDif && bmp[mv.X + 1][mv.Y] == null) {
                            mivs[mv.X][mv.Y].setImageBitmap(bmp[mv.X + 1][mv.Y]);
                            mivs[mv.X + 1][mv.Y].setImageBitmap(bmp[mv.X][mv.Y]);
                            Bitmap bmpTemp;
                            bmpTemp = bmp[mv.X][mv.Y];
                            bmp[mv.X][mv.Y] = bmp[mv.X + 1][mv.Y];
                            bmp[mv.X + 1][mv.Y] = bmpTemp;
                        }
                        //左
                        if (mv.Y - 1 >= 0 && bmp[mv.X][mv.Y - 1] == null) {
                            mivs[mv.X][mv.Y].setImageBitmap(bmp[mv.X][mv.Y - 1]);
                            mivs[mv.X][mv.Y - 1].setImageBitmap(bmp[mv.X][mv.Y]);
                            Bitmap bmpTemp;
                            bmpTemp = bmp[mv.X][mv.Y];
                            bmp[mv.X][mv.Y] = bmp[mv.X][mv.Y - 1];
                            bmp[mv.X][mv.Y - 1] = bmpTemp;
                        }
                        //右
                        if (mv.Y + 1 < chooseDif && bmp[mv.X][mv.Y + 1] == null) {
                            mivs[mv.X][mv.Y].setImageBitmap(bmp[mv.X][mv.Y + 1]);
                            mivs[mv.X][mv.Y + 1].setImageBitmap(bmp[mv.X][mv.Y]);
                            Bitmap bmpTemp;
                            bmpTemp = bmp[mv.X][mv.Y];
                            bmp[mv.X][mv.Y] = bmp[mv.X][mv.Y + 1];
                            bmp[mv.X][mv.Y + 1] = bmpTemp;
                        }
                    }
                });
            }
            llMain.addView(llTwo);
        }
    }

    public boolean isOver() {
        for (int i = 0; i < chooseDif; i++) {

            for (int j = 0; j < chooseDif; j++) {
                if (bmp[i][j] != bmp_fb[i][j]) {

                }
            }
        }
        return false;
    }

    public class MyImageView extends ImageView {
        public int X;
        public int Y;

        public MyImageView(Context context) {
            super(context);
        }
    }
}
