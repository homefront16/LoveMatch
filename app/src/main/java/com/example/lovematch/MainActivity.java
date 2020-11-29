package com.example.lovematch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCompute;
    ImageView ivNeedle;
    EditText etYourName, etOtherPersonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCompute = findViewById(R.id.btnCompute);
        ivNeedle = findViewById(R.id.ivNeedle);
        etYourName = findViewById(R.id.etYourName);
        etOtherPersonName = findViewById(R.id.etOtherPersonName);

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourName = etYourName.getText().toString().toLowerCase();
                String otherPersonName = etOtherPersonName.getText().toString().toLowerCase();

                float totalLetters = yourName.length() + otherPersonName.length();
                float totalMatches = 0;

                for(int i = 0; i < yourName.length(); i++){
                    for(int j = 0; j < otherPersonName.length(); j++){
                        if(yourName.charAt(i) == otherPersonName.charAt(j)){
                          totalMatches++;
                        }
                    }
                }

                for(int i = 0; i < otherPersonName.length(); i++){
                    for(int j = 0; j < yourName.length(); j++){
                        if(otherPersonName.charAt(i) == yourName.charAt(j)){
                            totalMatches++;
                        }
                    }
                }

                float compatScore = totalMatches / totalLetters;

                // Love score between -50 and 50
                int loveScore = ((int) (compatScore * 100)) - 50;

                RotateAnimation ra = new RotateAnimation(0,360 + loveScore,
                        Animation.RELATIVE_TO_SELF, .5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);

                ra.setFillAfter(true);
                ra.setDuration(2000);
                ra.setInterpolator(new AccelerateDecelerateInterpolator());
                ivNeedle.startAnimation(ra);
                Toast.makeText(MainActivity.this, "Love Score = " + loveScore, Toast.LENGTH_SHORT).show();
            }
        });
    }
}