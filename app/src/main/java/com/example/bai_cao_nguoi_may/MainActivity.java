package com.example.bai_cao_nguoi_may;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int manghinhbai[] = {
            R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
            R.drawable.cj, R.drawable.cq, R.drawable.ck,
            R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
            R.drawable.dj, R.drawable.dq, R.drawable.dk,
            R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
            R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
            R.drawable.hj, R.drawable.hq, R.drawable.hk,
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.sj, R.drawable.sq, R.drawable.sk};


    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;

    private TextView result_people;
    private TextView result_bot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);

        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);

        result_people = findViewById(R.id.result_people);
        result_bot = findViewById(R.id.result_bot);
        Button btnGetCards = findViewById(R.id.btnRutBai);

        btnGetCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] threeCard = random3Number(0, 51);
                iv4.setImageResource(manghinhbai[threeCard[0]]);
                iv5.setImageResource(manghinhbai[threeCard[1]]);
                iv6.setImageResource(manghinhbai[threeCard[2]]);
                result_people.setText(calculateScore(threeCard));

                botPlay();

            }
        });

    }

    private void botPlay() {
        int[] threeCard = random3Number(0, 51);
        iv1.setImageResource(manghinhbai[threeCard[0]]);
        iv2.setImageResource(manghinhbai[threeCard[1]]);
        iv3.setImageResource(manghinhbai[threeCard[2]]);
        result_bot.setText(calculateScore(threeCard));
    }

    private String calculateScore(int[] arr) {
        String result = "";

        if (count_JQK(arr) == 3)
            result = "3 tây - win";
        else {
            int tong = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] % 13 < 10)
                    tong += arr[i] % 13 + 1;

            }

            if (tong % 10 == 0) {
                result = "0";
            } else {
                result = "" + (tong % 10) + ", số lá tây " + count_JQK(arr);
            }
        }
        return result;
    }

    private int count_JQK(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 13 >= 10)
                count++;
        }
        return count;
    }

    private int[] random3Number(int min, int max) {
        int[] arr = new int[3];
        int i = 0;

        arr[i++] = randomNumber(min, max);

        do {
            int k = randomNumber(min, max);

            if (!duplicationNumber(k, arr))
                arr[i++] = k;
        } while (i < 3);

        return arr;
    }

    private boolean duplicationNumber(int k, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (k == arr[i])
                return true;
        }
        return false;
    }


    private int randomNumber(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }


}