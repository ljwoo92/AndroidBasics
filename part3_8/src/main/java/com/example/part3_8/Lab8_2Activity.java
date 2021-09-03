package com.example.part3_8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Lab8_2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText etTitle;
    EditText etContent;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab8_2);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String title = etTitle.getText().toString();
        final String content = etContent.getText().toString();

        Realm.init(this);
        RealmConfiguration conf = new RealmConfiguration.Builder().allowWritesOnUiThread(true)
                .build();
        Realm mRealm = Realm.getInstance(conf);

        mRealm.executeTransaction(realm -> {
            MemoVO vo = realm.createObject(MemoVO.class);
            vo.title = title;
            vo.content = content;
        });

        Intent intent = new Intent(this, RealmReadActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
