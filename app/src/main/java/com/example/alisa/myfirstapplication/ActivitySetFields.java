package com.example.alisa.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivitySetFields extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_fields);
    }
    public void sendMessage(View view) {

        final Intent intent = new Intent(this, BirthdayActivity.class);
        EditText name = (EditText) findViewById(R.id.edit_name);
        DatePicker date = (DatePicker) findViewById(R.id.datePicker);
        EditText comment = (EditText) findViewById(R.id.edit_comment);

        //try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,date.getYear());
            calendar.set(Calendar.MONTH,date.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH,date.getYear());
            final Date new_date= calendar.getTime();// new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(date.getYear()+date.getMonth()+date.getDayOfMonth()));
            final String new_name= String.valueOf(name.getText());
            final String new_comment= String.valueOf(comment.getText());
            UiHandler.post(new Runnable() {
                               @Override
                               public void run() {
                                   DataBase.getDb(ActivitySetFields.this).addRow(new_name,new_date,new_comment);
                                   startActivity(intent);
                               }
                           }

            );
            /*
        } catch (ParseException e) {
            Log.e(ActivitySetFields.class.getSimpleName(),"Error in setting fields",e);
        }
        */


    }
}
