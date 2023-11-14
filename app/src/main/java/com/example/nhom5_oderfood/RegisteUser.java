package com.example.nhom5_oderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.example.nhom5_oderfood.DTO.khachhang;

public class RegisteUser extends AppCompatActivity {
    EditText username, password, fullname, sdt, confirmpass, diachi;
    Button btnAdd;

    KhachHangDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe_user);

        //ánh xạ giao diện
        username = findViewById(R.id.edt_username_res);
        password = findViewById(R.id.edt_password_res);
        fullname = findViewById(R.id.edt_fullname_res);
        sdt = findViewById(R.id.edt_sdt_res);
        diachi = findViewById(R.id.edt_diachi_res);
        confirmpass = findViewById(R.id.edt_confirmpass_res);
        btnAdd = findViewById(R.id.btnResgister);

        //tạo ra 1 new khachhangdao
        dao = new KhachHangDAO(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ các trường
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String name = fullname.getText().toString();
                String phone = sdt.getText().toString();
                String address = diachi.getText().toString();

                boolean isValid = true;

                if (user.isEmpty()) {
                    username.setError("Username đang trống");
                    isValid = false;
                }

                if (pass.isEmpty()) {
                    password.setError("Password đang trống");
                    isValid = false;
                } else if (pass.length() < 6) {
                    password.setError("Mật khẩu phải lớn hơn 6 kí tự");
                    isValid = false;
                }

                if (name.isEmpty()) {
                    fullname.setError("Họ và tên đang trống");
                    isValid = false;
                }

                if (address.isEmpty()) {
                    diachi.setError("Địa chỉ đang trống");
                    isValid = false;
                }

                if (phone.isEmpty()) {
                    sdt.setError("Số điện thoại đang trống");
                    isValid = false;
                } else if (phone.length() != 10) {
                    sdt.setError("Số điện thoại phải là 10 số");
                    isValid = false;
                }

                // Kiểm tra mật khẩu nhập lại
                String nhaplai = confirmpass.getText().toString();
                if (nhaplai.isEmpty()) {
                    confirmpass.setError("Confirm đang trống");
                    isValid = false;
                } else if (!pass.equals(nhaplai)) {
                    confirmpass.setError("Mật khẩu không khớp");
                    isValid = false;
                }

                if (isValid) {
                    khachhang kh = new khachhang();
                    kh.setUsername(user);
                    kh.setPassword(pass);
                    kh.setFullname(name);
                    kh.setSdt(phone);
                    kh.setDiachi(address);

                    long result = dao.AddUser(kh);
                    if (result > 0) {
                        Toast.makeText(RegisteUser.this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
                        // Chuyển sang màn hình đăng nhập hoặc thực hiện các thao tác khác
                    } else {
                        Toast.makeText(RegisteUser.this, "Đăng ký tài khoản thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}