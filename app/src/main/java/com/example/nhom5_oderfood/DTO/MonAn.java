package com.example.nhom5_oderfood.DTO;

import java.io.Serializable;

public class MonAn  implements Serializable{
    private int idMA;
    private String hinhMA;
    private String tenMA;
    private int loaiMA;
    private int giaMA;
    private String motaMA;
    private int soLuong;

    public MonAn() {
    }

    public MonAn(String hinhMA, String tenMA, int giaMA) {
        this.hinhMA = hinhMA;
        this.tenMA = tenMA;
        this.giaMA = giaMA;
    }
    public MonAn(int idMA, String hinhMA, String tenMA, int loaiMA, int giaMA, String motaMA) {
        this.idMA = idMA;
        this.hinhMA = hinhMA;
        this.tenMA = tenMA;
        this.loaiMA = loaiMA;
        this.giaMA = giaMA;
        this.motaMA = motaMA;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getIdMA() {
        return idMA;
    }

    public void setIdMA(int idMA) {
        this.idMA = idMA;
    }

    public String getHinhMA() {
        return hinhMA;
    }

    public void setHinhMA(String hinhMA) {
        this.hinhMA = hinhMA;
    }

    public String getTenMA() {
        return tenMA;
    }

    public void setTenMA(String tenMA) {
        this.tenMA = tenMA;
    }

    public int getLoaiMA() {
        return loaiMA;
    }

    public void setLoaiMA(int loaiMA) {
        this.loaiMA = loaiMA;
    }

    public int getGiaMA() {
        return giaMA;
    }

    public void setGiaMA(int giaMA) {
        this.giaMA = giaMA;
    }

    public String getMotaMA() {
        return motaMA;
    }

    public void setMotaMA(String motaMA) {
        this.motaMA = motaMA;
    }
}
