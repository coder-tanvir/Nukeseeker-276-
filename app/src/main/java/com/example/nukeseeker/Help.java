package com.example.nukeseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView how=(TextView)findViewById(R.id.howtoplay);
        how.setText("The objective is to finish the game with least scans used. When user presses on a button it reveals the number of NUKES in the same column and row, use that to find other NUKES ");

        TextView link1=(TextView)findViewById(R.id.nukelink);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link2=(TextView)findViewById(R.id.magniguy);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

    }
}