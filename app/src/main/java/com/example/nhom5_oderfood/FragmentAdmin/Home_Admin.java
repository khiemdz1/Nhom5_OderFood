package com.example.nhom5_oderfood.FragmentAdmin;


import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.nhom5_oderfood.Adapter.MonAnAdapter;
import com.example.nhom5_oderfood.AdapterAdmin.MonAnAdapter_Admin;
import com.example.nhom5_oderfood.AdapterAdmin.SpinnerTheloai;
import com.example.nhom5_oderfood.DAO.MonAnDAO;
import com.example.nhom5_oderfood.DAO.TheloaiDAO;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.DTO.Theloai;
import com.example.nhom5_oderfood.FragmentKhachHang.MainActivity;
import com.example.nhom5_oderfood.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Home_Admin extends Fragment {
    FloatingActionButton fab;
    ImageSlider imageSlider;
    RecyclerView rcv;
    ArrayList<MonAn> list, list2;
    MonAnAdapter_Admin monAnAdapter;
    androidx.appcompat.widget.SearchView searchView;
    MonAnDAO monAnDAO;
    Theloai tl;
    TheloaiDAO tldao;
    ArrayList<Theloai> list_tl;
    SpinnerTheloai spinnerTheloai;
    ImageView imageView;
    private String selectedImagePath;

    int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home__admin, container, false);
        Anhxa(view);
        //Tìm kiếm và hiển thị recycleView
        monAnDAO = new MonAnDAO(getContext());
        list = monAnDAO.getAll();
        list2 = monAnDAO.getAll();
        monAnAdapter = new MonAnAdapter_Admin(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(monAnAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMonan();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        return view;
    }


    private void AddMonan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View v = getLayoutInflater().inflate(R.layout.item_addmonan, null);
        builder.setView(v);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        imageView = v.findViewById(R.id.imganh);
        EditText edtTenMonan = v.findViewById(R.id.edt_tenmonan_add);
        EditText edtGiaTien = v.findViewById(R.id.edt_giatien_add);
        EditText edtMoTa = v.findViewById(R.id.edt_mota_add);
        Spinner spinner = v.findViewById(R.id.spinnertl);

        tldao = new TheloaiDAO(getContext());
        list_tl = tldao.getAllTheLoai();
        spinnerTheloai = new SpinnerTheloai(getContext(), list_tl);
        spinner.setAdapter(spinnerTheloai);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readstogare();

            }
        });
        Button btnAddMonan = v.findViewById(R.id.btnAdd_monan);
        btnAddMonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy thông tin từ các trường nhập liệu
                String tenMonan = edtTenMonan.getText().toString();
                String giaTien = edtGiaTien.getText().toString();
                String moTa = edtMoTa.getText().toString();
                Theloai tenTheloai = (Theloai) spinner.getSelectedItem();
                int id_tl = Integer.parseInt(tenTheloai.getMaTL());

                // Kiểm tra dữ liệu trống
                if (tenMonan.isEmpty() || giaTien.isEmpty() || moTa.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra giá trị giá tiền
                int giaTienValue;
                try {
                    giaTienValue = Integer.parseInt(giaTien);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Giá tiền không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (giaTienValue <= 0) {
                    Toast.makeText(getContext(), "Giá tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                MonAn monAn = new MonAn();
                monAn.setHinhMA(selectedImagePath);
                monAn.setLoaiMA(id_tl);
                monAn.setTenMA(tenMonan);
                monAn.setGiaMA(giaTienValue);
                monAn.setMotaMA(moTa);


                long result = monAnDAO.insertMonAn(monAn);

                if (result > 0) {
                    Toast.makeText(getContext(), "Đã thêm món ăn", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(monAnDAO.getAll());
                    monAnAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Chưa thêm món ăn", Toast.LENGTH_SHORT).show();
                }

                alertDialog.dismiss();
            }
        });
    }

    public void readstogare() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu quyền
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
    }


    public void Anhxa(View v) {
        imageSlider = v.findViewById(R.id.image_slider);
        searchView = v.findViewById(R.id.search_admin);
        rcv = v.findViewById(R.id.rcv_view_admin);
        fab = v.findViewById(R.id.fab_admin);
    }

    public void filter(String s) {
        list.clear();
        for (MonAn ma : list2) {
            if (ma.getTenMA().toLowerCase().contains(s.toString())) {
                list.add(ma);
            }
        }
        monAnAdapter.notifyDataSetChanged();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE && resultCode == RESULT_OK && data != null) {
            // Nhận URI của hình ảnh đã chọn
            Uri selectedImageUri = data.getData();

            selectedImagePath = selectedImageUri.toString();
            // Hiển thị hình ảnh bằng Glide
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(imageView);

        }
    }
}