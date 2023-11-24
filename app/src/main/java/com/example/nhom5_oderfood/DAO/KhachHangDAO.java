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
public khachhang fetchData(int userId) {
    SQLiteDatabase db = database.getReadableDatabase();
    khachhang khachHangInfo = null;
    Cursor cursor = db.rawQuery("SELECT Fullname, Sdt, Diachi  FROM KhachHang WHERE MaKH = ?", new String[]{String.valueOf(userId)});
    if (cursor.getCount() > 0) {
        cursor.moveToFirst();
        String fullname = cursor.getString(cursor.getColumnIndex("Fullname"));
        String sdt = cursor.getString(cursor.getColumnIndex("Sdt"));
        String diachi = cursor.getString(cursor.getColumnIndex("Diachi"));

        khachHangInfo = new khachhang(fullname, sdt, diachi);
    }
    cursor.close();
    db.close();
    return khachHangInfo;
}
    //lấy id
    @SuppressLint("Range")
    public int getUserId(String username, String password) {
        SQLiteDatabase db = this.database.getReadableDatabase();
        int userId = -1; // Giá trị mặc định nếu không tìm thấy userId
        Cursor cursor = db.rawQuery("SELECT MaKH FROM KhachHang WHERE Username = ? AND Password = ?", new String[]{username, password});
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex("MaKH"));
        }
        cursor.close();
        db.close();
        return userId;
    }
    public boolean UpdateUser(int makh, String name,String sdt,String diachi) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fullname",name);
        values.put("Sdt", sdt);
        values.put("Diachi",diachi);
        int check = db.update("KhachHang", values, "MaKH = ?", new String[]{String.valueOf(makh)});
        return  check!=-1;
    }

}
