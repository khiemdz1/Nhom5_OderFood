package com.example.nhom5_oderfood.FragmentKhachHang;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nhom5_oderfood.FragmentKhachHang.databasegiohang.AppDatabase;
import com.example.nhom5_oderfood.R;

public class ThongTinMonAn extends AppCompatActivity{
    Toolbar toolbar;
    ImageView imageView;
    TextView tv_ten,tv_gia,tv_motama;
    Button btn_themmonan,btn_huy;
    // đặt biến toàn cục
    String anh,ten,mota;
    int gia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_mon_an);
        toolbar = findViewById(R.id.toolbar2);
        imageView = findViewById(R.id.image_view2);
        tv_ten = findViewById(R.id.tv_tenmonan2);
        tv_gia = findViewById(R.id.tv_giamonan2);
        tv_motama = findViewById(R.id.tv_mota);
        btn_themmonan = findViewById(R.id.btn_them);
        //set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi Tiết Sản Phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // đổi màu icon toolbar
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.back_activity);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
        toolbar.setNavigationIcon(drawable);
        // lấy dữ liệu bằng phương thức Intent
        // lấy ảnh và load ảnh bằng glide
        Intent intent = getIntent();
        anh = intent.getStringExtra("Hinhanh");
        int imageResourceId = this.getResources().getIdentifier(anh, "drawable", this.getPackageName());
        Glide.with(this).load(imageResourceId).into(imageView);
        // lấy tên
        ten = intent.getStringExtra("Tenmonan");
        //lấy giá
        gia = intent.getIntExtra("Donngia",0);
        mota = intent.getStringExtra("Mota");
        //ép kiểu
        String gia2 = String.valueOf(gia);
        // set dữ liệu lên text
        tv_ten.setText(ten);
        tv_gia.setText(gia2);
        tv_motama.setText(mota);
        // Khởi tạo ViewModel


        btn_themmonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenBottomSheetDialog();
            }
        });

    }

    private void OpenBottomSheetDialog() {
        String gia = tv_gia.getText().toString();
        int gia2 = Integer.parseInt(gia);
        MyBottomSheetFrag bottomSheetFrag = new MyBottomSheetFrag();
        Bundle bundle = new Bundle();
        bundle.putString("Hinhanh", anh);
        bundle.putString("Tenmonan",ten);
        bundle.putInt("Donngia", gia2);
        bottomSheetFrag.setArguments(bundle);
        bottomSheetFrag.setCancelable(false);
        bottomSheetFrag.show(getSupportFragmentManager(), bottomSheetFrag.getTag());
    }

    // back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}