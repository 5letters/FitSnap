package com.alexanderverge.fitsnap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class MainActivity extends ActionBarActivity {

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Database
        SQLiteDatabase FitSnapDB = openOrCreateDatabase("FitSnap.db", MODE_PRIVATE, null);
        FitSnapDB.execSQL("CREATE TABLE IF NOT EXISTS Exercises("
                + "eid INT PRIMARY KEY, name varchar(25) NOT NULL,"
                + "type varChar(25) NOT NULL, timed boolean NOT NULL,"
                + "units boolean NOT NULL, weight DOUBLE, count DOUBLE, duration LONG)");

        FitSnapDB.execSQL("CREATE TABLE IF NOT EXISTS Workouts("
                + "wid INT PRIMARY KEY, name varchar(25) NOT NULL,"
                + "eid00 INT, eid01 INT, eid02 INT, eid03 INT,"
                + "eid04 INT, eid05 INT, eid06 INT, eid07 INT,"
                + "eid08 INT, eid09 INT, eid10 INT, eid11 INT,"
                + "eid12 INT, eid13 INT, eid14 INT, eid15 INT,"
                + "eid16 INT, eid17 INT, eid18 INT, eid19 INT,"
                + "FOREIGN KEY (eid00) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid01) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid02) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid03) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid04) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid05) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid06) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid07) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid08) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid09) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid10) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid11) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid12) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid13) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid14) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid15) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid16) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid17) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid18) REFERENCES Exercises(eid),"
                + "FOREIGN KEY (eid19) REFERENCES Exercises(eid))");

        FitSnapDB.execSQL("CREATE TABLE IF NOT EXISTS PerformedWorkout("
                + "pwid PRIMARY KEY, wid INT NOT NULL, startTime LONG NOT NULL,"
                + "FOREIGN KEY (wid) REFERENCES Workout(wid))");

        FitSnapDB.execSQL("CREATE TABLE IF NOT EXISTS PerformedExercises("
                + "peid PRIMARY KEY, pwid INT NOT NULL, eid INT NOT NULL,"
                + "reps INT, sets INT, count DOUBLE, duration LONG,"
                + "FOREIGN KEY (pwid) REFERENCES PerformedWorkout(pwid)"
                + "FOREIGN KEY (eid) REFERENCES Exercises(eid))");

        // Standard exercises
        FitSnapDB.execSQL("INSERT OR IGNORE INTO Exercises VALUES (1, 'Arm Curls', 'chest', 0, 0, 25, 10, NULL)");
        FitSnapDB.execSQL("INSERT OR IGNORE INTO Exercises VALUES (2, 'Bench Press', 'chest', 0, 0, 100, 8, NULL)");
        FitSnapDB.execSQL("INSERT OR IGNORE INTO Exercises VALUES (3, 'jog', 'stamina', 1, 0, 0, 0, 60000)");

        // Standard workouts
        FitSnapDB.execSQL("INSERT OR IGNORE INTO Workouts VALUES (1, 'Arms',"
                + "1, 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL,"
                + "NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)");
        Data lData = Data.getInstance();
        lData.setExercises(getExercisesFromDB());
        lData.setWorkouts(getWorkoutsFromDB());
        lData.setPerformedExercises(getPerformedExercisesFromDB());
        lData.setPerformedWorkouts(getPerformedWorkoutsFromDB());
    }

    // Get Exercises from database
    public ArrayList<Exercise> getExercisesFromDB(){
        ArrayList<Exercise> lExercises = new ArrayList<Exercise>();
        SQLiteDatabase FitSnapDB = openOrCreateDatabase("FitSnap.db", MODE_PRIVATE, null);
        Cursor c = FitSnapDB.rawQuery("SELECT * FROM Exercises",null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            // Set Exercise properties
            Exercise lExercise = new Exercise(c.getInt(0), c.getString(1), c.getString(2),
                    c.getInt(3)>0, c.getInt(4)>0, c.getDouble(5),
                    c.getDouble(6), c.getLong(7));

            // Add Exercise to list
            lExercises.add(lExercise);
            c.moveToNext();
        }
        return lExercises;
    }

    public ArrayList<PerformedExercise> getPerformedExercisesFromDB(){
        ArrayList<PerformedExercise> lPerformedExercises = new ArrayList<PerformedExercise>();
        SQLiteDatabase FitSnapDB = openOrCreateDatabase("FitSnap.db", MODE_PRIVATE, null);
        Cursor c = FitSnapDB.rawQuery("SELECT * FROM PerformedExercises",null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            // Set Exercise properties
            PerformedExercise lPerformedExercise = new PerformedExercise(c.getInt(0),
                    c.getInt(1), Data.getInstance().getExerciseById(c.getInt(2)), c.getInt(3), c.getInt(4), c.getDouble(5), c.getLong(6));

            // Add Exercise to list
            lPerformedExercises.add(lPerformedExercise);
            c.moveToNext();
        }
        return lPerformedExercises;
    }

    // Get Workouts from database
    public ArrayList<Workout> getWorkoutsFromDB(){
        ArrayList<Workout> lWorkouts = new ArrayList<Workout>();
        SQLiteDatabase FitSnapDB = openOrCreateDatabase("FitSnap.db", MODE_PRIVATE, null);
        Cursor c = FitSnapDB.rawQuery("SELECT * FROM Workouts",null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            ArrayList<Exercise> workoutExercises = new ArrayList<Exercise>();
            for (int i = 0; i < 20; i++)
            {
                Integer id = c.getInt(i+2);
                if (id != null)
                {
                    Exercise lExercise = Data.getInstance().getExerciseById(id);
                    if (lExercise != null)
                    {
                        workoutExercises.add(lExercise);
                    }
                }
            }

            // Set Exercise properties
            Workout lWorkout = new Workout(c.getInt(0), c.getString(1), workoutExercises);

            // Add Exercise to list
            lWorkouts.add(lWorkout);
            c.moveToNext();
        }
        return lWorkouts;
    }

    public ArrayList<PerformedWorkout> getPerformedWorkoutsFromDB(){
        ArrayList<PerformedWorkout> lPerformedWorkouts = new ArrayList<PerformedWorkout>();
        SQLiteDatabase FitSnapDB = openOrCreateDatabase("FitSnap.db", MODE_PRIVATE, null);
        Cursor c = FitSnapDB.rawQuery("SELECT * FROM PerformedExercises",null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            // Set Exercise properties
            PerformedWorkout lPerformedWorkout = new PerformedWorkout(c.getInt(0),
                    Data.getInstance().getWorkoutById(c.getInt(2)), c.getLong(3));

            // Add Exercise to list
            lPerformedWorkouts.add(lPerformedWorkout);
            c.moveToNext();
        }
        return lPerformedWorkouts;
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

    public void startWorkout(View view){
        Intent myIntent = new Intent(this, WorkoutSelectionActivity.class);
        startActivity(myIntent);
    }

    public void startAnalytics(View view){
        Intent myIntent = new Intent(this, AnalyticsActivity.class);
        startActivity(myIntent);
    }

    public void editWorkout(View view){
        Intent myIntent = new Intent(this, EditWorkoutSelectionActivity.class);
        startActivity(myIntent);
    }

    public void editExercise(View view){
        Intent myIntent = new Intent(this, EditExerciseSelectionActivity.class);
        startActivity(myIntent);
    }

    public void testAnalytics(View view){
        Intent myIntent = new Intent(this, SimplePieChartActivity.class);
        startActivity(myIntent);
    }

    /*public void addListenerOnButton() {
        imageButton = (ImageButton) findViewById(R.id.imageButton1);
    }*/
}
