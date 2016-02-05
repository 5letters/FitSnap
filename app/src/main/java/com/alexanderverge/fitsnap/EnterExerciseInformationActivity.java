package com.alexanderverge.fitsnap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class EnterExerciseInformationActivity extends ActionBarActivity {

    private int pwid = 0;
    private int eid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_exercise_information);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            pwid = extras.getInt("pwid");
            eid = extras.getInt("eid");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_exercise_information, menu);
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

    public void takeBreak(View view)
    {
        TextView sets = (TextView) findViewById(R.id.sets);
        TextView reps = (TextView) findViewById(R.id.reps);
        TextView weight = (TextView) findViewById(R.id.weight);
        TextView hours = (TextView) findViewById(R.id.hours);
        TextView minutes = (TextView) findViewById(R.id.minutes);
        TextView seconds = (TextView) findViewById(R.id.seconds);

        String stext = sets.getText().toString();
        String rtext = reps.getText().toString();
        String wtext = weight.getText().toString();
        String hstext = hours.getText().toString();
        String mstext = minutes.getText().toString();
        String sstext = seconds.getText().toString();

        int s  = (stext.equals("")) ? -1 : Integer.parseInt(stext);
        int r  = (rtext.equals("")) ? -1 : Integer.parseInt(rtext);
        int w  = (wtext.equals("")) ? -1 : Integer.parseInt(wtext); // TODO: use this
        int hs = (hstext.equals("")) ? -1 : Integer.parseInt(hstext);
        int ms = (mstext.equals("")) ? -1 : Integer.parseInt(mstext);
        int ss = (sstext.equals("")) ? -1 : Integer.parseInt(sstext);
        int t;
        if (hs < 0 || ms < 0  || ss < 0)
        {
            t = 0;
        }
        else
        {
            t = (hs * 60 * 60 + ms * 60 + ss) * 1000;
        }
        int c = 0; // TODO: Where does this come from?

        SQLiteDatabase FitSnapDB = openOrCreateDatabase("FitSnap.db", MODE_PRIVATE, null);
        FitSnapDB.execSQL("INSERT INTO PerformedExercises"
                        + "(pwid, eid, reps, sets, count, duration) VALUES ("
                        +  pwid + "," + eid + "," + r +"," + s +"," + c + "," + t + ")");

        Intent intentBreak = new Intent(this, BreakTimerActivity.class);
        startActivity(intentBreak);
        finish();
    }
}
