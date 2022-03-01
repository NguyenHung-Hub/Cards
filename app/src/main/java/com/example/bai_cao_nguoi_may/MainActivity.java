package com.example.bai_cao_nguoi_may;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnBotPeople;
    private Button btnBotBot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBotPeople = findViewById(R.id.btnBotPeople);
        btnBotBot = findViewById(R.id.btnBotBot);

        btnBotPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBotPeople = new Intent(MainActivity.this, BotPeopleActivity.class);
                startActivityForResult(intentBotPeople, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK){
            String rs = data.getStringExtra("choilai");
//            if (rs == "Chơi lại"){
//                Intent intentBotPeople = new Intent(MainActivity.this, BotPeopleActivity.class);
//                startActivityForResult(intentBotPeople, 100);
//            }
        }
    }
}