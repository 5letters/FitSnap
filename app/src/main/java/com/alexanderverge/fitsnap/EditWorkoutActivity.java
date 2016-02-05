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
import android.widget.TextView;

import java.util.ArrayList;


public class EditWorkoutActivity extends ActionBarActivity {
    public final static String SELECTED_EID= "com.alexanderverge.fitsnap.EID";
    private ListView exerciseView;
    private ArrayList<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        Intent intent = getIntent();
        int wid = intent.getIntExtra(EditWorkoutSelectionActivity.SELECTED_WID, 12);

        exerciseView = (ListView) findViewById(R.id.list_edit_workout);
        exerciseList = Data.getInstance().getWorkoutById(wid).getExercises();

        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(this, exerciseList);
        exerciseView.setAdapter(exerciseAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_workout, menu);
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

        if(id == R.id.action_add_exercise){
            Intent myIntent = new Intent(this, AddExerciseSelectionActivity.class);
            startActivity(myIntent);
            Log.d("Action", "add exercise");
            return true;
        }

        if(id == R.id.action_save_workout){
            //TODO SAVE WORKOUT HERE
            Log.d("Action", "save workout");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void exercisePicked(View view){
        final int eid = exerciseList.get(Integer.parseInt(view.getTag().toString())).getEid();
        final String[] items = new String[]{"Edit", "Delete", "Add"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Do what with " + exerciseList.get(Integer.parseInt(view.getTag().toString())).getName() + "?");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    editExercise(eid);
                } else if (which == 1) {
                    //TODO DELETE WORKOUT
                } else {
                    //TODO CREATE AND THEN EDIT BLANK EXERCISE
                }
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void editExercise(int eid){
        Intent myIntent = new Intent(this, EditExerciseActivity.class);
        myIntent.putExtra(SELECTED_EID, eid);
        startActivity(myIntent);
    }
}
