package com.example.baseecommerceapp.Buyers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baseecommerceapp.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView welcome_tv;
    private static int splashTimeOut = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        welcome_tv = findViewById( R.id.welcome_tv );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class );
                startActivity( i );
                finish();
            }
        }, splashTimeOut );

        Animation text_animate = AnimationUtils.loadAnimation( this, R.anim.my_splash_text_animation );
        welcome_tv.startAnimation( text_animate );

    }
}