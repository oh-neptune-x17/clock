package com.example.clock;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Pointer extends View {

    private Paint p;
    private static float xSec =0;
    private static float ySec =0;
    private static float xMinute =0;
    private static float yMinute =0;
    private static float xHour =0;
    private static float yHour =0;
    static int[] numbers  = {0,1,2,3,4,5,6,7,8,9,10,11};

    public Pointer(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLACK);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(550,750,400,p);
        p.setColor(Color.WHITE);
        canvas.drawCircle(550,750,390,p);
        p.setColor(Color.RED);
        p.setStrokeWidth(2);
        canvas.drawLine(xSec, ySec, 550, 750, p);
        p.setStrokeWidth(4);
        canvas.drawLine(xMinute, yMinute, 550, 750, p);
        canvas.drawLine(xHour, yHour, 550, 750, p);

        for(int number : numbers){
            p.setTextSize(50);
            p.setColor(Color.GRAY);
            double angle = Math.PI * number / 6 - Math.PI / 2 + Math.PI/6;
            int x =(int) (getWidth()/2+Math.cos(angle) * 360);
            int y =(int) (getHeight()/2+Math.sin(angle) * 360);
            canvas.drawText(String.valueOf(number+1),x-8,y-100,p);
        }
        invalidate();

    }

    public static void repaint(int sec,int min,int hour) {

        float angle = (float) (6 * sec * Math.PI / 180);
        float x1 = 550;
        float y1 = 350;
        float x2 = 550;
        float y2 = 750;
        xSec = (float) ((y2 - y1) * Math.sin(angle) + x2);
        ySec = (float) ((y1 - y2)* Math.cos(angle)+ y2);

        angle = (float) (6 * min * Math.PI / 180);
        xMinute = (float) (-(y1 - y2 +60) * Math.sin(angle) + x2);
        yMinute = (float) ((y1 - y2 +60)* Math.cos(angle)+ y2);

        angle = (float) (30 * hour * Math.PI / 180);
        xHour = (float) (-(y1 - y2 +200) * Math.sin(angle) + x2);
        yHour = (float) ((y1 - y2 +200)* Math.cos(angle)+ y2);





    }
}
