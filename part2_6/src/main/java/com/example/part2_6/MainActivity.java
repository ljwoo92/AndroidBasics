package com.example.part2_6;

import android.os.Bundle;
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
}
