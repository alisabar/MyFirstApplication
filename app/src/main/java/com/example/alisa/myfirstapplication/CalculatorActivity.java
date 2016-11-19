package com.example.alisa.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.alisa.myfirstapplication.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
    }
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, CalculatorDisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_number);
        EditText editText2 = (EditText) findViewById(R.id.edit_second_number);
        int num=Integer.parseInt(String.valueOf(editText.getText()));
       // int num= Integer.parseInt(String.valueOf((EditText) findViewById(R.id.edit_number)));
     //   int num =findViewById(R.id.edit_number);
        int num2=Integer.parseInt(String.valueOf(editText2.getText()));
      //  int num2=Integer.parseInt(String.valueOf((EditText) findViewById(R.id.edit_second_number)));
        num2+=num;
        String message =String.valueOf(num2);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
