package com.example.nhom5_oderfood.DTO;

public class HoaDon {
    private int idHD;
    private String maHD;
    private String tenkhachhangHD;
    private String sdtkhachhangHD;
    private String diachikhachhangHD;
    private String tenmonanHD;
    private int soluongHD;
    private String ngaydatHD;
    private int giaHD;
    private int makh;

    public HoaDon() {
    }

    public HoaDon(int idHD, String maHD, String tenkhachhangHD, String sdtkhachhangHD, String diachikhachhangHD, String tenmonanHD, int soluongHD, String ngaydatHD, int giaHD, int makh) {
        this.idHD = idHD;
        this.maHD = maHD;
        this.tenkhachhangHD = tenkhachhangHD;
        this.sdtkhachhangHD = sdtkhachhangHD;
        this.diachikhachhangHD = diachikhachhangHD;
        this.tenmonanHD = tenmonanHD;
        this.soluongHD = soluongHD;
        this.ngaydatHD = ngaydatHD;
        this.giaHD = giaHD;
        this.makh = makh;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenkhachhangHD() {
        return tenkhachhangHD;
    }

    public void setTenkhachhangHD(String tenkhachhangHD) {
        this.tenkhachhangHD = tenkhachhangHD;
    }

    public String getSdtkhachhangHD() {
        return sdtkhachhangHD;
    }

    public void setSdtkhachhangHD(String sdtkhachhangHD) {
        this.sdtkhachhangHD = sdtkhachhangHD;
    }

    public String getDiachikhachhangHD() {
        return diachikhachhangHD;
    }

    public void setDiachikhachhangHD(String diachikhachhangHD) {
        this.diachikhachhangHD = diachikhachhangHD;
    }

    public String getTenmonanHD() {
        return tenmonanHD;
    }

    public void setTenmonanHD(String tenmonanHD) {
        this.tenmonanHD = tenmonanHD;
    }

    public int getSoluongHD() {
        return soluongHD;
    }

    public void setSoluongHD(int soluongHD) {
        this.soluongHD = soluongHD;
    }

    public String getNgaydatHD() {
        return ngaydatHD;
    }

    public void setNgaydatHD(String ngaydatHD) {
        this.ngaydatHD = ngaydatHD;
    }

    public int getGiaHD() {
        return giaHD;
    }

    public void setGiaHD(int giaHD) {
        this.giaHD = giaHD;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }
}
