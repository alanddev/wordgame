package com.alanddev.wordgame;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button drag ;
    Button dragtwo ;
    Button dropthree;
    Button dropfour;
    Button dropfive;
    LinearLayout drop;
    TextView text;
    TextView sucess;
    int failure;
    int total;
    int currentCol;

    String []words = new String []{"A","","C","T","I","V","I","T","Y"};
    int row = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drag = (Button)findViewById(R.id.one);
        dragtwo = (Button)findViewById(R.id.two);


        drag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                // TODO Auto-generated method stub
                return dragEvent(v, "A", drag);

            }
        });

        dragtwo.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                // TODO Auto-generated method stub
                return dragEvent(v,"B",dragtwo);

            }
        });


        LinearLayout layoutBoard = (LinearLayout)findViewById(R.id.gridcell);

        int nRows = words.length/row;

        for (int i = 0 ; i < nRows ;i++){
            LinearLayout layoutRow = new LinearLayout(this);
            layoutRow.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j<row; j++) {
                final Button button = new Button(this);
                button.setBackgroundResource(R.mipmap.button);
                button.setLayoutParams(new LinearLayout.LayoutParams(160, 140));
                button.setTextSize(30);
                currentCol = (i*nRows) + j;
                button.setTag(words[currentCol]);

                button.setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        // TODO Auto-generated method stub
                        return dropEvent(event, button);
                    }
                });
                layoutRow.addView(button);
            }
            layoutBoard.addView(layoutRow);
        }

    }


    private boolean dropEvent(DragEvent event, Button button){
        final int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            // ClipData only get in ACTION_DROP
            case DragEvent.ACTION_DROP: {
                ClipData dataClip = event.getClipData();
                String data = "";
                if (dataClip !=null) {
                    data = dataClip.getItemAt(0).getText().toString();
                }
                String tag = button.getTag().toString();
                if (!tag.equals(data)) {
                    button.setText("W");
                }else{
                    button.setText(data);
                }
                return (true);
            }

            case DragEvent.ACTION_DRAG_ENDED: {
                return (true);
            }
            default:
                break;
        }
        return true;


    }

    private boolean dragEvent(View v, String value,Button button){
        ClipData data = ClipData.newPlainText("name", value);
        View.DragShadowBuilder shadow = new View.DragShadowBuilder(button);
        v.startDrag(data, shadow, null, 0);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
