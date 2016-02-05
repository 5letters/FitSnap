package com.alexanderverge.fitsnap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;


public class EditWorkoutSelectionActivity extends ActionBarActivity {
    public final static String SELECTED_WID= "com.alexanderverge.fitsnap.WID";
    private ListView workoutView;
    private ArrayList<Workout> workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout_selection);

        workoutView = (ListView) findViewById(R.id.list_edit_workout_selection);
        workoutList = Data.getInstance().getWorkouts();

        WorkoutAdapter workoutAdt = new WorkoutAdapter(this, workoutList);
        workoutView.setAdapter(workoutAdt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_workout_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_new_workout){
            //TODO launch edit workout with blank workout
            Intent myIntent = new Intent(this, EditWorkoutActivity.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void workoutPicked(View view){
        final int wid = workoutList.get(Integer.parseInt(view.getTag().toString())).getWid();
        final String[] items = new String[]{"Edit", "Delete"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Do what with " + workoutList.get(Integer.parseInt(view.getTag().toString())).getName() + "?");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    editWorkout(wid);
                }else{
                    //TODO DELETE WORKOUT
                }
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void editWorkout(int wid){
        Intent myIntent = new Intent(this, EditWorkoutActivity.class);
        myIntent.putExtra(SELECTED_WID, wid);
        startActivity(myIntent);
    }

}
