package com.example.pjthuexeapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pjthuexeapp.Class.HoaDon;

public class Database_DonDatXe {
    SQLiteDatabase database;

    DBHelper helper;

    public Database_DonDatXe (Context context){
        helper = new DBHelper(context);

        database = helper.getWritableDatabase();
    }

    public Cursor layTatCaDuLieu(){
        String[] cot = {DBHelper.COT_ID_HOADON,
            DBHelper.COT_IDCUS_HOADON,
            DBHelper.COT_IDXE_HOADON,
            DBHelper.COT_IDTHUEXE_HOADON,
            DBHelper.COT_TENXE_HOADON,
            DBHelper.COT_TENCUS_HOADON,
            DBHelper.COT_NGAYTRAXE_HOADON,
            DBHelper.COT_NGAYNHANXE_HOADON,
            DBHelper.COT_SDTCUS_HOADON,
            DBHelper.COT_TONGNGAYTHUE_HOADON,
                DBHelper.COT_GIATIENTHUEXE_HOADON,
            DBHelper.COT_TONGTIEN_HOADON,
            DBHelper.COT_NGAYDATDON_HOADON,
            DBHelper.COT_GIOTRAXE_HOADON,
            DBHelper.COT_GIONHANXE_HOADON,
            DBHelper.COT_DIADIEMTRAXE_HOADON,
            DBHelper.COT_DIADIEMNHANXE_HOADON,
            DBHelper.COT_BIENSOXE_HOADON,
            DBHelper.COT_CMND_HOADON};
        Cursor cursor = null;
        cursor = database.query(DBHelper.TEN_BANG_DONDATXE,cot, null,null,null,null,DBHelper.COT_ID_HOADON + " DESC");

        return cursor;
    }

    public long ThemHoaDon(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_CMND_HOADON, hoaDon.getCMND());
        values.put(DBHelper.COT_BIENSOXE_HOADON, hoaDon.getBienSoXe());
        values.put(DBHelper.COT_DIADIEMNHANXE_HOADON, hoaDon.getDiaDiemNhanXe());
        values.put(DBHelper.COT_DIADIEMTRAXE_HOADON, hoaDon.getDiaDiemTraXe());
        values.put(DBHelper.COT_GIONHANXE_HOADON, hoaDon.getGioNhanXe());
        values.put(DBHelper.COT_GIOTRAXE_HOADON, hoaDon.getGioTraXe());
        values.put(DBHelper.COT_NGAYDATDON_HOADON, hoaDon.getNgatDatDon());
        values.put(DBHelper.COT_TONGTIEN_HOADON, hoaDon.getTongTien());
        values.put(DBHelper.COT_TONGNGAYTHUE_HOADON, hoaDon.getTongNgayThue());
        values.put(DBHelper.COT_SDTCUS_HOADON, hoaDon.getSDTCus());
        values.put(DBHelper.COT_GIATIENTHUEXE_HOADON, hoaDon.getGiaTienThueXe());
        values.put(DBHelper.COT_NGAYNHANXE_HOADON, hoaDon.getNgayNhanXe());
        values.put(DBHelper.COT_NGAYTRAXE_HOADON, hoaDon.getNgayTraXe());
        values.put(DBHelper.COT_TENCUS_HOADON, hoaDon.getTenCus());
        values.put(DBHelper.COT_TENXE_HOADON, hoaDon.getTenXe());
        values.put(DBHelper.COT_IDTHUEXE_HOADON, hoaDon.getIdThueXe());
        values.put(DBHelper.COT_IDXE_HOADON, hoaDon.getIdXe());
        values.put(DBHelper.COT_IDCUS_HOADON,hoaDon.getIdCus());

        return database.insert(DBHelper.TEN_BANG_DONDATXE,null,values);
    }

    public Cursor GetHoaDonFromId(long id){
        String[] cot = {String.valueOf(id)};
        String query = "select * from DonDatXe where IDCus_HoaDon = ? order by ID_HoaDon DESC ";
        Cursor cursor = database.rawQuery(query,cot);
        return cursor;
    }


    public Cursor SumTotal(){
        String query = "select sum(cast(replace(TongTien_HoaDon,',000đ','.000') as double)) from DonDatXe";
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }

//    public boolean KiemTraXe(long idXe, String gioNhanXe, String gioTraXe, String ngayNhanXe, String ngayTraXe) {
//        boolean KiemTraXe = false;
//        String[] cot = {String.valueOf(idXe), gioNhanXe, gioTraXe, ngayNhanXe, ngayTraXe};
//        String query = "SELECT * FROM DonDatXe WHERE IDXe_HoaDon = ? AND NgayTraXe_HoaDon >= ? AND NgayNhanXe_HoaDon <= ? " +
//                "AND GioTraXe_HoaDon >= ? AND GioNhanXe_HoaDon <= ?";
//
//        Cursor cursor = database.rawQuery(query, cot);
//        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
//            KiemTraXe = true;
//        }
//        cursor.close();
//        return KiemTraXe;
//    }

    public boolean KiemTraXe(long idXe, String gioNhanXe, String gioTraXe, String ngayNhanXe, String ngayTraXe) {
        boolean KiemTraXe = false;
        String[] cot = {String.valueOf(idXe), gioNhanXe, gioTraXe, ngayNhanXe, ngayTraXe};
        String query = "SELECT * FROM DonDatXe WHERE IDXe_HoaDon = ? AND GioNhanXe_HoaDon <= ? AND GioTraXe_HoaDon >= ? " +
                "AND NgayNhanXe_HoaDon <= ? AND NgayTraXe_HoaDon >= ?";

        Cursor cursor = database.rawQuery(query, cot);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            KiemTraXe = true;
        }
        cursor.close();
        return KiemTraXe;
    }

}
