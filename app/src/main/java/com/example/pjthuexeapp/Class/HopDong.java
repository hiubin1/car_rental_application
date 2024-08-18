package com.example.pjthuexeapp.Class;

import java.io.Serializable;

public class HopDong implements Serializable {
    long id, idCus, idXe, idThueXe;
    String DiaDiemNhanXe, BienSoXe, CCCD, DiaDiemTraXe, GioNhanXe, GioTraXe, LoaiXe,
    NgayDatXe, NgayTraXe, SoGhe, TenCus, TenXe, TruyenDong, NgayDat;

    int SoTienThue, TongNgayThue;

    public HopDong(long id, long idCus, long idXe, long idThueXe, String diaDiemNhanXe, String bienSoXe, String CCCD, String diaDiemTraXe, String gioNhanXe, String gioTraXe, String loaiXe, String ngayDatXe, String ngayTraXe, String soGhe, String tenCus, String tenXe, String truyenDong, String ngayDat, int soTienThue, int tongNgayThue) {
        this.id = id;
        this.idCus = idCus;
        this.idXe = idXe;
        this.idThueXe = idThueXe;
        DiaDiemNhanXe = diaDiemNhanXe;
        BienSoXe = bienSoXe;
        this.CCCD = CCCD;
        DiaDiemTraXe = diaDiemTraXe;
        GioNhanXe = gioNhanXe;
        GioTraXe = gioTraXe;
        LoaiXe = loaiXe;
        NgayDatXe = ngayDatXe;
        NgayTraXe = ngayTraXe;
        SoGhe = soGhe;
        TenCus = tenCus;
        TenXe = tenXe;
        TruyenDong = truyenDong;
        NgayDat = ngayDat;
        SoTienThue = soTienThue;
        TongNgayThue = tongNgayThue;
    }

    public HopDong() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCus() {
        return idCus;
    }

    public void setIdCus(long idCus) {
        this.idCus = idCus;
    }

    public long getIdXe() {
        return idXe;
    }

    public void setIdXe(long idXe) {
        this.idXe = idXe;
    }

    public long getIdThueXe() {
        return idThueXe;
    }

    public void setIdThueXe(long idThueXe) {
        this.idThueXe = idThueXe;
    }

    public String getDiaDiemNhanXe() {
        return DiaDiemNhanXe;
    }

    public void setDiaDiemNhanXe(String diaDiemNhanXe) {
        DiaDiemNhanXe = diaDiemNhanXe;
    }

    public String getBienSoXe() {
        return BienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        BienSoXe = bienSoXe;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaDiemTraXe() {
        return DiaDiemTraXe;
    }

    public void setDiaDiemTraXe(String diaDiemTraXe) {
        DiaDiemTraXe = diaDiemTraXe;
    }

    public String getGioNhanXe() {
        return GioNhanXe;
    }

    public void setGioNhanXe(String gioNhanXe) {
        GioNhanXe = gioNhanXe;
    }

    public String getGioTraXe() {
        return GioTraXe;
    }

    public void setGioTraXe(String gioTraXe) {
        GioTraXe = gioTraXe;
    }

    public String getLoaiXe() {
        return LoaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        LoaiXe = loaiXe;
    }

    public String getNgayDatXe() {
        return NgayDatXe;
    }

    public void setNgayDatXe(String ngayDatXe) {
        NgayDatXe = ngayDatXe;
    }

    public String getNgayTraXe() {
        return NgayTraXe;
    }

    public void setNgayTraXe(String ngayTraXe) {
        NgayTraXe = ngayTraXe;
    }

    public String getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(String soGhe) {
        SoGhe = soGhe;
    }

    public String getTenCus() {
        return TenCus;
    }

    public void setTenCus(String tenCus) {
        TenCus = tenCus;
    }

    public String getTenXe() {
        return TenXe;
    }

    public void setTenXe(String tenXe) {
        TenXe = tenXe;
    }

    public String getTruyenDong() {
        return TruyenDong;
    }

    public void setTruyenDong(String truyenDong) {
        TruyenDong = truyenDong;
    }

    public int getSoTienThue() {
        return SoTienThue;
    }

    public void setSoTienThue(int soTienThue) {
        SoTienThue = soTienThue;
    }

    public int getTongNgayThue() {
        return TongNgayThue;
    }

    public void setTongNgayThue(int tongNgayThue) {
        TongNgayThue = tongNgayThue;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }
}
