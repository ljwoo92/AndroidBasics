package com.example.part2_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Lab3_3Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnTrue;
    Button btnFalse;
    TextView tvTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_3);

        btnTrue = findViewById(R.id.btn_visible_true);
        btnFalse = findViewById(R.id.btn_visible_false);
        tvTarget = findViewById(R.id.text_visible_target);

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnTrue) {
            tvTarget.setVisibility(View.VISIBLE);
        } else if (v == btnFalse) {
            tvTarget.setVisibility(View.INVISIBLE);
        } else {
            return;
        }
    }
}