package com.example.alisa.myfirstapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Alisa on 12/17/2016.
 */

public class MySecondCustomView extends View {
    private Paint paint=new Paint();
    public MySecondCustomView(Context context) {
        super(context);
        init();
    }

    public MySecondCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySecondCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        canvas.scale(getWidth()/320, getHeight()/100);
        //canvas.translate(0,100);
        canvas.drawLine(0,0,0,100,paint);//H
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
