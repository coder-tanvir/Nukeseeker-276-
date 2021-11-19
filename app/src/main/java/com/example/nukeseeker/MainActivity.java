package com.example.nukeseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=8500;
    public void onClickImge(View view){
        ImageView imageview=(ImageView) findViewById(R.id.nukeview);
        imageview.animate().alpha(0.4f).setDuration(5000);
        imageview.animate().translationX(1000).setDuration(5000);
    }
    public void onClickImge2(View view){
        ImageView imageview2=(ImageView)findViewById(R.id.magniguy);
        imageview2.animate().alpha(0.4f).setDuration(5000);
        imageview2.animate().translationY(1200).setDuration(5000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skipbutton();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent menuintent=new Intent(MainActivity.this,Menu.class);
                startActivity(menuintent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }

    private void skipbutton() {
        Button button=(Button)findViewById(R.id.skipbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anothermenuintent=new Intent(MainActivity.this,Menu.class);
                startActivity(anothermenuintent);
                finish();

            }
        });
    }

}