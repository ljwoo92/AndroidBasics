package com.example.part3_9;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etContent;
    Button btnSave;

    boolean fileReadPermission = false;
    boolean fileWritePermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);

        String state = Environment.getExternalStorageState();
        System.out.println(state);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            fileReadPermission = true;
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            fileWritePermission = true;
        }

        if (!fileReadPermission || !fileWritePermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                    },
                    200);
        }

        System.out.println(fileReadPermission);
        System.out.println(fileWritePermission);
    }

    @Override
    public void onClick(View view) {
        String content = etContent.getText().toString();
        if (fileReadPermission && fileWritePermission) {
            FileWriter writer;
            try {
                // String dirPath = Environment.getExternalStorageDirectory()
                //        .getAbsolutePath() + "/Part3_9/";
                String dirPath = ContextCompat
                        .getExternalFilesDirs(getApplicationContext(), null)[0]
                        .getAbsolutePath() + "/Part3_9";

                File dir = new File(dirPath);

                if (!dir.exists()) {
                    dir.mkdir();
                }

                File file = new File(dirPath + "/myFile.txt");
                System.out.println(file.getAbsolutePath());

                if (!file.exists()) {
                    file.createNewFile();
                }

                writer = new FileWriter(file, true);
                writer.write(content);
                writer.flush();
                writer.close();

                Intent intent = new Intent(this, ReadFileActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showToast("Permission denied. Check again the permissions for external storages");
        }
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fileReadPermission = true;
            }
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                fileWritePermission = true;
            }
        }
    }
}
