package com.example.nhom5_oderfood.FragmentKhachHang;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.nhom5_oderfood.Activity.LoginActivity;
import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.example.nhom5_oderfood.DTO.khachhang;
import com.example.nhom5_oderfood.R;
import com.example.nhom5_oderfood.databinding.FragmentFragThayDoiTTBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class ThayDoiTT extends AppCompatActivity {
    FragmentFragThayDoiTTBinding binding;
    KhachHangDAO khachHangDAO;
    TextInputEditText fullname;
    TextInputEditText sdt;
    TextInputEditText diachi;
    Button edit;
    String namee,sdtt,diachii;
    private boolean isEditing = false;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentFragThayDoiTTBinding.inflate(getLayoutInflater());
        khachHangDAO = new KhachHangDAO(this);
        fullname = binding.fullname;
        sdt = binding.sdt;
        diachi = binding.diachi;
        edit = binding.btnedit;
        khachHangDAO = new KhachHangDAO(this);

        int loggedInUserId = getLoggedInUserId();
        khachhang infokh = khachHangDAO.fetchData(loggedInUserId);
        String fullName = infokh.getFullname();
        String sdT = infokh.getSdt();
        String diachI = infokh.getDiachi();
        fullname.setText(fullName);
        sdt.setText(sdT);
        diachi.setText(diachI);
        fullname.setEnabled(false);
        sdt.setEnabled(false);
        diachi.setEnabled(false);
        fullname.setTextColor(ContextCompat.getColor(this, R.color.black)); // Đổi màu về màu bạn mong muốn
        sdt.setTextColor(ContextCompat.getColor(this, R.color.black));
        diachi.setTextColor(ContextCompat.getColor(this, R.color.black));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing) {
                    // Nếu đang chỉnh sửa, trở về trạng thái ban đầu
                    fullname.setEnabled(false);
                    sdt.setEnabled(false);
                    diachi.setEnabled(false);
                    fullname.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black)); // Đổi màu về màu bạn mong muốn
                    sdt.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
                    diachi.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
                    edit.setText("Sửa");
                    int id = getLoggedInUserId();
                    namee = fullname.getText().toString();
                    sdtt  = sdt.getText().toString();
                    diachii = diachi.getText().toString();
                    boolean check = khachHangDAO.UpdateUser(id,namee,sdtt,diachii);
                    if (check) {
                        Snackbar.make(view, "Lưu thành công", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(view, "Lưu Thất Bại", Snackbar.LENGTH_SHORT).show();
                    }
                }else{
                    // Nếu không đang chỉnh sửa, bắt đầu chỉnh sửa
                    fullname.setEnabled(true);
                    sdt.setEnabled(true);
                    diachi.setEnabled(true);
                    fullname.setTextColor(ContextCompat.getColor(view.getContext(), R.color.xam));
                    sdt.setTextColor(ContextCompat.getColor(view.getContext(), R.color.xam));
                    diachi.setTextColor(ContextCompat.getColor(view.getContext(), R.color.xam));
                    Snackbar.make(view, "điền vào ô cần sửa", Snackbar.LENGTH_SHORT).show();
                    edit.setText("Lưu");
                }
                isEditing = !isEditing;

            }
        });


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
    private int getLoggedInUserId() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("USER_FILE", MODE_PRIVATE);
        return sharedPreferences.getInt("USER_ID", -1); // -1 là giá trị mặc định nếu không tìm thấy
    }
}
