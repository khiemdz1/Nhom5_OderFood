package com.example.nhom5_oderfood.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom5_oderfood.DTO.Theloai;
import com.example.nhom5_oderfood.Dbhelper.MyDbhelper;

import java.util.ArrayList;

public class TheloaiDAO {
    private SQLiteDatabase database;
    private MyDbhelper dbHelper;

    public TheloaiDAO(Context context) {
        dbHelper = new MyDbhelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertTheLoai(Theloai theLoai) {
        ContentValues values = new ContentValues();
        values.put("MaTL", theLoai.getMaTL());
        values.put("Tentheloai", theLoai.getTenTheLoai());

        return database.insert("TheLoai", null, values);
    }

    public ArrayList<Theloai> getAllTheLoai() {
        ArrayList<Theloai> theLoaiList = new ArrayList<>();
        Cursor cursor = database.query("TheLoai", null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            @SuppressLint("Range") String maTL = cursor.getString(cursor.getColumnIndex("MaTL"));
            @SuppressLint("Range") String tenTheLoai = cursor.getString(cursor.getColumnIndex("Tentheloai"));

            Theloai theLoai = new Theloai(maTL, tenTheLoai);
            theLoaiList.add(theLoai);

            cursor.moveToNext();
        }

        cursor.close();
        return theLoaiList;
    }

    public int updateTheLoai(Theloai theLoai) {
        ContentValues values = new ContentValues();
        values.put("Tentheloai", theLoai.getTenTheLoai());

        return database.update("TheLoai", values, "MaTL=?", new String[]{theLoai.getMaTL()});
    }

    public int deleteTheLoai(Theloai theLoai) {
        return database.delete("TheLoai", "MaTL=?", new String[]{theLoai.getMaTL()});
    }
}