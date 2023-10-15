package com.example.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    TextView welcomeTxt, luckNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        welcomeTxt = findViewById(R.id.textView2);
        luckNumberTxt = findViewById(R.id.lucky_number_txt);
        share_btn = findViewById(R.id.share_lucky_number);


        //Receiving the data from Main Activity
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        //Generating Radom Numbers
        int random_num = generateRadomNumber();
        luckNumberTxt.setText("" + random_num);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(userName, random_num);
            }
        });

    }

    public int generateRadomNumber(){
        Random random = new Random();
        int upper_limit = 1000;

        int randomnumberGenerated = random.nextInt(upper_limit);
        return randomnumberGenerated;
    }

    public void shareData(String username, int randomNumber){
        //Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        //Additional Info
        i.putExtra(Intent.EXTRA_SUBJECT, username + "got lucky today");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: " + randomNumber);

        startActivity(Intent.createChooser(i,"Choose a Platform"));

    }
}