package com.example.pjthuexeapp.Class;

public class DonGiaHan {

    long id, idHoaDon;

    String NgayBatDauGiaHan;

    int TongNgayGiaHan;

    float TongTien;

    public DonGiaHan(long id, long idHoaDon, String ngayBatDauGiaHan, int tongNgayGiaHan, float tongTien) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        NgayBatDauGiaHan = ngayBatDauGiaHan;
        TongNgayGiaHan = tongNgayGiaHan;
        TongTien = tongTien;
    }

    public DonGiaHan() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(long idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getNgayBatDauGiaHan() {
        return NgayBatDauGiaHan;
    }

    public void setNgayBatDauGiaHan(String ngayBatDauGiaHan) {
        NgayBatDauGiaHan = ngayBatDauGiaHan;
    }

    public int getTongNgayGiaHan() {
        return TongNgayGiaHan;
    }

    public void setTongNgayGiaHan(int tongNgayGiaHan) {
        TongNgayGiaHan = tongNgayGiaHan;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float tongTien) {
        TongTien = tongTien;
    }
}
