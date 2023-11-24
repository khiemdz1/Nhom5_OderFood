package com.example.nhom5_oderfood.DTO;

import java.io.Serializable;

public class NhaCungCap implements Serializable {

    private int maNCC;

    private String tenNhaCC;
    private String thongTin,lienHe,email;

    public NhaCungCap() {
    }

    public NhaCungCap(int maNCC, String tenNhaCC, String thongTin, String lienHe, String email) {
        this.maNCC = maNCC;
        this.tenNhaCC = tenNhaCC;
        this.thongTin = thongTin;
        this.lienHe = lienHe;
        this.email = email;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNhaCC() {
        return tenNhaCC;
    }

    public void setTenNhaCC(String tenNhaCC) {
        this.tenNhaCC = tenNhaCC;
    }

    public String getThongTin() {
        return thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }

    public String getLienHe() {
        return lienHe;
    }

    public void setLienHe(String lienHe) {
        this.lienHe = lienHe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
