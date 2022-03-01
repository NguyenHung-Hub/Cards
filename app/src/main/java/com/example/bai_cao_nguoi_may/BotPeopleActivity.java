package com.example.bai_cao_nguoi_may;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BotPeopleActivity extends AppCompatActivity {

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

    private Button btnGetCards;
    private TextView scoreBotPeople;
    private int scoreBot = 0;
    private int scorePeople = 0;

    private int timesPlay = 3;
    private TextView tv_timesPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_people);

        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);

        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);

        result_people = findViewById(R.id.result_people);
        result_bot = findViewById(R.id.result_bot);
        btnGetCards = findViewById(R.id.btnRutBai);

        scoreBotPeople = findViewById(R.id.scoreBotPeople);
        tv_timesPlay = findViewById(R.id.tv_timesPlay);

        tv_timesPlay.setText("Bạn còn " + timesPlay + " rút bài.");

        btnGetCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] sixCards = random6Number(0, 51);
                int[] threeCardPeople = new int[3];
                int[] threeCardBot = new int[3];

                threeCardPeople[0] = sixCards[0];
                threeCardPeople[1] = sixCards[1];
                threeCardPeople[2] = sixCards[2];
                threeCardBot[0] = sixCards[3];
                threeCardBot[0] = sixCards[4];
                threeCardBot[0] = sixCards[5];

                iv4.setImageResource(manghinhbai[threeCardPeople[0]]);
                iv5.setImageResource(manghinhbai[threeCardPeople[1]]);
                iv6.setImageResource(manghinhbai[threeCardPeople[2]]);
                int subScorePeople = calculateScore(threeCardPeople);

                if (subScorePeople == 999) {
                    result_people.setText("3 tây - win");
                } else {
                    result_people.setText(subScorePeople + " điểm");
                }


                iv1.setImageResource(manghinhbai[threeCardBot[0]]);
                iv2.setImageResource(manghinhbai[threeCardBot[1]]);
                iv3.setImageResource(manghinhbai[threeCardBot[2]]);
                int subScoreBot = calculateScore(threeCardBot);
                if (subScoreBot == 999) {
                    result_bot.setText("3 tây - win");
                } else {
                    result_bot.setText(subScoreBot + " điểm");
                }

                if (subScoreBot > subScorePeople) {
                    scoreBot += 1;
                } else if (subScoreBot < subScorePeople) {
                    scorePeople += 1;
                } else {
                    scoreBot += 1;
                    scorePeople += 1;
                }

                scoreBotPeople.setText(scoreBot + " - " + scorePeople);
                timesPlay--;
                tv_timesPlay.setText("Bạn còn " + timesPlay + " rút bài.");
                if (timesPlay == 0) {
                    btnGetCards.setEnabled(false);

                    AlertDialog alertDialog = new AlertDialog.Builder(BotPeopleActivity.this)
                            .setTitle("Ai thắng")
                            .setMessage("Nhấn Ok để chơi lại.")
                            .setPositiveButton("Exist", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })

                            .setNegativeButton("Chơi lại", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    restarGame();
                                }
                            }).setCancelable(false)
                            .show();
                }
            }
        });
    }


    private void restarGame() {
        timesPlay = 3;
        scoreBot = 0;
        scorePeople = 0;

        iv1.setImageResource(R.drawable.b1fv);
        iv2.setImageResource(R.drawable.b1fv);
        iv3.setImageResource(R.drawable.b1fv);
        iv4.setImageResource(R.drawable.b1fv);
        iv5.setImageResource(R.drawable.b1fv);
        iv6.setImageResource(R.drawable.b1fv);

        result_bot.setText("Kết quả");
        result_people.setText("Kết quả");


        tv_timesPlay.setText("Bạn còn " + timesPlay + " rút bài.");
        scoreBotPeople.setText("0 - 0");
        btnGetCards.setEnabled(true);

    }

    private int calculateScore(int[] arr) {
        int score = 0;

        if (count_JQK(arr) == 3)
            score = 999;
        else {
            int tong = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] % 13 < 10)
                    tong += arr[i] % 13 + 1;

            }

            score = tong % 10;
        }
        return score;
    }

    private int count_JQK(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 13 >= 10)
                count++;
        }
        return count;
    }

    private int[] random6Number(int min, int max) {
        int[] arr = new int[6];
        int i = 0;

        arr[i++] = randomNumber(min, max);

        do {
            int k = randomNumber(min, max);

            if (!duplicationNumber(k, arr))
                arr[i++] = k;
        } while (i < 6);

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