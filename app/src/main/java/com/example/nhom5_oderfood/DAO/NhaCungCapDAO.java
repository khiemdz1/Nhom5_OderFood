package com.example.nhom5_oderfood.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom5_oderfood.DTO.NhaCungCap;
import com.example.nhom5_oderfood.DTO.khachhang;
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

    public boolean deleteNCC(int id){
        SQLiteDatabase sqLiteDatabase =dbhelper.getWritableDatabase();
        int row = sqLiteDatabase.delete("NhaCungCap", "MaNCC = ?", new String[]{String.valueOf(id)});
        return  row!=-1;
    }

    @SuppressLint("Range")
    public NhaCungCap fetchDataNCC(int id) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        NhaCungCap nccInfor = null;
        Cursor cursor = db.rawQuery("SELECT Tennhacc, Thongtin, Lienhe,Email FROM NhaCungCap WHERE MaKH = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String tenNCC = cursor.getString(cursor.getColumnIndex("Tennhacc"));
            String thongTinNCC = cursor.getString(cursor.getColumnIndex("Thongtin"));
            String lienheNCC = cursor.getString(cursor.getColumnIndex("Lienhe"));
            String emailNCC = cursor.getString(cursor.getColumnIndex("Email"));

            nccInfor = new NhaCungCap(tenNCC, thongTinNCC, lienheNCC,emailNCC);
        }
        cursor.close();
        db.close();
        return nccInfor;
    }

    public boolean updateNCC(NhaCungCap nhaCungCap) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NhaCungCap WHERE maNCC = ?",new String[] {String.valueOf(nhaCungCap)});
        ContentValues contentValues = new ContentValues();
        contentValues.put("Tennhacc", nhaCungCap.getTenNhaCC());
        contentValues.put("Thongtin", nhaCungCap.getThongTin());
        contentValues.put("Lienhe", nhaCungCap.getLienHe());
        contentValues.put("Email", nhaCungCap.getEmail());
        long check =  sqLiteDatabase.update("NhaCungCap",contentValues,"maNCC = ?",new String[]{String.valueOf(nhaCungCap.getMaNCC())} );

        if(check == -1) {
            return false;
        }
        return true;
    }

    public boolean addNCC(NhaCungCap nhaCungCap){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NhaCungCap WHERE maNCC = ?",new String[] {String.valueOf(nhaCungCap.getMaNCC())});
        ContentValues contentValues = new ContentValues();
        contentValues.put("Tennhacc", nhaCungCap.getTenNhaCC());
        contentValues.put("Thongtin", nhaCungCap.getThongTin());
        contentValues.put("Lienhe",nhaCungCap.getLienHe());
        contentValues.put("Email",nhaCungCap.getEmail());
        long check =  sqLiteDatabase.insert("NhaCungCap",null, contentValues);
        if(check == -1) {
            return false;
        }
        return true;
    }
}
