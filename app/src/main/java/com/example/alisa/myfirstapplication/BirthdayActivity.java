package com.example.alisa.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayActivity extends AppCompatActivity {
    ListView mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        UiHandler.post(new Runnable() {
                           @Override
                           public void run() {
                               final Birthday[] birthday_dates = DataBase.getDb(BirthdayActivity.this).getBirthdays();
                               BirthdayActivity.this.runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       SetListData(birthday_dates);
                                   }
                               });
                           }
                       }

        );
    }
    public void send(View view)
    {
        final Intent intent = new Intent(this, ActivitySetFields.class);
        startActivity(intent);
    }

    private void SetListData(Birthday[] birthday_dates) {
        mainList = (ListView) findViewById(R.id.birthdayList);


        // add the data to an adapter
        final ArrayAdapter<Birthday> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, birthday_dates);

        // attach the data to the list view
        mainList.setAdapter(adapter);
    }


}
