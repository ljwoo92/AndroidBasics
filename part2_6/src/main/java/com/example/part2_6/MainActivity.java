package com.example.part2_6;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CheckBox) findViewById(R.id.cbRepeated)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cbVibrate)).setOnCheckedChangeListener(this);
        ((Switch) findViewById(R.id.swTimeType)).setOnCheckedChangeListener(this);
        findViewById(R.id.tvBell).setOnClickListener(this);
        findViewById(R.id.tvLabel).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (vid == R.id.tvBell) {
            showToast("bell text clicked!");
        } else if (vid == R.id.tvLabel) {
            showToast("label text clicked!");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int vid = compoundButton.getId();
        if (vid == R.id.cbRepeated) {
            showToast("repeated checkbox is " + b);
        } else if (vid == R.id.cbVibrate) {
            showToast("vibrate checkbox is " + b);
        } else if (vid == R.id.swTimeType) {
            showToast("time type switch is " + b);
        }
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    float initX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getRawX();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float diffX = initX - event.getRawX();
            if (diffX < -30) {
                showToast("you slided screen to right");
            } else if (diffX > 30) {
                showToast("you slided screen to left");
            }
        }

        return true;
    }

    long initTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTime > 3000) {
                showToast("press one more time to exit");
                initTime = System.currentTimeMillis();
            }
            else {
                finish();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
