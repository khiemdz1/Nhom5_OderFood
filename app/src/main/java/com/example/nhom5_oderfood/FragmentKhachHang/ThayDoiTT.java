package com.example.nhom5_oderfood.FragmentKhachHang;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.example.nhom5_oderfood.R;
import com.example.nhom5_oderfood.databinding.FragmentFragThayDoiTTBinding;
import com.google.android.material.textfield.TextInputEditText;

public class ThayDoiTT extends AppCompatActivity {
    FragmentFragThayDoiTTBinding binding;
    KhachHangDAO khachHangDAO;


    TextInputEditText fullname;
    TextInputEditText sdt;
    TextInputEditText diachi;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentFragThayDoiTTBinding.inflate(getLayoutInflater());
        khachHangDAO = new KhachHangDAO(this);
        fullname = binding.fullname;
        sdt = binding.sdt;
        diachi = binding.diachi;
        khachHangDAO = new KhachHangDAO(this);
//        String dataFromSQLite = khachHangDAO.fetchDataName();
//        fullname.setText(dataFromSQLite);
//        String datasdt = khachHangDAO.fetchDataSDT();
//        sdt.setText(datasdt);
//        String datadiachi = khachHangDAO.fetchDataDiaChi();
//        diachi.setText(datadiachi);


        setSupportActionBar(binding.toolbar3);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // đổi màu icon toolbar
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.back_activity);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
        binding.toolbar3.setNavigationIcon(drawable);setSupportActionBar(binding.toolbar3);

        setContentView(binding.getRoot());


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
