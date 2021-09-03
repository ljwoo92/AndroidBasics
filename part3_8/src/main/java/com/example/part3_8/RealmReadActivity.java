package com.example.part3_8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;

public class RealmReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_read);

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvContent = findViewById(R.id.tvContent);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        Realm mRealm = Realm.getDefaultInstance();
        MemoVO vo = mRealm.where(MemoVO.class).equalTo("title", title).findFirst();
        if (vo != null) {
            tvTitle.setText(vo.title);
            tvContent.setText(vo.content);
        } else {
            tvTitle.setText("title is empty!!");
            tvContent.setText("content is empty!!");
        }
    }
}
