package com.example.part3_9;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);

        TextView tv = findViewById(R.id.tvResult);

        String dirPath = ContextCompat
                .getExternalFilesDirs(getApplicationContext(), null)[0]
                .getAbsolutePath() + "/Part3_9";

        File file = new File(
                dirPath + "/myFile.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer buf = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                buf.append(line);
            }

            tv.setText(buf.toString());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
