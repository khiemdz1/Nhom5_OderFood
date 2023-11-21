package com.example.nhom5_oderfood.FragmentKhachHang.databasegiohang;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.nhom5_oderfood.DTO.GioHang;


import java.util.List;

@Dao
public interface GioHangDAO {
    @Insert
    void insertMonan(GioHang gioHang);
    @Query("SELECT * FROM GioHang")
    List<GioHang> getlitsMonan();
    @Delete
    void deleteMonAn(GioHang gioHang);
    @Query("SELECT COUNT(*) FROM GioHang WHERE tenGH = :tenGH")
    int checkMonan(String tenGH);
}
