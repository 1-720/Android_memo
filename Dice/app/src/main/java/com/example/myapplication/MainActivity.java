package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.roll_button);
        button.setText("Let's Roll");

        imageView = findViewById(R.id.image);
        imageView.setImageResource(R.drawable.empty_dice);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

    }

    private void rollDice() {

        Random random = new Random();
        int randomInt = random.nextInt(6) + 1;

        switch (randomInt) {
            case 1:
                imageView.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.dice_6);
                break;

        }

    }
}
