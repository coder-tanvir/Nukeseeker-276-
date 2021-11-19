package com.example.nukeseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class option extends AppCompatActivity {
    private Minemanager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        createRadioButtonmines();
        manager=Minemanager.getInstance();
        int savedminecount = getnumberofmines(this);
        Toast.makeText(this, "Saved value" + savedminecount,Toast.LENGTH_SHORT).show();

       createRadioButtonboardsize();
       manager=Minemanager.getInstance();
       int savedcol=column(this);
       int savedrow=getnumberofrows(this);
       Toast.makeText(this,"Saved row and column"+savedcol + "lalala"+savedrow,Toast.LENGTH_SHORT).show();
    }

    private void createRadioButtonboardsize() {
        RadioGroup groupsize = (RadioGroup) findViewById(R.id.radiogroupboardsize);
        int[] boardrow=getResources().getIntArray(R.array.board_rows);
        int[] boardcol=getResources().getIntArray(R.array.board_cols);
        for(int j=0;j<boardrow.length;j++) {
                final int rowsize = boardrow[j];
                final int colsize = boardcol[j];

                final RadioButton boardsizebutton=new RadioButton(this);
                boardsizebutton.setText(rowsize + " Rows" +" * "+ colsize + " Columns");
                boardsizebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        manager.setRow(rowsize);
                        manager.setColumn(colsize);
                        Toast.makeText(option.this,"You clicked" + colsize + " AND ROWS" + rowsize,Toast.LENGTH_SHORT).show();
                        savenumberofrows(rowsize);
                        save(colsize);
                        manager.populateMine();
                    }
                });
                groupsize.addView(boardsizebutton);

        }
    }

    private void save(int colsize) {
        SharedPreferences pref=this.getSharedPreferences("Apppreferences",MODE_PRIVATE);
        SharedPreferences.Editor editcolumn=pref.edit();
        editcolumn.putInt("column",colsize);
        editcolumn.apply();
    }
    static public int column(Context context){
        SharedPreferences pref=context.getSharedPreferences("Apppreferences",MODE_PRIVATE);
        return pref.getInt("column",399);
    }


    private void savenumberofrows(int rowsize) {
        SharedPreferences prefs=this.getSharedPreferences("Appprefs",MODE_PRIVATE);
        SharedPreferences.Editor editorrow=prefs.edit();
        editorrow.putInt("Numcountrow",rowsize);
        editorrow.apply();

    }
    static public int getnumberofrows(Context context){
        SharedPreferences prefs=context.getSharedPreferences("Appprefs",MODE_PRIVATE);
        return prefs.getInt("Numcountrow",0);

    }

    private void createRadioButtonmines() {
        RadioGroup groupmine=(RadioGroup)findViewById(R.id.radiogroupmines);
        int[] nummines=getResources().getIntArray(R.array.numberofmines);
        for(int i=0; i<nummines.length;i++){
            final int minearr=nummines[i];

            RadioButton buttonmine=new RadioButton(this);
            buttonmine.setText(minearr + " Mines on the Board");
            buttonmine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setNumberofmines(minearr);
                    Toast.makeText(option.this,"You clicked" + minearr,Toast.LENGTH_SHORT).show();


                    savenumberofmines(minearr);
                    manager.populateMine();

                }
            });

            groupmine.addView(buttonmine);


        }
    }

    private void savenumberofmines(int minearr) {
        SharedPreferences prefs=this.getSharedPreferences("Appprefs",MODE_PRIVATE);
        SharedPreferences.Editor editormine=prefs.edit();
        editormine.putInt("Numcountmines",minearr);
        editormine.apply();
    }

    static public int getnumberofmines(Context context){
        SharedPreferences prefs=context.getSharedPreferences("Appprefs",MODE_PRIVATE);
        return prefs.getInt("Numcountmines",0);
    }





}