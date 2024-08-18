package com.example.pjthuexeapp.Class;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Xe implements Serializable {
    long id;

    byte[] HinhXe;
    String BienSoXe;

    String LoaiXe;

    String TenXe;
    String DiaDiemNhanXe;
    String SoGhe;

    String TruyenDong;
    String NhienLieu;
    String NangLuongTieuHao;
    int GiaTien;
    String TrangThai;

    public Xe(long id, byte[] hinhXe, String bienSoXe, String loaiXe, String tenXe, String diaDiemNhanXe, String soGhe, String truyenDong, String nhienLieu, String nangLuongTieuHao, int giaTien, String trangThai) {
        this.id = id;
        HinhXe = hinhXe;
        BienSoXe = bienSoXe;
        LoaiXe = loaiXe;
        TenXe = tenXe;
        DiaDiemNhanXe = diaDiemNhanXe;
        SoGhe = soGhe;
        TruyenDong = truyenDong;
        NhienLieu = nhienLieu;
        NangLuongTieuHao = nangLuongTieuHao;
        GiaTien = giaTien;
        TrangThai = trangThai;
    }

    public Xe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getBienSoXe() {
        return BienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        BienSoXe = bienSoXe;
    }

    public String getTenXe() {
        return TenXe;
    }

    public void setTenXe(String tenXe) {
        TenXe = tenXe;
    }

    public String getDiaDiemNhanXe() {
        return DiaDiemNhanXe;
    }

    public void setDiaDiemNhanXe(String diaDiemNhanXe) {
        DiaDiemNhanXe = diaDiemNhanXe;
    }

    public String getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(String soGhe) {
        SoGhe = soGhe;
    }

    public String getTruyenDong() {
        return TruyenDong;
    }

    public void setTruyenDong(String truyenDong) {
        TruyenDong = truyenDong;
    }

    public String getNhienLieu() {
        return NhienLieu;
    }

    public void setNhienLieu(String nhienLieu) {
        NhienLieu = nhienLieu;
    }

    public String getNangLuongTieuHao() {
        return NangLuongTieuHao;
    }

    public void setNangLuongTieuHao(String nangLuongTieuHao) {
        NangLuongTieuHao = nangLuongTieuHao;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public byte[] getHinhXe() {
        return HinhXe;
    }

    public void setHinhXe(byte[] hinhXe) {
        HinhXe = hinhXe;
    }

    public String getLoaiXe() {
        return LoaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        LoaiXe = loaiXe;
    }
}
