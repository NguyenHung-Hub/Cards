package com.example.bai_cao_nguoi_may;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BotBotActivity extends AppCompatActivity {

    private int manghinhbai[] = {
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


    private ImageView iv1_botBot;
    private ImageView iv2_botBot;
    private ImageView iv3_botBot;
    private ImageView iv4_botBot;
    private ImageView iv5_botBot;
    private ImageView iv6_botBot;


    private TextView tv_subScoreBotA;
    private TextView tv_subScoreBotB;

    private TextView scoreBotBot;
    private TextView tv_countdown;
    private Button btnStopStart;

    private int scoreBotA = 0;
    private int scoreBotB = 0;
//    private int subScoreBotA;
//    private int subScoreBotB;


    private CountDownTimer countDownTimer;
    private long totalTime = 6000;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_bot);

        iv1_botBot = findViewById(R.id.iv1_botBot);
        iv2_botBot = findViewById(R.id.iv2_botBot);
        iv3_botBot = findViewById(R.id.iv3_botBot);

        iv4_botBot = findViewById(R.id.iv4_botBot);
        iv5_botBot = findViewById(R.id.iv5_botBot);
        iv6_botBot = findViewById(R.id.iv6_botBot);

        tv_subScoreBotA = findViewById(R.id.tv_subScoreBotA);
        tv_subScoreBotB = findViewById(R.id.tv_subScoreBotB);
        scoreBotBot = findViewById(R.id.scoreBotBot);

        tv_countdown = findViewById(R.id.tv_countdownTimer);

        btnStopStart = findViewById(R.id.btnStopStart);

        btnStopStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });


    }

    private void startStop() {
        if (isRunning)
            stopTimer();
        else
            startTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long l) {
                totalTime = l;
                updateTimer();
                play();

            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();

        isRunning = true;
        btnStopStart.setText("Stop");


    }

    private void stopTimer() {
        countDownTimer.cancel();
        isRunning = false;
        btnStopStart.setText("Start");
    }

    private void updateTimer() {
        int minutes = (int) totalTime / 60000;
        int seconds = (int) totalTime % 60000 / 1000;

        String timeString = "" + minutes + ":";
        if (seconds < 10)
            timeString += "0";
        timeString += seconds;

        tv_countdown.setText(timeString);

    }

    private void play() {
        int[] sixCards = random6Number(0, 51);
        int[] threeCardBotB = new int[3];
        int[] threeCardBotA = new int[3];

        threeCardBotB[0] = sixCards[0];
        threeCardBotB[1] = sixCards[1];
        threeCardBotB[2] = sixCards[2];
        threeCardBotA[0] = sixCards[3];
        threeCardBotA[1] = sixCards[4];
        threeCardBotA[2] = sixCards[5];

        iv4_botBot.setImageResource(manghinhbai[threeCardBotB[0]]);
        iv5_botBot.setImageResource(manghinhbai[threeCardBotB[1]]);
        iv6_botBot.setImageResource(manghinhbai[threeCardBotB[2]]);
        int subScoreBotB = calculateScore(threeCardBotB);

        if (subScoreBotB == 999) {
            tv_subScoreBotB.setText("3 tây - win");
        } else {
            tv_subScoreBotB.setText(subScoreBotB + " điểm");
        }


        iv1_botBot.setImageResource(manghinhbai[threeCardBotA[0]]);
        iv2_botBot.setImageResource(manghinhbai[threeCardBotA[1]]);
        iv3_botBot.setImageResource(manghinhbai[threeCardBotA[2]]);
        int subScoreBotA = calculateScore(threeCardBotA);
        if (subScoreBotA == 999) {
            tv_subScoreBotA.setText("3 tây - win");
        } else {
            tv_subScoreBotA.setText(subScoreBotA + " điểm");
        }

        if (subScoreBotA > subScoreBotB) {
            scoreBotA += 1;
        } else if (subScoreBotA < subScoreBotB) {
            scoreBotB += 1;
        } else {
            scoreBotA += 1;
            scoreBotB += 1;
        }

        scoreBotBot.setText(scoreBotA + " - " + scoreBotB);

    }

    private void endGame() {

        AlertDialog alertDialog = new AlertDialog.Builder(BotBotActivity.this)
                .setTitle("Thông báo")
                .setMessage("Bạn có muốn chơi lại không?")
                .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
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

    private void restarGame() {
        totalTime = 6000;
        scoreBotA = 0;
        scoreBotB = 0;

        iv1_botBot.setImageResource(R.drawable.b1fv);
        iv2_botBot.setImageResource(R.drawable.b1fv);
        iv3_botBot.setImageResource(R.drawable.b1fv);
        iv4_botBot.setImageResource(R.drawable.b1fv);
        iv5_botBot.setImageResource(R.drawable.b1fv);
        iv6_botBot.setImageResource(R.drawable.b1fv);

        tv_subScoreBotA.setText("Kết quả");
        tv_subScoreBotB.setText("Kết quả");

        tv_countdown.setText("-:-");
        btnStopStart.setText("Start");

        scoreBotBot.setText("0 - 0");

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