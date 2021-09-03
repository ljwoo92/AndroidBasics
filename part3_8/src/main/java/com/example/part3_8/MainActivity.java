package com.example.part3_8;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etTitle;
    EditText etContent;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into tb_memo (title, content) values (?, ?)",
                new String[]{title, content});
        db.close();

        Intent intent = new Intent(this, ReadDbActivity.class);
        startActivity(intent);
    }
}
