package com.example.nhom5_oderfood.FragmentKhachHang;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.example.nhom5_oderfood.R;
import com.example.nhom5_oderfood.databinding.FragmentFragDoiMKBinding;

public class DoiMK extends AppCompatActivity {
    FragmentFragDoiMKBinding binding;

    KhachHangDAO khachHangDAO;
    String username, passwordOld,passwordNew1,passWordNew2,passwordData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentFragDoiMKBinding.inflate(getLayoutInflater());

        setSupportActionBar(binding.toolbar4);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // đổi màu icon toolbar
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.back_activity);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
        binding.toolbar4.setNavigationIcon(drawable);


        khachHangDAO = new KhachHangDAO(this);
        listener();



        setContentView(binding.getRoot());

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void listener(){
        binding.btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }
    private void validate(){
        username = binding.edtusername.getText().toString().trim();
        passwordOld = binding.edtmkcu.getText().toString().trim();
        passwordNew1 = binding.edtmknew.getText().toString().trim();
        passWordNew2 = binding.edtnhaplai.getText().toString().trim();
        passwordData = khachHangDAO.getPasswordByUsername(username);

        if (username.isEmpty() || passwordOld.isEmpty() || passwordNew1.isEmpty() || passWordNew2.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();

        } else if (passwordData == null) {
            Toast.makeText(this, "Sai tài khoản", Toast.LENGTH_SHORT).show();
            binding.edtusername.setError("Tài khoản chưa đúng");

        } else if (!passwordOld.equals(passwordData)) {
            Toast.makeText(this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
            binding.edtmkcu.setError("Mật khẩu không đúng");

        } else if (!passwordNew1.equals(passWordNew2)) {
            Toast.makeText(this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
            binding.edtmknew.setError("Mật khẩu hoặc mật khẩu hoặc mật khẩu xác nhận lại đã sai");
            binding.edtnhaplai.setError("Mật khẩu hoặc mật khẩu hoặc mật khẩu xác nhận lại đã sai");

        }else {
            khachHangDAO.changePassword(username, passwordNew1);
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            binding.edtusername.setText("");
            binding.edtmkcu.setText("");
            binding.edtmknew.setText("");
            binding.edtnhaplai.setText("");
        }
    }

    }
