package com.example.nhom5_oderfood.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GioHang")
public class GioHang {
    @PrimaryKey(autoGenerate = true)
    private int idGH;
    private String hinhanhGH;
    private String tenGH;
    private int giaGH;
    private int soluongGH;

    public GioHang() {
        this.idGH = idGH;
        this.hinhanhGH = hinhanhGH;
        this.tenGH = tenGH;
        this.giaGH = giaGH;
        this.soluongGH = soluongGH;
    }

    public int getIdGH() {
        return idGH;
    }

    public void setIdGH(int idGH) {
        this.idGH = idGH;
    }

    public String getHinhanhGH() {
        return hinhanhGH;
    }

    public void setHinhanhGH(String hinhanhGH) {
        this.hinhanhGH = hinhanhGH;
    }

    public String getTenGH() {
        return tenGH;
    }

    public void setTenGH(String tenGH) {
        this.tenGH = tenGH;
    }

    public int getGiaGH() {
        return giaGH;
    }

    public void setGiaGH(int giaGH) {
        this.giaGH = giaGH;
    }

    public int getSoluongGH() {
        return soluongGH;
    }

    public void setSoluongGH(int soluongGH) {
        this.soluongGH = soluongGH;
    }
}
