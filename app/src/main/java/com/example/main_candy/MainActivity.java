package com.example.main_candy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView1;
    Button btnS1_1_1,btnS1_2_1,btnS1_2_2,btnS1_3_1,btnS1_3_2,btnS1_4_1,btnS1_5_1,btnS1_5_2,btnS1_6_1,btnS1_6_2;
    Button btnS1_7_1,btnS1_7_2,btnS1_7_3,btnS1_8_1,btnS1_8_2,menuBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView1 = findViewById(R.id.scroll1);
        setStage();
        setStageBtns();
        scrollToEnd();
    }

    protected void setStage(){
        btnS1_1_1 = findViewById(R.id.s1_1_1);
        btnS1_2_1 = findViewById(R.id.s1_2_1);
        btnS1_2_2 = findViewById(R.id.s1_2_2);
        btnS1_3_1 = findViewById(R.id.s1_3_1);
        btnS1_3_2 = findViewById(R.id.s1_3_2);
        btnS1_4_1 = findViewById(R.id.s1_4_1);
        btnS1_5_1 = findViewById(R.id.s1_5_1);
        btnS1_5_2 = findViewById(R.id.s1_5_2);
        btnS1_6_1 = findViewById(R.id.s1_6_1);
        btnS1_6_2 = findViewById(R.id.s1_6_2);
        btnS1_7_1 = findViewById(R.id.s1_7_1);
        btnS1_7_2 = findViewById(R.id.s1_7_2);
        btnS1_7_3 = findViewById(R.id.s1_7_3);
        btnS1_8_1 = findViewById(R.id.s1_8_1);
        btnS1_8_2 = findViewById(R.id.s1_8_2);
        menuBtn = findViewById(R.id.menuBtn);
    }
    public void scrollToEnd(){
        scrollView1.post(new Runnable() {
            @Override
            public void run() {
                scrollView1.fullScroll(View.FOCUS_DOWN);
            }

        });

    }



    protected void setStageBtns(){
        btnS1_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S1_1_1.class);
                startActivity(intent);
            }
        });
        btnS1_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S1_2_1.class);
                startActivity(intent);
            }
        });
        btnS1_2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S1_2_2.class);
                startActivity(intent);
            }
        });
        btnS1_3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_3_1.class);
                startActivity(intent);
            }
        });
        btnS1_3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_3_2.class);
                startActivity(intent);
            }
        });
        btnS1_4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_4_1.class);
                startActivity(intent);
            }
        });
        btnS1_5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_5_1.class);
                startActivity(intent);
            }
        });
        btnS1_5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_5_2.class);
                startActivity(intent);
            }
        });
        btnS1_6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_6_1.class);
                startActivity(intent);
            }
        });
        btnS1_6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_6_2.class);
                startActivity(intent);
            }
        });
        btnS1_7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_7_1.class);
                startActivity(intent);
            }
        });
        btnS1_7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_7_2.class);
                startActivity(intent);
            }
        });
        btnS1_7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_7_3.class);
                startActivity(intent);
            }
        });
        btnS1_8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_8_1.class);
                startActivity(intent);
            }
        });
        btnS1_8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),S_1_8_2.class);
                startActivity(intent);
            }
        });

    }

}
