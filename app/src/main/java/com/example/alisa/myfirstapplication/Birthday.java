package com.example.alisa.myfirstapplication;

import java.util.Date;

/**
 * Created by Alisa on 12/5/2016.
 */

public class Birthday {
    private String name;
    private Date birthday_date;
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
