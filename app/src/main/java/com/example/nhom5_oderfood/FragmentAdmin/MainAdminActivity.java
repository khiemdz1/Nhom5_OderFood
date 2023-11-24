package com.example.nhom5_oderfood.FragmentAdmin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhom5_oderfood.AdapterAdmin.MonAnAdapter_Admin;
import com.example.nhom5_oderfood.AdapterAdmin.SpinnerTheloai;
import com.example.nhom5_oderfood.DAO.MonAnDAO;
import com.example.nhom5_oderfood.DAO.TheloaiDAO;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.DTO.Theloai;
import com.example.nhom5_oderfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdminActivity extends AppCompatActivity {
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bot_nav;

    NavigationView nav_view;
    Toolbar toolbar;
    ImageView imganh;

    String ImgAnh;
    MonAnAdapter_Admin monAnAdapterAdmin;
    MonAnDAO dao;
    MonAn model;
    ArrayList<MonAn> list_monan;
    Theloai tl;
    TheloaiDAO tldao;
    ArrayList<Theloai> list_tl;
    SpinnerTheloai spinnerTheloai;

    RecyclerView rc_ma;
    Uri img_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        fab = findViewById(R.id.fab_admin);
        drawerLayout = findViewById(R.id.drawerLayout);
        bot_nav = findViewById(R.id.bottomNavigationView_admin);
        nav_view = findViewById(R.id.navigationView_admin);
        toolbar = findViewById(R.id.toolbar_admin);
        rc_ma = findViewById(R.id.rc_monan);

        dao = new MonAnDAO(MainAdminActivity.this);
        list_monan = dao.getAll();
        monAnAdapterAdmin = new MonAnAdapter_Admin(MainAdminActivity.this, list_monan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainAdminActivity.this, RecyclerView.VERTICAL, false);
        rc_ma.setLayoutManager(layoutManager);
        rc_ma.setAdapter(monAnAdapterAdmin);
        rc_ma.invalidate();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainAdminActivity.this);
                View inflater = getLayoutInflater().inflate(R.layout.item_addmonan, null);
                dialog.setView(inflater);
                Dialog dialog1 = dialog.create();
                dialog1.show();

                EditText namefood = inflater.findViewById(R.id.edt_tenmonan_add);
                EditText giatien = inflater.findViewById(R.id.edt_giatien_add);
                EditText mota = inflater.findViewById(R.id.edt_mota_add);
                Spinner spinnertl = inflater.findViewById(R.id.spinnertl);
                imganh = inflater.findViewById(R.id.imganh);
                Button btnadd = inflater.findViewById(R.id.btnAdd_monan);

                // Khởi tạo SpinnerTheloai
                tldao = new TheloaiDAO(MainAdminActivity.this);
                ArrayList<Theloai> listTheloai = tldao.getAllTheLoai();
                spinnerTheloai = new SpinnerTheloai(MainAdminActivity.this, listTheloai);
                spinnertl.setAdapter(spinnerTheloai);

                imganh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickThuVienFuntion();
                    }
                });

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenMonAn = namefood.getText().toString();
                        int giaTien = Integer.parseInt(giatien.getText().toString());
                        String moTa = mota.getText().toString();

                        Theloai selectedTheloai = (Theloai) spinnertl.getSelectedItem();
                        int idTheloai = Integer.parseInt(selectedTheloai.getMaTL());

                        if (img_uri == null) {
                            Toast.makeText(getBaseContext(), "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            ImgAnh = img_uri.toString();
                        }
                        model = new MonAn();
                        model.setHinhMA("");
                        model.setTenMA(tenMonAn);
                        model.setLoaiMA(idTheloai);
                        model.setGiaMA(giaTien);
                        model.setMotaMA(moTa);

                        dao = new MonAnDAO(MainAdminActivity.this);
                        long result = dao.insertMonAn(model);

                        if (result != -1) {
                            Toast.makeText(MainAdminActivity.this, "Đã thêm món ăn", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                            list_monan.clear();
                            list_monan.addAll(dao.getAll());
                            monAnAdapterAdmin.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainAdminActivity.this, "Thêm món ăn thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void pickThuVienFuntion() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResult.launch(intent);
    }

    private ActivityResultLauncher<Intent> galleryActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == Activity.RESULT_OK) {
                Intent intent = o.getData();

                img_uri = intent.getData();

                try {
                    Picasso.get().load(img_uri).placeholder(R.drawable.baseline_edit_24).error(R.drawable.baseline_edit_24).into(imganh);
                }catch (Exception e){
                    Log.d("Tag", "onActivityResultLaucher: k load được ảnh" + e.getMessage());
                }
            }
        }
    });

}