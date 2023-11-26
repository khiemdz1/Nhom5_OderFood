package com.example.nhom5_oderfood.FragmentKhachHang;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nhom5_oderfood.Adapter.GioHangAdapter;
import com.example.nhom5_oderfood.DAO.HoaDonDAO;
import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.example.nhom5_oderfood.DTO.GioHang;
import com.example.nhom5_oderfood.DTO.HoaDon;
import com.example.nhom5_oderfood.DTO.khachhang;
import com.example.nhom5_oderfood.FragmentKhachHang.databasegiohang.AppDatabase;
import com.example.nhom5_oderfood.FragmentKhachHang.databasegiohang.GioHangDAO;
import com.example.nhom5_oderfood.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyBottomSheetFragDatHang extends BottomSheetDialogFragment {
    Button btn_huy,btn_xacnhan;
    GioHangDAO gioHangDAO;
    KhachHangDAO khachHangDAO;
    TextView tv_diachi,tv_tenmon,tv_tonggia;
    List<GioHang> gioHangList;
    HoaDonDAO hoaDonDAO;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private Frag_GioHang frag_gioHang;

    public void setFrag_gioHang(Frag_GioHang frag_gioHang) {
        this.frag_gioHang = frag_gioHang;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        khachHangDAO = new KhachHangDAO(getContext());
        hoaDonDAO = new HoaDonDAO(getContext());
        gioHangList = new ArrayList<>();
        gioHangDAO = AppDatabase.getDatabase(getContext()).gioHangDao();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View viewDialog = LayoutInflater.from(getContext()).inflate(R.layout.item_dathang,null);
        bottomSheetDialog.setContentView(viewDialog);
        btn_huy = viewDialog.findViewById(R.id.btn_huy);
        tv_diachi = viewDialog.findViewById(R.id.tv_diachidathang);
        tv_tenmon = viewDialog.findViewById(R.id.tv_tenmonandathang);
        btn_xacnhan = viewDialog.findViewById(R.id.btn_xacnhan);
        tv_tonggia = viewDialog.findViewById(R.id.tv_tonggiadathang);

        //lấy thông tin người dùng qua id
        int loggedInUserId = getLoggedInUserId();
        khachhang infokh = khachHangDAO.fetchData(loggedInUserId);
        tv_diachi.setText(infokh.getDiachi());
        //lấy list tên món ăn và số lượng có trong database
        List<GioHang> list = gioHangDAO.getlitsMonan();
        StringBuilder thongTinStringBuilder = new StringBuilder();
        for (GioHang item : list) {
            thongTinStringBuilder.append(item.getTenGH())
                    .append("  x").append(item.getSoluongGH())
                    .append("\n");
        }
        tv_tenmon.setText(thongTinStringBuilder);
        // tổng số lượng sản phẩm có trong list
        List<GioHang> list2 = gioHangDAO.getlitsMonan();
        int tongSoLuong = 0;
        for (GioHang item : list2) {
            tongSoLuong += item.getSoluongGH();
        }
        //lấy tổng giá
        int giatong = getGiaTong();
        tv_tonggia.setText(String.format("%,d", giatong));
        int finalTongSoLuong = tongSoLuong;
        //lấy thời gian
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm - dd/MM/yyyy ");
        String formattedDate = dateFormat.format(currentDate);
        //mã hóa đơn
        String randomString = generateRandomString(13);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 HoaDon hoaDon = new HoaDon();
                 hoaDon.setMaHD(randomString);
                 hoaDon.setTenkhachhangHD(infokh.getFullname());
                 hoaDon.setSdtkhachhangHD(infokh.getSdt());
                 hoaDon.setDiachikhachhangHD(infokh.getDiachi());
                 hoaDon.setTenmonanHD(thongTinStringBuilder.toString());
                 hoaDon.setSoluongHD(finalTongSoLuong);
                 hoaDon.setNgaydatHD(formattedDate);
                 hoaDon.setGiaHD(giatong);
                 hoaDon.setMakh(loggedInUserId);

                 long result = hoaDonDAO.addHoadon(hoaDon);
                 if (result != -1) {
                     gioHangDAO.deleteAllGioHang();
                     frag_gioHang.loadData();
                     Toast.makeText(getContext(), "Đơn hàng đã được đặt thành công", Toast.LENGTH_SHORT).show();
                     dismiss();
                 } else {
                     Toast.makeText(getContext(), "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                 }
             }
         });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {bottomSheetDialog.dismiss();}});
        return bottomSheetDialog;
    }
    //random
    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
    //lấy tổng giá
    private int getGiaTong() {
        if (getArguments() != null) {
            return getArguments().getInt("giatong", 0);
        }
        return 0; // Giá trị mặc định nếu không có dữ liệu
    }
    private int getLoggedInUserId() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        return sharedPreferences.getInt("USER_ID", -1); // -1 là giá trị mặc định nếu không tìm thấy
    }
}
