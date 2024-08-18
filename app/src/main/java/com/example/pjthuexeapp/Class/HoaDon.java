package com.example.pjthuexeapp.Class;

import java.io.Serializable;

public class HoaDon implements Serializable {
    long Id, IdXe, IdCus, IdThueXe;

    String NgatDatDon, TenCus, SDTCus, CMND, TenXe, BienSoXe, DiaDiemNhanXe, DiaDiemTraXe, NgayNhanXe, NgayTraXe, GioTraXe, GioNhanXe;

    int TongNgayThue;
    float TongTien,GiaTienThueXe;

    public HoaDon(long id, long idXe, long idCus, long idThueXe, String ngatDatDon, String tenCus, String SDTCus, String CMND, String tenXe, String bienSoXe, String diaDiemNhanXe, String diaDiemTraXe, String ngayNhanXe, String ngayTraXe, String gioTraXe, String gioNhanXe, int tongNgayThue, float tongTien, float giaTienThueXe) {
        Id = id;
        IdXe = idXe;
        IdCus = idCus;
        IdThueXe = idThueXe;
        NgatDatDon = ngatDatDon;
        TenCus = tenCus;
        this.SDTCus = SDTCus;
        this.CMND = CMND;
        TenXe = tenXe;
        BienSoXe = bienSoXe;
        DiaDiemNhanXe = diaDiemNhanXe;
        DiaDiemTraXe = diaDiemTraXe;
        NgayNhanXe = ngayNhanXe;
        NgayTraXe = ngayTraXe;
        GioTraXe = gioTraXe;
        GioNhanXe = gioNhanXe;
        TongNgayThue = tongNgayThue;
        TongTien = tongTien;
        GiaTienThueXe = giaTienThueXe;
    }

    public HoaDon() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getIdXe() {
        return IdXe;
    }

    public void setIdXe(long idXe) {
        IdXe = idXe;
    }

    public long getIdCus() {
        return IdCus;
    }

    public void setIdCus(long idCus) {
        IdCus = idCus;
    }

    public long getIdThueXe() {
        return IdThueXe;
    }

    public void setIdThueXe(long idThueXe) {
        IdThueXe = idThueXe;
    }

    public String getNgatDatDon() {
        return NgatDatDon;
    }

    public void setNgatDatDon(String ngatDatDon) {
        NgatDatDon = ngatDatDon;
    }

    public String getTenCus() {
        return TenCus;
    }

    public void setTenCus(String tenCus) {
        TenCus = tenCus;
    }

    public String getSDTCus() {
        return SDTCus;
    }

    public void setSDTCus(String SDTCus) {
        this.SDTCus = SDTCus;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getTenXe() {
        return TenXe;
    }

    public void setTenXe(String tenXe) {
        TenXe = tenXe;
    }

    public String getBienSoXe() {
        return BienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        BienSoXe = bienSoXe;
    }

    public String getDiaDiemNhanXe() {
        return DiaDiemNhanXe;
    }

    public void setDiaDiemNhanXe(String diaDiemNhanXe) {
        DiaDiemNhanXe = diaDiemNhanXe;
    }

    public String getDiaDiemTraXe() {
        return DiaDiemTraXe;
    }

    public void setDiaDiemTraXe(String diaDiemTraXe) {
        DiaDiemTraXe = diaDiemTraXe;
    }

    public String getNgayNhanXe() {
        return NgayNhanXe;
    }

    public void setNgayNhanXe(String ngayNhanXe) {
        NgayNhanXe = ngayNhanXe;
    }

    public String getNgayTraXe() {
        return NgayTraXe;
    }

    public void setNgayTraXe(String ngayTraXe) {
        NgayTraXe = ngayTraXe;
    }

    public int getTongNgayThue() {
        return TongNgayThue;
    }

    public void setTongNgayThue(int tongNgayThue) {
        TongNgayThue = tongNgayThue;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float tongTien) {
        TongTien = tongTien;
    }

    public String getGioTraXe() {
        return GioTraXe;
    }

    public void setGioTraXe(String gioTraXe) {
        GioTraXe = gioTraXe;
    }

    public String getGioNhanXe() {
        return GioNhanXe;
    }

    public void setGioNhanXe(String gioNhanXe) {
        GioNhanXe = gioNhanXe;
    }

    public float getGiaTienThueXe() {
        return GiaTienThueXe;
    }

    public void setGiaTienThueXe(float giaTienThueXe) {
        GiaTienThueXe = giaTienThueXe;
    }
}
