package com.example.pjthuexeapp.Class;

import com.example.pjthuexeapp.R;

import java.util.ArrayList;

public class NguoiDung {
    private long id;
    private String HoTen;
    private String SDT;
    private String CCCD;
    private String Password;

    private String GPLX;
    private long Role;

    public NguoiDung() {}

    public NguoiDung(long id, String hoTen, String SDT, String CCCD, String password, String GPLX, long role) {
        this.id = id;
        HoTen = hoTen;
        this.SDT = SDT;
        this.CCCD = CCCD;
        Password = password;
        this.GPLX = GPLX;
        Role = role;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public long getRole() {
        return Role;
    }

    public void setRole(long role) {
        Role = role;
    }

    public String getGPLX() {
        return GPLX;
    }

    public void setGPLX(String GPLX) {
        this.GPLX = GPLX;
    }

}
