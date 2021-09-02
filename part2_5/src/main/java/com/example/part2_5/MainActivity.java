package com.example.part2_5;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnVibrate;
    Button btnBeep;
    Button btnCustom;
    Button btnAlert;
    Button btnList;
    Button btnDate;
    Button btnTime;
    Button btnCustomDialog;

    AlertDialog dlgCustom;
    AlertDialog dlgList;
    AlertDialog dlgAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnVibration).setOnClickListener(this);
        findViewById(R.id.btnBeep).setOnClickListener(this);
        findViewById(R.id.btnCustom).setOnClickListener(this);
        findViewById(R.id.btnAlert).setOnClickListener(this);
        findViewById(R.id.btnList).setOnClickListener(this);
        findViewById(R.id.btnDate).setOnClickListener(this);
        findViewById(R.id.btnTime).setOnClickListener(this);
        findViewById(R.id.btnCustomDialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btnVibration) {
            Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vib.vibrate(1000);
        } else if (viewId == R.id.btnBeep) {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        } else if (viewId == R.id.btnCustom) {
            MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.fallbackring);
            player.start();
        } else if (viewId == R.id.btnAlert) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("Notice");
            builder.setMessage("close AlertDialog?");
            builder.setPositiveButton("Ok", dialogListener);
            builder.setNegativeButton("No", null);

            dlgAlert = builder.create();
            dlgAlert.show();
        } else if (viewId == R.id.btnList) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.ic_launcher_foreground);
            builder.setTitle("Alarm bells");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);
            builder.setPositiveButton("Ok", null);
            builder.setNegativeButton("Cancel", null);

            dlgList = builder.create();
            dlgList.show();
        } else if (viewId == R.id.btnDate) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (datePicker, i, i1, i2) -> showToast(i + ":" + (i1 + 1) + ":" + i2), year,
                    month, day);
            datePickerDialog.show();
        } else if (viewId == R.id.btnTime) {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (timepicker, i, i1) -> showToast(i + ":" + i1), hour, minute, false);
            timePickerDialog.show();
        } else if (viewId == R.id.btnCustomDialog) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.dialog_layout, null);
            builder.setView(v);
            builder.setPositiveButton("Ok", dialogListener);
            builder.setNegativeButton("No", null);

            dlgCustom = builder.create();
            dlgCustom.show();
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (dialogInterface == dlgCustom && i == DialogInterface.BUTTON_POSITIVE) {
                showToast("custom dialog! OK clicked...");
            } else if (dialogInterface == dlgList) {
                String[] datas = getResources().getStringArray(R.array.dialog_array);
                showToast(datas[i] + " is selected.");
            } else if (dialogInterface == dlgAlert && i == DialogInterface.BUTTON_POSITIVE) {
                showToast("alert dialog! Ok clicked.");
            }
        }
    };
}
