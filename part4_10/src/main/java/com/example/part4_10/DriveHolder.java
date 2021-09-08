package com.example.part4_10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DriveHolder {
    public ImageView imgType;
    public TextView tvTitle;
    public TextView tvDate;
    public ImageView imgMenu;

    public DriveHolder(View root) {
        imgType = root.findViewById(R.id.imgType);
        tvTitle = root.findViewById(R.id.tvTitle);
        tvDate = root.findViewById(R.id.tvDate);
        imgMenu = root.findViewById(R.id.imgMenu);
    }
}
