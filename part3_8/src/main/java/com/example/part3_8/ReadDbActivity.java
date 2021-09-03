package com.example.part3_8;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReadDbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_db);

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvContent = findViewById(R.id.tvContent);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db
                .rawQuery("select title, content from tb_memo order by _id desc limit 1", null);

        while (cursor.moveToNext()) {
            tvTitle.setText(cursor.getString(0));
            tvContent.setText(cursor.getString(1));
        }

        cursor.close();
        db.close();
    }
}
