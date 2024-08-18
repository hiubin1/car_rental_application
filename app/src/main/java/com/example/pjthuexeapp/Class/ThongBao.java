package com.example.pjthuexeapp.Class;

public class ThongBao {
    long id, idCus;

    String TieuDe, NoiDung;

    public ThongBao(long id, long idCus, String tieuDe, String noiDung) {
        this.id = id;
        this.idCus = idCus;
        TieuDe = tieuDe;
        NoiDung = noiDung;
    }

    public ThongBao() {
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

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }
}
