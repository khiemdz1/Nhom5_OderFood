package com.example.nhom5_oderfood.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.nhom5_oderfood.DTO.khachhang;
import com.example.nhom5_oderfood.Dbhelper.MyDbhelper;

import java.util.ArrayList;

public class KhachHangDAO {
    MyDbhelper database;
    SQLiteDatabase db;

    public KhachHangDAO(Context context) {
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

    @SuppressLint("Range")
    public ArrayList<khachhang> GetAccount() {
        ArrayList<khachhang> list_kh = new ArrayList<khachhang>();
        Cursor cursor = db.rawQuery("SELECT * FROM KhachHang", null);
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
}
