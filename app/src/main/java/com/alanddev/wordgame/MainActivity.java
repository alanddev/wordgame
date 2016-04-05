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
        dropthree = (Button)findViewById(R.id.three);
        dropfour = (Button)findViewById(R.id.four);
        dropfive = (Button)findViewById(R.id.five);
        dropthree.setTag(new String("A"));
        dropfour.setTag(new String("B"));
        dropfive.setTag(new String("C"));
        drop = (LinearLayout)findViewById(R.id.bottomlinear2);


        dropthree.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                // TODO Auto-generated method stub
                return dropEvent(event,dropthree);
            }
        });

        dropfour.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                // TODO Auto-generated method stub
                return dropEvent(event,dropfour);
            }
        });


        dropfive.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                // TODO Auto-generated method stub
                return dropEvent(event,dropfive);
            }
        });



        drag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                // TODO Auto-generated method stub
                return dragEvent(v,"A",drag);

            }
        });

        dragtwo.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                // TODO Auto-generated method stub
                return dragEvent(v,"B",dragtwo);

            }
        });


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
