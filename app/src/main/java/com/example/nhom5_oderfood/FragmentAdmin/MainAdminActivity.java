package com.example.nhom5_oderfood.FragmentAdmin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom5_oderfood.AdapterAdmin.MonAnAdapter_Admin;
import com.example.nhom5_oderfood.AdapterAdmin.SpinnerTheloai;
import com.example.nhom5_oderfood.DAO.KhachHangDAO;
import com.example.nhom5_oderfood.DAO.MonAnDAO;
import com.example.nhom5_oderfood.DAO.TheloaiDAO;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.DTO.Theloai;
import com.example.nhom5_oderfood.DTO.khachhang;
import com.example.nhom5_oderfood.FragmentKhachHang.Frag_CaiDat;
import com.example.nhom5_oderfood.FragmentKhachHang.Frag_FeedBack;
import com.example.nhom5_oderfood.FragmentKhachHang.Frag_GioHang;
import com.example.nhom5_oderfood.FragmentKhachHang.Frag_HoaDon;
import com.example.nhom5_oderfood.FragmentKhachHang.Frag_Home;
import com.example.nhom5_oderfood.FragmentKhachHang.Frag_NhaCungCap;
import com.example.nhom5_oderfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdminActivity extends AppCompatActivity {
    static final int Home_Admin = 0;
    static final int Frag_GiohangAdmin = 1;
    static final int Frag_HoaDonAdmin = 2;
    static final int FRAG_FEEDBACKAdmin = 3;
    static final int FRAG_NHACUNGCAPAdmin = 4;
    static final int FRAG_CAIDATAdmin = 5;
    int mCurrentFrag = Home_Admin;
    Toolbar toolbar;
    NavigationView nagView;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        toolbar = findViewById(R.id.toolbar_admin);
        drawerLayout = findViewById(R.id.drawerLayout_Admin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();
        nagView = findViewById(R.id.navigationView_admin);

        bottomNavigationView = findViewById(R.id.bottomNavigationView_admin);
        frameLayout = findViewById(R.id.frameLayout_admin);
        relaceFrg(new Home_Admin());
        nagView.setCheckedItem(R.id.menuHOME);
        bottomNavigationView.getMenu().findItem(R.id.menuHOME2).setChecked(true);

        //set fullname vào header
        View headerview = nagView.getHeaderView(0);
        TextView nameheader = headerview.findViewById(R.id.userheader);
        nameheader.setText("Admin");
        nagView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menuHOME) {
                    if (mCurrentFrag != Home_Admin) {
                        relaceFrg(new Home_Admin());
                        mCurrentFrag = Home_Admin;
                        bottomNavigationView.getMenu().findItem(R.id.menuHOME2).setChecked(true);
                    }
                }else if(id == R.id.menuGIOHANG){
                    if (mCurrentFrag != Frag_GiohangAdmin) {
                        relaceFrg(new GiohangAdminFragment());
                        mCurrentFrag = Frag_GiohangAdmin;
                        bottomNavigationView.getMenu().findItem(R.id.menuGIOHANG2).setChecked(true);
                    }
                }else if(id == R.id.menuHOADON){
                    if (mCurrentFrag != Frag_HoaDonAdmin) {
                        relaceFrg(new HoadonAdminFragment());
                        mCurrentFrag = Frag_HoaDonAdmin;
                        bottomNavigationView.getMenu().findItem(R.id.menuHOADON2).setChecked(true);
                    }
                }else if(id == R.id.menuFEEDBACK){
                    if (mCurrentFrag != FRAG_FEEDBACKAdmin) {
                        relaceFrg(new FeedbackAdminFragment());
                        mCurrentFrag = FRAG_FEEDBACKAdmin;
                        bottomNavigationView.getMenu().findItem(R.id.menuFEEDBACK2).setChecked(true);
                    }
                }else if(id == R.id.menuNHACUNGCAP){
                    if (mCurrentFrag != FRAG_NHACUNGCAPAdmin) {
                        relaceFrg(new NhacungcapAdminFragment());
                        mCurrentFrag = FRAG_NHACUNGCAPAdmin;
                    }
                }else if(id == R.id.menuCAIDAT){
                    if (mCurrentFrag != FRAG_CAIDATAdmin) {
                        relaceFrg(new CaiDatAdminFragment());
                        mCurrentFrag = FRAG_CAIDATAdmin;
                    }
                }
                drawerLayout.close();
                return true;
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menuHOME2){
                    relaceFrg(new Home_Admin());
                    nagView.setCheckedItem(R.id.menuHOME);
                }else if(id == R.id.menuGIOHANG2){
                    relaceFrg(new GiohangAdminFragment());
                    nagView.setCheckedItem(R.id.menuGIOHANG2);
                }else if(id == R.id.menuHOADON2){
                    relaceFrg(new HoadonAdminFragment());
                    nagView.setCheckedItem(R.id.menuHOADON2);
                }else {
                    relaceFrg(new FeedbackAdminFragment());
                    nagView.setCheckedItem(R.id.menuFEEDBACK2);
                }
                return true;
            }
        });
    }

    public void relaceFrg (Fragment frg){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout_admin, frg);
        transaction.commit();
    }

    public void onBackPressed () {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}