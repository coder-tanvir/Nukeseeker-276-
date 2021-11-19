package com.example.nukeseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setoptionsbutton();
        setplaygamebutton();
        setuphelpbutton();
    }

    private void setplaygamebutton() {
        Button gamebutton=(Button) findViewById(R.id.Playgame);
        gamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameintent=new Intent(Menu.this,playgame.class);
                startActivity(gameintent);
                finish();

            }
        });
    }

    private void setoptionsbutton() {
        Button optionsbutton=(Button)findViewById(R.id.options);
        optionsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionintent=new Intent(Menu.this,option.class);
                startActivity(optionintent);
                finish();


            }
        });
    }

    private void setuphelpbutton(){
        Button helpbutton=(Button)findViewById(R.id.help);
        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpintent=new Intent(Menu.this,Help.class);
                startActivity(helpintent);
                finish();
            }
        });
    }
}