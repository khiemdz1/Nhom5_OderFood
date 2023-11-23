package com.example.nhom5_oderfood.FragmentKhachHang;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.example.nhom5_oderfood.Activity.LoginActivity;
import com.example.nhom5_oderfood.Activity.WellcomeActivity;
import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.example.nhom5_oderfood.DTO.khachhang;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;

public class Frag_CaiDat extends Fragment {
    TextView logout,doimk,thongtinnd,userheadercd;
    KhachHangDAO khachHangDAO;
    ArrayList<khachhang> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_caidat,container,false);
        doimk = view.findViewById(R.id.changePass);
        logout = view.findViewById(R.id.out);
        thongtinnd = view.findViewById(R.id.changeTT);
        userheadercd = view.findViewById(R.id.userheadercd);
        khachHangDAO = new KhachHangDAO(getContext());


//        userheadercd.setText(kh.getFullname());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thông Báo");
                builder.setIcon(R.drawable.logout);
                builder.setMessage("Bạn có muốn đăng xuất hay không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);

                        startActivity(intent);                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DoiMK.class);
                startActivity(intent);
            }
        });
        thongtinnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ThayDoiTT.class);
                startActivity(i);
            }
        });
        return view;


    }
}
