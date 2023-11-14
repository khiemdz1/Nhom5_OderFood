package com.example.nhom5_oderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText Password ,Username;
    Button btnLogin;
    CheckBox chkRememberPass;
    private KhachHangDAO dao;
    int temp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username  = findViewById(R.id.edtUsername);
        Password  = findViewById(R.id.edtPassword);
        btnLogin  = findViewById(R.id.btnLogin);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        dao = new KhachHangDAO(this);
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
        if (user.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if (dao.checkLogin(user,pass)){
                Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                rememberUser(user,pass,chkRememberPass.isChecked());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
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