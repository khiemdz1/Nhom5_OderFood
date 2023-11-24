package com.example.nhom5_oderfood.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom5_oderfood.DTO.NhaCungCap;
import com.example.nhom5_oderfood.Dbhelper.MyDbhelper;

import java.util.ArrayList;

public class NhaCungCapDAO {

    MyDbhelper dbhelper;
    public NhaCungCapDAO(Context context){
        dbhelper = new MyDbhelper(context);
    }

    public ArrayList<NhaCungCap> getDSNCC() {
        ArrayList<NhaCungCap> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NhaCungCap",null);

        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                list.add(new NhaCungCap(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
