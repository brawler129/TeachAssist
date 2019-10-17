package com.suchana.atten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heading=findViewById(R.id.flash_screen_heading);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "Poppins-Bold.ttf");
        heading.setTypeface(custom_font);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Walkthrough.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}
