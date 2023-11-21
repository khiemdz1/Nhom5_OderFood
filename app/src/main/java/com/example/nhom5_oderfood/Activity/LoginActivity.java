package com.example.nhom5_oderfood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.example.nhom5_oderfood.FragmentKhachHang.MainActivity;
import com.example.nhom5_oderfood.FragmentAdmin.MainAdminActivity;
import com.example.nhom5_oderfood.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText Password ,Username;

    Button btnLogin;
    CheckBox chkRememberPass;
    private KhachHangDAO dao;
    TextView Taotk;
    int temp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username  = findViewById(R.id.edtUsername);
        Password  = findViewById(R.id.edtPassword);
        btnLogin  = findViewById(R.id.btnLogin);
        Taotk = findViewById(R.id.taotk);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        dao = new KhachHangDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        Username.setText(pref.getString("USERNAME",""));
        Password.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));


        Taotk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this , RegisteUser.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checklg();
            }
        });
    }

    public void checklg(){
        String user = Username.getText().toString();
        String pass = Password.getText().toString();

        if(user.isEmpty()){
            Username.setError("Tên Đăng Nhập Đang Trống");
        }

        if (pass.isEmpty()){
            Password.setError("Mật Khẩu Nhập Đang Trống");

        }
        if (user.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if (dao.checkLogin(user,pass)){
                rememberUser(user,pass,chkRememberPass.isChecked());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else if (user.equals("admin123")|| pass.equals("admin")) {
                Toast.makeText(this, "Bạn đã đăng nhập : Admin", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainAdminActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}