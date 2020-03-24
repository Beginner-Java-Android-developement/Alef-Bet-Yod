package com.golan.amit.alefbetyod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DEBUGTAG = "AMGO";
    public static final boolean DEBUG = false;

    ImageView ivMainPic;
    Button btnGoToPlay;
    Animation animRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMainPic = findViewById(R.id.ivMain);
        btnGoToPlay = findViewById(R.id.btnGoToGame);
        btnGoToPlay.setOnClickListener(this);
        animRight = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_right);
        ivMainPic.startAnimation(animRight);
    }

    @Override
    public void onClick(View v) {
        if(v == btnGoToPlay) {
            Intent i = new Intent(this, AlefBetYodActivity.class);
            startActivity(i);
        }
    }
}
