package com.example.nhom5_oderfood.FragmentKhachHang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.example.nhom5_oderfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final int FRAG_HOME = 0;
    static final int FRAG_GIOHANG = 1;
    static final int FRAG_HOADON = 2;
    static final int FRAG_FEEDBACK = 3;
    static final int FRAG_NHACUNGCAP = 4;
    static final int FRAG_CAIDAT = 5;
    int mCurrentFrag = FRAG_HOME;
    Toolbar toolbar;
    NavigationView nagView;
    DrawerLayout drawerLayout;

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();
        nagView = findViewById(R.id.navigationView);
        nagView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);
        relaceFrg(new Frag_Home());
        nagView.setCheckedItem(R.id.menuHOME);
        bottomNavigationView.getMenu().findItem(R.id.menuHOME2).setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menuHOME2){
                    relaceFrg(new Frag_Home());
                    nagView.setCheckedItem(R.id.menuHOME);
                }else if(id == R.id.menuGIOHANG2){
                    relaceFrg(new Frag_GioHang());
                    nagView.setCheckedItem(R.id.menuGIOHANG2);
                }else if(id == R.id.menuHOADON2){
                    relaceFrg(new Frag_HoaDon());
                    nagView.setCheckedItem(R.id.menuHOADON2);
                }else {
                    relaceFrg(new Frag_FeedBack());
                    nagView.setCheckedItem(R.id.menuFEEDBACK2);
                }
                return true;
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuHOME) {
            if (mCurrentFrag != FRAG_HOME) {
                relaceFrg(new Frag_Home());
                mCurrentFrag = FRAG_HOME;
                bottomNavigationView.getMenu().findItem(R.id.menuHOME2).setChecked(true);
            }
        }else if(id == R.id.menuGIOHANG){
            if (mCurrentFrag != FRAG_GIOHANG) {
                relaceFrg(new Frag_GioHang());
                mCurrentFrag = FRAG_GIOHANG;
                bottomNavigationView.getMenu().findItem(R.id.menuGIOHANG2).setChecked(true);
            }
        }else if(id == R.id.menuHOADON){
            if (mCurrentFrag != FRAG_HOADON) {
                relaceFrg(new Frag_HoaDon());
                mCurrentFrag = FRAG_HOADON;
                bottomNavigationView.getMenu().findItem(R.id.menuHOADON2).setChecked(true);
            }
        }else if(id == R.id.menuFEEDBACK){
            if (mCurrentFrag != FRAG_FEEDBACK) {
                relaceFrg(new Frag_FeedBack());
                mCurrentFrag = FRAG_FEEDBACK;
                bottomNavigationView.getMenu().findItem(R.id.menuFEEDBACK2).setChecked(true);
            }
        }else if(id == R.id.menuNHACUNGCAP){
            if (mCurrentFrag != FRAG_NHACUNGCAP) {
                relaceFrg(new Frag_NhaCungCap());
                mCurrentFrag = FRAG_NHACUNGCAP;
            }
        }else if(id == R.id.menuCAIDAT){
            if (mCurrentFrag != FRAG_CAIDAT) {
                relaceFrg(new Frag_CaiDat());
                mCurrentFrag = FRAG_CAIDAT;
            }
        }
        drawerLayout.close();
        return true;
    }

        public void relaceFrg (Fragment frg){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, frg);
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