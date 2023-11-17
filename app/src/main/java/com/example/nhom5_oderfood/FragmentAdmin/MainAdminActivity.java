package com.example.nhom5_oderfood.FragmentAdmin;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhom5_oderfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainAdminActivity extends AppCompatActivity {
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bot_nav;

    NavigationView nav_view;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        fab = findViewById(R.id.fab_admin);
        drawerLayout = findViewById(R.id.drawerLayout);
        bot_nav = findViewById(R.id.bottomNavigationView_admin);
        nav_view = findViewById(R.id.navigationView_admin);
        toolbar = findViewById(R.id.toolbar_admin);

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
                Button btnadd = inflater.findViewById(R.id.btnAdd_monan);

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainAdminActivity.this, "Đã thêm món ăn", Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                    }
                });
            }
        });
    }
}