package com.example.part2_5;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnVibrate;
    Button btnBeep;
    Button btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVibrate = findViewById(R.id.btnVibration);
        btnBeep = findViewById(R.id.btnBeep);
        btnCustom = findViewById(R.id.btnCustom);
    }

    @Override
    public void onClick(View view) {
        if (view == btnVibrate) {
            Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
            vib.vibrate(1000);
        } else if (view == btnBeep) {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        } else if (view == btnCustom) {
            MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.fallbackring);
            player.start();
        }
    }
}
