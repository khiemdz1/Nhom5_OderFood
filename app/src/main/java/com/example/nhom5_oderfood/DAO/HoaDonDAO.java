package com.example.nhom5_oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom5_oderfood.DTO.HoaDon;
import com.example.nhom5_oderfood.Dbhelper.MyDbhelper;

import java.util.ArrayList;

public class HoaDonDAO {
    private MyDbhelper dbhelper;
    public HoaDonDAO(Context context){
        this.dbhelper = new MyDbhelper(context);
    }
    public ArrayList<HoaDon> getAllByCustomerId(int customerId) {
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HoaDon WHERE MaKH = ? ORDER BY ID DESC", new String[]{String.valueOf(customerId)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getInt(6), cursor.getString(7), cursor.getInt(8),cursor.getInt(9)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public long addHoadon(HoaDon obj){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaHD", obj.getMaHD());
        values.put("TenHD", obj.getTenkhachhangHD());
        values.put("SdtHD", obj.getSdtkhachhangHD());
        values.put("DiachiHD", obj.getDiachikhachhangHD());
        values.put("TenmonanHD", obj.getTenmonanHD());
        values.put("SoluongHD", obj.getSoluongHD());
        values.put("NgaydatHD", obj.getNgaydatHD());
        values.put("GiaHD", obj.getGiaHD());
        values.put("MaKH",obj.getMakh());
        return db.insert("HoaDon", null, values);
    }

    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HoaDon", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getInt(6), cursor.getString(7), cursor.getInt(8),cursor.getInt(9)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public int DeleteHoaDon(HoaDon obj) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        String[] condition = new String[]{String.valueOf(obj.getIdHD())};

        return db.delete("HoaDon", "id =? ", condition);
    }
}
