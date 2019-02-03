package com.example.pc.quehojaes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import weka.classifiers.functions.MultilayerPerceptron;


public class Splash extends Activity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final ImageView iv= (ImageView) findViewById(R.id.imageView);
        final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an2= AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                SingletonMLP.getInstance();
                iv.startAnimation(an2);
                finish();
                Intent i10 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i10);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }




}
