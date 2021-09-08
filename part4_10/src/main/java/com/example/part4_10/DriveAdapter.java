package com.example.part4_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class DriveAdapter extends ArrayAdapter<DriveVO> {
    Context context;
    int resId;
    ArrayList<DriveVO> datas;

    public DriveAdapter(Context context, int resId, ArrayList<DriveVO> datas) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(resId, null);
            DriveHolder holder = new DriveHolder(convertView);
            convertView.setTag(holder);
        }

        DriveHolder holder = (DriveHolder) convertView.getTag();

        ImageView imgType = holder.imgType;
        TextView tvTitle = holder.tvTitle;
        TextView tvDate = holder.tvDate;
        ImageView imgMenu = holder.imgMenu;

        final DriveVO vo = datas.get(position);
        tvTitle.setText(vo.title);
        tvDate.setText(vo.date);

        if (vo.type.equals("doc")) {
            imgType.setImageDrawable(ResourcesCompat
                    .getDrawable(context.getResources(), R.drawable.ic_type_doc, null));
        } else if (vo.type.equals("file")) {
            imgType.setImageDrawable(ResourcesCompat
                    .getDrawable(context.getResources(), R.drawable.ic_type_file, null));
        } else if (vo.type.equals("img")) {
            imgType.setImageDrawable(ResourcesCompat
                    .getDrawable(context.getResources(), R.drawable.ic_type_image, null));
        }

        imgMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(context, vo.title + " menu clicked", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        return convertView;
    }
}
