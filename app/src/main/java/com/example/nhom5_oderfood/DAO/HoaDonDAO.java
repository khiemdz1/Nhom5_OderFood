package com.example.nhom5_oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom5_oderfood.DTO.HoaDon;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.DTO.khachhang;
import com.example.nhom5_oderfood.Dbhelper.MyDbhelper;

import java.util.ArrayList;

public class HoaDonDAO {
    private MyDbhelper dbhelper;
    public HoaDonDAO(Context context){
        this.dbhelper = new MyDbhelper(context);
    }
    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HoaDon", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public long addHoadon(HoaDon obj){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenHD", obj.getTenkhachhangHD());
        values.put("SdtHD", obj.getSdtkhachhangHD());
        values.put("DiachiHD", obj.getDiachikhachhangHD());
        values.put("TenmonanHD", obj.getTenmonanHD());
        values.put("SoluongHD", obj.getSoluongHD());
        values.put("GiaHD", obj.getGiaHD());
        return db.insert("HoaDon", null, values);
    }
}
