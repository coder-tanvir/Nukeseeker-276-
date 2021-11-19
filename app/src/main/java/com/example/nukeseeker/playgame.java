package com.example.nukeseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.nukeseeker.option.column;
import static com.example.nukeseeker.option.getnumberofmines;
import static com.example.nukeseeker.option.getnumberofrows;

public class playgame extends AppCompatActivity {
    Minemanager manager;
    int num_scans = 0;
    int num_found = 0;
    Button buttons[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);

        checkSharedPreferences();
        manager=Minemanager.getInstance();
        populateButtons();

        updateUI();
        

    }

    private void updateUI() {
        manager = Minemanager.getInstance();
        TextView found = (TextView) findViewById(R.id.textView7);
        TextView totalScans = (TextView) findViewById(R.id.textView8);

        found.setText(" " + num_found+ " of " + (manager.getNumberofmines())+" Mines");
        //Toast.makeText(this,""+ num_scans,Toast.LENGTH_SHORT).show();
        totalScans.setText("Scans Used: " + num_scans);
        for (int i = 0; i < manager.getRow(); i++) {
            for (int j = 0; j < manager.getColumn(); j++) {
                updateRowsAndCols(i, j);
            }
        }
    }




    private void populateButtons() {
        manager = Minemanager.getInstance();
        buttons = new Button[manager.getRow()][manager.getColumn()];
        manager.populateMine();
        TableLayout table = (TableLayout) findViewById(R.id.tableforbuttons);
        for (int i = 0; i < manager.getRow(); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));
            table.addView(tableRow);

            for (int j = 0; j < manager.getColumn(); j++) {
                final int finalcol = j;
                final int finalrow = i;
                Button button=new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                1.0f));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mine temp=manager.getMines(finalrow,finalcol);
                        if(temp.isPresent()) {
                            if(temp.isRevealed()){
                                num_scans++;
                                manager.getMines(finalrow,finalcol).showsText();
                                updateUI();

                            }else{
                                num_found++;
                                setMineRevealed(finalrow,finalcol);
                                updateUI();
                            }
                        }else{
                            num_scans++;
                            manager.getMines(finalrow,finalcol).setRevealed();
                            manager.getMines(finalrow,finalcol).showsText();
                            updateUI();
                        }
                        }

                });


                tableRow.addView(button);
                buttons[i][j]=button;

            }
        }
    }

    private void lockButtonSizes() {
        manager = Minemanager.getInstance();
        for (int i = 0; i < manager.getRow(); i++) {
            for (int j = 0; j < manager.getColumn(); j++) {
                Button button = buttons[i][j];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }



    private void checkSharedPreferences() {
        manager=Minemanager.getInstance();
        int savedcount=getnumberofmines(this);
        manager.setNumberofmines(savedcount);
        int savedrow=getnumberofrows(this);
        manager.setRow(savedrow);
        int savedcolumn=column(this);
        manager.setColumn(savedcolumn);

    }
    private void setMineRevealed(int x, int y) {
        manager = Minemanager.getInstance();
        manager.getMines(x, y).setRevealed();
        Button button = buttons[x][y];

        //Make text not clip on small buttons
        button.setPadding(0, 0, 0, 0);

        // Lock button sizes
        lockButtonSizes();

        // Scale background to button
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.download);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
        button.setAlpha(1);


    }

    private void updateRowsAndCols(int x, int y) {
        manager = Minemanager.getInstance();



        int count = 0;
        for (int i = 0; i < manager.getColumn(); i++) {
            Mine temp = manager.getMines(x, i);
            if (temp.isPresent() && !(temp.isRevealed())) {
                count++;
                //Toast.makeText(this,"COussssss"+count ,Toast.LENGTH_SHORT).show();

            }
        }

        for (int j = 0; j < manager.getRow(); j++) {
            Mine temp = manager.getMines(j, y);
            if (temp.isPresent() && !(temp.isRevealed())) {
                count++;
            }

        }

        if (num_found >= manager.getNumberofmines()) {


            //Toast.makeText(this,"yyyyyyyyyyy",Toast.LENGTH_SHORT).show();
            if (manager.getMines(x, y).isRevealed())  {
                Button button = buttons[x][y];
                button.setText("" + count);
                button.setAlpha(1);
            }
            //gameOver();
        } else {
            if (manager.getMines(x, y).isRevealed())  {
                Button button = buttons[x][y];
                button.setText( ""+count);
                button.setAlpha(1);
                //button.setTextSize(18);
                if (manager.getMines(x, y).isPresent()) {
                    button.setTextColor(Color.parseColor("#ffffff"));
                }
                //button.startAnimation(animation);
            }
        }
    }




}