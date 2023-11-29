package com.example.nhom5_oderfood.Dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbhelper extends SQLiteOpenHelper {
    static final String DB_NAME = "OrderFood.db";
    static final int DB_VERSION = 1;

    public MyDbhelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      // bảng khách hàng
        String Tablekhachhang = "CREATE TABLE KhachHang(" +
                "MaKH INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "Fullname TEXT NOT NULL," +
                "Sdt TEXT NOT NULL," +
                "Diachi TEXT NOT NULL)";
        db.execSQL(Tablekhachhang);
        // bảng loại món ăn
        String TableTheloai = "CREATE TABLE TheLoai(" +
                "MaTL INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Tentheloai TEXT NOT NULL)";
        db.execSQL(TableTheloai);
//        // bảng món ăn
        String Tablemonan = "CREATE TABLE MonAn(" +
                "MaMA INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Hinhanh TEXT NOT NULL," +
                "Tenmonan TEXT NOT NULL," +
                "MaTL INTEGER REFERENCES TheLoai(MaTL)," +
                "Dongia INTEGER NOT NULL," +
                "Mota TEXT NOT NULL)";
        db.execSQL(Tablemonan);

        // Bảng Hóa Đơn
        String Tablehoadon = "CREATE TABLE HoaDon(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MaHD TEXT NOT NULL," +
                "TenHD TEXT NOT NULL," +
                "SdtHD TEXT NOT NULL," +
                "DiachiHD TEXT NOT NULL," +
                "TenmonanHD TEXT NOT NULL," +
                "SoluongHD TEXT NOT NULL," +
                "NgaydatHD TEXT NOT NULL," +
                "GiaHD TEXT NOT NULL," +
                "MaKH INTEGER," +
                "FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH))";
        db.execSQL(Tablehoadon);
        // Bảng nhà cung cấp
        String Tablenhacc = "CREATE TABLE NhaCungCap(" +
                "MaNCC INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Tennhacc TEXT NOT NULL," +
                "Thongtin TEXT NOT NULL," +
                "Lienhe TEXT NOT NULL," +
                "Email TEXT NOT NULL)";
        db.execSQL(Tablenhacc);
        db.execSQL("INSERT INTO KhachHang VALUES (1,'admin','admin','Cấn Gia Khiêm',0372858655,'BinhPhu,ThachThat,HaNoi')");

        db.execSQL("Insert Into TheLoai values(1,'Đồ ăn'),(2,'Đồ uống')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String tablekhachhang = "drop table if exists KhachHang";
        db.execSQL(tablekhachhang);
        String tableloaimonan = "drop table if exists TheLoai";
        db.execSQL(tableloaimonan);
        String tablemonan = "drop table if exists MonAn";
        db.execSQL(tablemonan);
        String tablehoadon = "drop table if exists HoaDon";
        db.execSQL(tablehoadon);
        String tablenhacc= "drop table if exists NhaCungCap";
        db.execSQL(tablenhacc);

        onCreate(db);
    }
}
