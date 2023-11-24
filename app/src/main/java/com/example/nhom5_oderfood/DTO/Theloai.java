package com.example.nhom5_oderfood.DTO;

public class Theloai {
    private String maTL;
    private String tenTheLoai;

    public Theloai(String maTL, String tenTheLoai) {
        this.maTL = maTL;
        this.tenTheLoai = tenTheLoai;
    }

    public String getMaTL() {
        return maTL;
    }

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
