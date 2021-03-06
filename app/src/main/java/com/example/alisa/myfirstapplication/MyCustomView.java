package com.example.alisa.myfirstapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MyCustomView extends View {

    private Paint paint=new Paint();
    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(0xff_00_00_ff);
    }
    private void yellow() {
        paint.setColor(0xff_ff_ff_00);
    }

    @Override
    protected void onDraw(Canvas canvas) {

       // canvas.translate(0,100);
        canvas.rotate(-15);
        canvas.scale((getWidth()/320)*0.9f,(getHeight()/100)*0.9f);
        canvas.drawLine(0,0,0,100,paint);//
        canvas.drawLine(0,50,50,50,paint);//H
        canvas.drawLine(50,0,50,100,paint);//H
        canvas.drawLine(70,0,70,100,paint);//E
        canvas.drawLine(70,0,120,0,paint);//E
        canvas.drawLine(70,50,120,50,paint);
        canvas.drawLine(70,100,120,100,paint);//E
        canvas.drawLine(140,0,140,100,paint);
        canvas.drawLine(140,100,190,100,paint);
        canvas.drawLine(210,0,210,100,paint);
        canvas.drawLine(210,100,260,100,paint);
        canvas.drawOval(270,0,320,100,paint);
        yellow();
        canvas.drawOval(272,2,318,98,paint);

    }


}
