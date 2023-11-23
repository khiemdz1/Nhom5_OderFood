package com.example.nhom5_oderfood.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nhom5_oderfood.DTO.khachhang;
import com.example.nhom5_oderfood.Dbhelper.MyDbhelper;

import java.util.ArrayList;

public class KhachHangDAO {
    MyDbhelper database;
    SQLiteDatabase db;

    Context context;


    public KhachHangDAO(Context context) {
        this.context = context;
        database = new MyDbhelper(context);
        db = database.getWritableDatabase();
    }

    public long AddUser(khachhang kh){
        ContentValues values = new ContentValues();
        values.put("Username", kh.getUsername());
        values.put("Password", kh.getPassword());
        values.put("Fullname", kh.getFullname());
        values.put("Sdt", kh.getSdt());
        values.put("Diachi", kh.getDiachi());

        return db.insert("KhachHang", null, values);
    }

    public boolean checkLogin(String ten,String matkhau){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM KhachHang WHERE Username = ? AND Password = ?", new String[]{ten,matkhau});
        if (cursor.getCount() != 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public void changePassword(String username, String newPassword){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Password", newPassword);
        db.update("KhachHang", cv, "Username=?", new String[]{username});
        db.close();
    }
    @SuppressLint("Range")
    public String getPasswordByUsername(String username) {
        String password = null;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Password FROM KhachHang WHERE Username=?", new String[]{username});
        if (cursor.moveToFirst()) {
            // Lấy mật khẩu từ cột "password" trong kết quả truy vấn
            password = cursor.getString(cursor.getColumnIndex("Password"));
        }
        cursor.close();
        db.close();
        return password;
    }
    @SuppressLint("Range")
    public ArrayList<khachhang> GetAccount() {
        ArrayList<khachhang> list_kh = new ArrayList<>();
        String[] selectionArgs = {"maKH"};
        Cursor cursor = db.rawQuery("SELECT * FROM KhachHang WHERE MaKH = ?", selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                khachhang kh = new khachhang();
                kh.setUsername(cursor.getString(cursor.getColumnIndex("Username")));
                kh.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
                kh.setFullname(cursor.getString(cursor.getColumnIndex("Fullname")));
                kh.setSdt(cursor.getString(cursor.getColumnIndex("Sdt")));
                kh.setDiachi(cursor.getString(cursor.getColumnIndex("Diachi")));
                list_kh.add(kh);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list_kh;
    }

    public boolean fetchDataKhachhang(int makh,String name, String sdt, String diachi) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fullname", name);
        values.put("Sdt", sdt);
        values.put("Diachi", diachi);
        int check = db.update("KhachHang", values, "MaKH = ?", new String[]{String.valueOf(makh)});
        return  check!=-1;
    }

}
