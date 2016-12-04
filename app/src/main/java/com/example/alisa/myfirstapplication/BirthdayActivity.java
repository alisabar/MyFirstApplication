package com.example.alisa.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayActivity extends AppCompatActivity {
    ListView mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        mainList = (ListView) findViewById(R.id.birthdayList);

        Birthday[] birthday_dates = new Birthday[0];
        try {
            birthday_dates = new Birthday[]{
                    new Birthday("Alisa",new SimpleDateFormat("yyyymmdd").parse("19910122"),new SimpleDateFormat("yyyymmdd").parse("20170122"),"Baloons"),
                    new Birthday("Alex",new SimpleDateFormat("yyyymmdd").parse("19820312"),new SimpleDateFormat("yyyymmdd").parse("20170312"),"Candies"),
            };
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // add the data to an adapter
        final ArrayAdapter<BirthdayActivity.Birthday> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, birthday_dates);

        // attach the data to the list view
        mainList.setAdapter(adapter);


    }

    private class Birthday {
        private String name;
        private Date  birthday_date;
        private Date next_birthday;
        private String comment;

        public Birthday( String name, Date  birthday_date, Date next_birthday,String comment)
        {
            setName(name);
            setBirthDay(birthday_date);
            setNextBirthday(next_birthday);
            setComment(comment);
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBirthDay(Date birthday_date) {
            this.birthday_date = birthday_date;
        }

        public void setNextBirthday(Date next_birthday) {
            this.next_birthday = next_birthday;
        }
        @Override
        public String toString() {

                return "Name: " + name+"\nBirthday date: "+birthday_date+"\nNext Birthday: "+
                        next_birthday+"\nComment: "+comment;


        }
    }
}
