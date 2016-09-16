package com.applications.primoz.flaborfit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_ITEM_TITLE = "extra.item.title";
    public static final String EXERCISE_WEIGHTS = "Weight lifting";
    public static final String EXERCISE_YOGA = "Yoga";
    public static final String EXERCISE_CARDIO = "Cardio";
    public static final String MODE_NIGHT = "mode.night";
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout weightBtn = (RelativeLayout) findViewById(R.id.weightBtn);
        RelativeLayout yogaBtn = (RelativeLayout) findViewById(R.id.yogaBtn);
        RelativeLayout cardioBtn = (RelativeLayout) findViewById(R.id.cardioBtn);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutMain);

        final CheckBox nightMode = (CheckBox) findViewById(R.id.cbNight);

         sharedPref = getPreferences(Context.MODE_PRIVATE);
        boolean defaultValue = sharedPref.getBoolean(MainActivity.MODE_NIGHT, false);

        if (defaultValue) {
            nightMode.setChecked(true);
        } else {
            nightMode.setChecked(false);
        }

        changeBackground(linearLayout, nightMode);

        weightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetailActivity(MainActivity.EXERCISE_WEIGHTS);
            }
        });

        yogaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetailActivity(MainActivity.EXERCISE_YOGA);
            }
        });

        cardioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetailActivity(MainActivity.EXERCISE_CARDIO);
            }
        });


        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean(MODE_NIGHT, true);
                    editor.apply();
                    changeBackground(linearLayout,nightMode);
                } else {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean(MODE_NIGHT, false);
                    editor.apply();
                    changeBackground(linearLayout,nightMode);
                }


            }
        });

    }

    private void changeBackground(LinearLayout linearLayout, CheckBox nightMode) {
        if (nightMode.isChecked()) {
            linearLayout.setBackgroundColor(Color.parseColor("#444540"));
        }else{
            linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    private void loadDetailActivity(String exerciseTitle) {
        boolean defaultValue = sharedPref.getBoolean(MainActivity.MODE_NIGHT, false);
        Log.d("NIGHT_MAIN", defaultValue + "");

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(MainActivity.EXTRA_ITEM_TITLE, exerciseTitle);
        intent.putExtra(MainActivity.MODE_NIGHT, defaultValue);
        startActivity(intent);
    }
}
