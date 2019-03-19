package com.example.clock;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Date;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public int sec=0;
    public int minute=0;
    public int hour=0;

    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.clock);


        Thread t = new Thread(new ClockThread(new Handler()));
        t.start();
    }

    class ClockThread implements Runnable {
        private Handler handler;

        public ClockThread(Handler h)
        {
            handler = h;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);

                    Date date = new Date();
                    Format formatter = new SimpleDateFormat("hh:mm:ss a");
                    final String s = formatter.format(date);
                    sec= date.getSeconds();
                    minute=date.getMinutes();
                    hour = date.getHours();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(s);
                            Pointer.repaint(sec,minute,hour);

                        }
                    });

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}