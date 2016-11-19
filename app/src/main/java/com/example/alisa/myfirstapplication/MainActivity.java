package com.example.alisa.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent intent = new Intent(this, ConstraintLayoutC.class);
        startActivity(intent);
    }
}
