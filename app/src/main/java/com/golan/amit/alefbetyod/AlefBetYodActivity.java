package com.golan.amit.alefbetyod;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AlefBetYodActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivDisplay;
    ImageButton btnUp, btnDown, btnPlay;
    TextView tvDisplay;
    AlefBetYodHelper aby;
    Bitmap[] bm;
    SoundPool sp;
    int[] abySnd;
    Animation animScaleInOut, animScaleOutIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alef_bet_yod);

        init();

        play();
    }

    private void play() {
        btnDown.setVisibility(View.INVISIBLE);
        ivDisplay.setImageBitmap(bm[aby.get_runner()]);
    }

    private void init() {
        ivDisplay = findViewById(R.id.ivDisplay);
        btnDown = findViewById(R.id.btnDown);
        btnUp = findViewById(R.id.btnUp);
        btnPlay = findViewById(R.id.btnPlay);
        btnDown.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        tvDisplay = findViewById(R.id.tvDisplayAlefBet);
        aby = new AlefBetYodHelper();
        /**
         * Animation
         */

        animScaleInOut = AnimationUtils.loadAnimation(this, R.anim.anim_scale_inout);
        animScaleOutIn = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

        /**
         * Images / Media
         */
        bm = new Bitmap[10];
        bm[0] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef0);
        bm[1] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef1);
        bm[2] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef2);
        bm[3] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef3);
        bm[4] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef4);
        bm[5] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef5);
        bm[6] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef6);
        bm[7] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef7);
        bm[8] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef8);
        bm[9] = BitmapFactory.decodeResource(getResources(), R.mipmap.alef9);
        /**
         * Sound
         */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes aa = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME). build();
            sp = new SoundPool.Builder()
                    .setMaxStreams(10).setAudioAttributes(aa).build();
        } else {
            sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }
        abySnd = new int[10];
        abySnd[0] = sp.load(this, R.raw.alef0, 1);
        abySnd[1] = sp.load(this, R.raw.alef1, 1);
        abySnd[2] = sp.load(this, R.raw.alef2, 1);
        abySnd[3] = sp.load(this, R.raw.alef3, 1);
        abySnd[4] = sp.load(this, R.raw.alef4, 1);
        abySnd[5] = sp.load(this, R.raw.alef5, 1);
        abySnd[6] = sp.load(this, R.raw.zain, 1);
        abySnd[7] = sp.load(this, R.raw.het, 1);
        abySnd[8] = sp.load(this, R.raw.tet, 1);
        abySnd[9] = sp.load(this, R.raw.alef9, 1);
    }

    @Override
    public void onClick(View v) {
        if(v == btnDown) {
            aby.decreaseRunner();
            if(aby.get_runner() == 0) {
                btnDown.setVisibility(View.INVISIBLE);
            }
            btnUp.setVisibility(View.VISIBLE);
            ivDisplay.setImageBitmap(bm[aby.get_runner()]);
            sp.play(abySnd[aby.get_runner()], 1, 1, 0, 0, 1);
            v.setAlpha((float)0.8);
            ivDisplay.startAnimation(animScaleInOut);
        } else if (v == btnUp) {
            aby.increaseRunner();
            if(aby.get_runner() == 9) {
                btnUp.setVisibility(View.INVISIBLE);
            }
            btnDown.setVisibility(View.VISIBLE);
            ivDisplay.setImageBitmap(bm[aby.get_runner()]);
            sp.play(abySnd[aby.get_runner()], 1, 1, 0, 0, 1);
            v.setAlpha((float)0.8);
            ivDisplay.startAnimation(animScaleOutIn);
        } else if(v == btnPlay) {
            sp.play(abySnd[aby.get_runner()], 1, 1, 0, 0, 1);
            v.setAlpha((float)0.8);
        } else {
            Log.d(MainActivity.DEBUGTAG, "button - should never reach here");
        }
        tvDisplay.setText(aby.sequenceRepresentation());
    }
}
