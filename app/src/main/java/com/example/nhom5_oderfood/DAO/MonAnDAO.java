package com.example.nhom5_oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.Dbhelper.MyDbhelper;

import java.util.ArrayList;

public class MonAnDAO {
    private MyDbhelper dbhelper;
    SQLiteDatabase database;
    public MonAnDAO(Context context){
       this.dbhelper = new MyDbhelper(context);
       database = dbhelper.getWritableDatabase();
    }
    public ArrayList<MonAn> getAll() {
        ArrayList<MonAn> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from MonAn", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new MonAn(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getInt(4),cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public long insertMonAn(MonAn monAn) {
        ContentValues values = new ContentValues();
        values.put("Hinhanh", monAn.getHinhMA());
        values.put("Tenmonan", monAn.getTenMA());
        values.put("MaTL", monAn.getLoaiMA());
        values.put("Dongia", monAn.getGiaMA());
        values.put("Mota", monAn.getMotaMA());

        return database.insert("MonAn", null, values);
    }

    public int updateMonAn(MonAn monAn) {
        ContentValues values = new ContentValues();
        values.put("Hinhanh", monAn.getHinhMA());
        values.put("Tenmonan", monAn.getTenMA());
        values.put("MaTL", monAn.getLoaiMA());
        values.put("Dongia", monAn.getGiaMA());
        values.put("Mota", monAn.getMotaMA());

        return database.update("MonAn", values, "MaMA=?", new String[]{String.valueOf(monAn.getIdMA())});
    }

    public int deleteMonAn(MonAn monAn) {
        return database.delete("MonAn", "MaMA=?",new String[]{String.valueOf(monAn.getIdMA())});
    }
}
