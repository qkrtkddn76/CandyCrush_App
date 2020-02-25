package com.example.main_candy;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class S1_1_1 extends AppCompatActivity {
    Button menuBtn1;
    TableLayout placeLay;
    int maxWidth,maxHeight;
    TextView life,scoreTV;
    Integer[][] imgViewIDs = {
            {R.id.lay00,R.id.lay01,R.id.lay02,R.id.lay03,R.id.lay04,R.id.lay05,R.id.lay06,R.id.lay07,R.id.lay08},
            {R.id.lay10,R.id.lay11,R.id.lay12,R.id.lay13,R.id.lay14,R.id.lay15,R.id.lay16,R.id.lay17,R.id.lay18},
            {R.id.lay20,R.id.lay21,R.id.lay22,R.id.lay23,R.id.lay24,R.id.lay25,R.id.lay26,R.id.lay27,R.id.lay28},
            {R.id.lay30,R.id.lay31,R.id.lay32,R.id.lay33,R.id.lay34,R.id.lay35,R.id.lay36,R.id.lay37,R.id.lay38},
            {R.id.lay40,R.id.lay41,R.id.lay42,R.id.lay43,R.id.lay44,R.id.lay45,R.id.lay46,R.id.lay47,R.id.lay48},
            {R.id.lay50,R.id.lay51,R.id.lay52,R.id.lay53,R.id.lay54,R.id.lay55,R.id.lay56,R.id.lay57,R.id.lay58},
            {R.id.lay60,R.id.lay61,R.id.lay62,R.id.lay63,R.id.lay64,R.id.lay65,R.id.lay66,R.id.lay67,R.id.lay68},
            {R.id.lay70,R.id.lay71,R.id.lay72,R.id.lay73,R.id.lay74,R.id.lay75,R.id.lay76,R.id.lay77,R.id.lay78},
            {R.id.lay80,R.id.lay81,R.id.lay82,R.id.lay83,R.id.lay84,R.id.lay85,R.id.lay86,R.id.lay87,R.id.lay88}};
    ImageView[][] imageViews =new ImageView[9][9];
    Placement placement = new Placement(); //클래스에서 블록을다루기위해
    int oldXvalue, oldYvalue ,finalXvalue, finalYvalue,unit;
    int movedXvalue,movedYvalue;
    int tmpImageSrc,tmpImageSrc1,tmpImageSrc2 ;
    int blocksBackGround[][]=new int[9][9];
    int score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_1_1);
        placeLay = findViewById(R.id.placeLay);
        menuBtn1 =findViewById(R.id.stageMenu);
        scoreTV=findViewById(R.id.score1);
        life=findViewById(R.id.life1);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                blocksBackGround[i][j]=0;
            }
        }

        init();
        menuBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        placeLay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                maxWidth = view.getWidth();
                maxHeight =view.getHeight();
                unit= maxWidth/9;
                tmpImageSrc=tmpImageSrc1=tmpImageSrc2=0;


                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    oldXvalue = (int)motionEvent.getX();    //맨처음 x,y좌표
                    oldYvalue = (int)motionEvent.getY();
                    // Toast.makeText(getApplicationContext(),oldXvalue+" || "+oldYvalue,Toast.LENGTH_SHORT).show();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    movedXvalue = Math.abs((int)motionEvent.getX()-oldXvalue); //이동거리(절대값)
                    movedYvalue = Math.abs((int)motionEvent.getY()-oldYvalue);
                    finalXvalue  = (int)motionEvent.getX();    //최종 x,y좌표
                    finalYvalue  = (int)motionEvent.getY();
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    tmpImageSrc1 = placement.getBlockType(oldYvalue/unit,oldXvalue/unit);
                    tmpImageSrc=tmpImageSrc1;
                    tmpImageSrc2 = placement.getBlockType(finalYvalue/unit,finalXvalue/unit);
                    if(((50<movedXvalue&&movedXvalue<130) && movedYvalue<30)||((50<movedYvalue&&movedYvalue<130)&& movedXvalue<30)){//한방향으로 교체가능한 이동거리일때
                        placement.swapBlockType(oldYvalue/unit,oldXvalue/unit,finalYvalue/unit,finalXvalue/unit); //블록교체
                        countDown();
                        backGroundReset();

                    }

                }
                //SystemClock.sleep(5000);
                score +=placement.explosionCheck();
                scoreTV.setText(""+score);
                placement.blockDrop();
                blocksBackGround =placement.makeBlock(blocksBackGround);
                backGroundEffect();
                init();
                //backGroundReset();
                if(Integer.parseInt(life.getText().toString())==0){
                    Toast.makeText(getApplicationContext(),"이동횟수를 다썼습니다!",Toast.LENGTH_LONG).show();
                    finish();
                }

                return true;
            }
        });
    }
    public void backGroundReset(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                blocksBackGround[i][j]=0;
            }
        }
    }
    public void backGroundEffect(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(blocksBackGround[i][j]==0)
                    imageViews[i][j].setBackgroundResource(R.drawable.blank);
                else if(blocksBackGround[i][j]==1)
                    imageViews[i][j].setBackgroundResource(R.drawable.highlight);
            }
        }
    }


    public void init(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                imageViews[i][j] = findViewById(imgViewIDs[i][j]);
                imageViews[i][j].setImageResource(placement.getBlockType(i,j));
            }
        }

    }
    public void countDown(){
        int n= Integer.parseInt(life.getText().toString())-1;
        life.setText(Integer.toString(n));
    }




}