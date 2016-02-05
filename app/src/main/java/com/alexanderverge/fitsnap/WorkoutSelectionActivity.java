package com.alexanderverge.fitsnap;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class WorkoutSelectionActivity extends ActionBarActivity {

    private ArrayAdapter<IntentData> todoListAdapter;
    private ArrayList<IntentData> todoListItems = new ArrayList<IntentData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_selection);

        final ListView todoList = (ListView) findViewById(R.id.todo_list);

        for (Workout w : Data.getInstance().getWorkouts())
        {
            todoListItems.add(new IntentData(w.getName(), w.getWid()));
        }

        todoListAdapter = new ArrayAdapter<IntentData>(
                this,
                R.layout.todo_item,
                todoListItems);

        todoList.setAdapter(todoListAdapter);

        /*
        Button addButton = (Button) findViewById(R.id.add_button);
        final Intent intent = new Intent(this, AddToDoItem.class);

        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(intent);
                    }
                }
        );
        */

        final Intent start = new Intent(this, WorkoutActivity.class);
        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                IntentData data = (IntentData) todoList.getItemAtPosition(position);
                start.putExtra("wid", data.getId());
                startActivity(start);
            }
        });


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
}
