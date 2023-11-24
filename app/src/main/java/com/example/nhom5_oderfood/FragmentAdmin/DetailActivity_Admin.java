package com.example.nhom5_oderfood.FragmentAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nhom5_oderfood.R;

public class DetailActivity_Admin extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgMonan;
    TextView txtTenMonan;
    TextView txtDonGia;
    TextView txtMoTa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_admin);
        // Ánh xạ các view
        toolbar = findViewById(R.id.toolbar_detail);
        imgMonan = findViewById(R.id.imageView_monan);
        txtTenMonan = findViewById(R.id.tv_tenmonan);
        txtDonGia = findViewById(R.id.tv_giatien);
        txtMoTa = findViewById(R.id.textView_mota);

        // Thiết lập toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi tiết món ăn");

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String tenMonan = intent.getStringExtra("tenMonan");
        int donGia = intent.getIntExtra("donGia", 0);
        String moTa = intent.getStringExtra("moTa");

        // Gắn dữ liệu vào các TextView
        txtTenMonan.setText("Tên món ăn: " + tenMonan);
        txtDonGia.setText(String.valueOf(donGia) + "VNĐ");
        txtMoTa.setText(moTa);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Xử lý sự kiện khi người dùng bấm nút quay lại trên Toolbar
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Trở về Activity trước đó
    }
}