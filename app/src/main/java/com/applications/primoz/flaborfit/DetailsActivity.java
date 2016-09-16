package com.applications.primoz.flaborfit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView titleText = (TextView) findViewById(R.id.titleText);
        ImageView mainImg = (ImageView) findViewById(R.id.mainImg);
        LinearLayout mainBG = (LinearLayout) findViewById(R.id.mainBg);

        String exerciseTitle = getIntent().getStringExtra(MainActivity.EXTRA_ITEM_TITLE);
        titleText.setText(exerciseTitle);

        boolean defaultValue = getIntent().getBooleanExtra(MainActivity.MODE_NIGHT, false);
        Log.d("NIGHT", defaultValue + "");


        if (defaultValue) {
            mainBG.setBackgroundColor(Color.parseColor("#444540"));
        } else {
            if (exerciseTitle.equalsIgnoreCase(MainActivity.EXERCISE_WEIGHTS)) {
                mainImg.setImageDrawable(getResources().getDrawable(R.drawable.weight, getApplicationContext().getTheme()));
                mainBG.setBackgroundColor(Color.parseColor("#2ca5f5"));
            } else if (exerciseTitle.equalsIgnoreCase(MainActivity.EXERCISE_YOGA)) {
                mainImg.setImageDrawable(getResources().getDrawable(R.drawable.lotus, getApplicationContext().getTheme()));
                mainBG.setBackgroundColor(Color.parseColor("#916bcd"));
            } else {
                mainImg.setImageDrawable(getResources().getDrawable(R.drawable.heart, getApplicationContext().getTheme()));
                mainBG.setBackgroundColor(Color.parseColor("#52ad56"));
            }
        }
    }
}
