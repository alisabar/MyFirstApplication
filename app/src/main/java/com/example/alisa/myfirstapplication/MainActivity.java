package com.example.alisa.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainList = (ListView) findViewById(R.id.btList);

        // define data for the list
        ArrayItem[] values = new ArrayItem[]{
                new ArrayItem("Lecture 2: Calculator", CalculatorActivity.class),
                new ArrayItem("Lecture 3: ConstraintLayoutA", ConstraintLayoutA.class),
                new ArrayItem("Lecture 3: ConstraintLayoutB", ConstraintLayoutB.class),
                new ArrayItem("Lecture 3: ConstraintLayoutC", ConstraintLayoutC.class),
                new ArrayItem("Lecture 4: Birthday", BirthdayActivity.class),
        };

        // add the data to an adapter
        final ArrayAdapter<ArrayItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);

        // attach the data to the list view
        mainList.setAdapter(adapter);

        // wait for click events (this is a non-blocking call!)
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ArrayItem clicked = (ArrayItem) mainList.getItemAtPosition(position);
                if (clicked.activity == null) {
                    // nothing to do here
                    Toast.makeText(getBaseContext(), "Activity is not attached", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getBaseContext(), clicked.activity);
                    /*
                    Transition mFadeTransition =
                            TransitionInflater.from(MainActivity.this).
                                    inflateTransition(R.transition.transition1);
                    */
                    startActivity(intent);
                    overridePendingTransition(R.anim.card_flip_left_in,R.anim.card_flip_left_out);
                    //MainActivity.this.finish();


                  //  overridePendingTransition(R.anim.card_flip_left_in,R.anim.card_flip_left_out);
                }
            }
        });
    }

    public void OpenCalculator(View view)
    {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }
    public void OpenConstraintA(View view)
    {
        Intent intent = new Intent(this, ConstraintLayoutA.class);
        startActivity(intent);
    }
    public void OpenConstraintB(View view)
    {
        Intent intent = new Intent(this, ConstraintLayoutB.class);
        startActivity(intent);
    }
    public void OpenConstraintC(View view)
    {
        Intent intent = new Intent(this, BirthdayActivity.class);
        startActivity(intent);
    }
    public void OpenBirthday(View view)
    {
        Intent intent = new Intent(this, ConstraintLayoutC.class);
        startActivity(intent);
    }
    static class ArrayItem {
        private Class activity;
        private String label;

        public ArrayItem(String label, Class activity) {
            this.label = label;
            this.activity = activity;
        }

        @Override
        public String toString() {
            if (activity != null) {
                return "Open " + label;
            }
            return label;
        }
    }
}
