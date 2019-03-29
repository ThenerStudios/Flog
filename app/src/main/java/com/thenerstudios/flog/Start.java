package com.thenerstudios.flog;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Start extends AppCompatActivity {

    private ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        constraintLayout = findViewById(R.id.start_background);
        constraintLayout.setBackgroundResource(R.drawable.food);


    }


}
