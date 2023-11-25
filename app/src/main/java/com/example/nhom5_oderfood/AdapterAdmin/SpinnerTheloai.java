package com.example.nhom5_oderfood.AdapterAdmin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhom5_oderfood.DTO.Theloai;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;

public class SpinnerTheloai extends BaseAdapter {
    private Context context;
    ArrayList<Theloai> list_tl;
    public SpinnerTheloai(Context context, ArrayList<Theloai> list_tl) {
        this.context = context;
        this.list_tl = list_tl;
    }

    public Theloai getSelectedItem(int position) {
        return list_tl.get(position);
    }

    private class ViewHolder {
        TextView idTextView;
        TextView tenTextView;
    }

    @Override
    public int getCount() {
        return list_tl.size();
    }

    @Override
    public Object getItem(int position) {
        return list_tl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
            holder = new ViewHolder();
            holder.idTextView = convertView.findViewById(R.id.id_tl);
            holder.tenTextView = convertView.findViewById(R.id.name_tl);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Theloai theloai = list_tl.get(position);
        holder.idTextView.setText(theloai.getMaTL());
        holder.tenTextView.setText(theloai.getTenTheLoai());

        return convertView;
    }
}