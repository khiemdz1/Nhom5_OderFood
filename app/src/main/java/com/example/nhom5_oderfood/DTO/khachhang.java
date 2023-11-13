package com.example.nhom5_oderfood.DTO;

public class khachhang {
    int makh;
    String username, password, fullname, sdt, diachi;

    public khachhang() {
    }

    public khachhang(String username, String password, String fullname, String sdt, String diachi) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public khachhang(int makh, String username, String password, String fullname, String sdt, String diachi) {
        this.makh = makh;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
