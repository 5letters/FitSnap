package com.alexanderverge.fitsnap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WorkoutActivity extends ActionBarActivity {

    private Workout aWorkout;
    private int exerciseCount = 0;
    int pwid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            int wid = extras.getInt("wid");
            aWorkout = Data.getInstance().getWorkoutById(wid);
            nextActivity();
        }

        SQLiteDatabase FitSnapDB = openOrCreateDatabase("FitSnap.db", MODE_PRIVATE, null);
        FitSnapDB.execSQL("INSERT INTO PerformedWorkout (wid,startTime) VALUES (" + aWorkout.getWid() + "," + System.currentTimeMillis() +")");
        Cursor c = FitSnapDB.rawQuery("SELECT last_insert_rowid()", null);
        c.moveToFirst();
        pwid = c.getInt(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout, menu);
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

    public void enterInfo(View v)
    {
        Intent intentInfo = new Intent(this, EnterExerciseInformationActivity.class);
        intentInfo.putExtra("pwid", pwid);
        intentInfo.putExtra("eid", aWorkout.getExercises().get(exerciseCount).getEid());
        startActivity(intentInfo);

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {}
            public void onFinish()
            {
                exerciseCount++;
                nextActivity();

            }
        }.start();
    }

    private void nextActivity()
    {
        if (exerciseCount >= aWorkout.getExercises().size())
        {
            // TODO: present congratulations on finishing workout
            finish();
            return;
        }
        TextView activityText = (TextView) findViewById(R.id.activity_text);
        TextView amount = (TextView) findViewById(R.id.amount);
        Exercise lExercise = aWorkout.getExercises().get(exerciseCount);
        activityText.setText(String.format("Activity: " + lExercise.getName()));
        if (lExercise.isTimed())
        {
            amount.setText(String.format("Time: " + lExercise.getDuration()/1000/60) + ":" + (lExercise.getDuration()/1000)%60);
        }
        else
        {
            amount.setText(String.format("Reps: " + lExercise.getCount()));
        }
    }
}
