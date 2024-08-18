package com.example.pjthuexeapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pjthuexeapp.Class.DonGiaHan;
import com.example.pjthuexeapp.Class.HoaDon;

public class Database_DonGiaHan {
    SQLiteDatabase database;

    DBHelper helper;

    public Database_DonGiaHan (Context context){
        helper = new DBHelper(context);

        database = helper.getWritableDatabase();
    }

    public long ThemDonDatXe(DonGiaHan donGiaHan){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_IDHOADON_DONGIAHAN, donGiaHan.getIdHoaDon());
        values.put(DBHelper.COT_NGAYBATDAUGIAHAN_DONGIAHAN, donGiaHan.getNgayBatDauGiaHan());
        values.put(DBHelper.COT_TONGNGAYGIAHAN_DONGIAHAN,donGiaHan.getTongNgayGiaHan());
        values.put(DBHelper.COT_TONGTIEN_DONGIAHAN,donGiaHan.getTongTien());
        return database.insert(DBHelper.TEN_BANG_DONGIAHAN, null,values);
    }

    public Cursor SumTotal(){
        String query = "select sum(cast(replace(TongTien_DonGiaHan,',000đ','.000') as double)) from DonGiaHan";
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }

    public Cursor LayTatCaDuLieu(){
        String[] cot = {
                DBHelper.COT_ID_DONGIAHAN,
                DBHelper.COT_IDHOADON_DONGIAHAN,
                DBHelper.COT_TONGTIEN_DONGIAHAN,
                DBHelper.COT_TONGNGAYGIAHAN_DONGIAHAN,
                DBHelper.COT_NGAYBATDAUGIAHAN_DONGIAHAN
        };
        Cursor cursor = null;

        cursor = database.query(DBHelper.TEN_BANG_DONGIAHAN,cot,null,null,null,null,DBHelper.COT_ID_DONGIAHAN + " DESC");
        return cursor;
    }

    public Cursor GetDonGiaHanFromId(long id){
        String[] cot = {String.valueOf(id)};
        String query = "select * from DonGiaHan where IDHoaDon_DonGiaHan = ? order by ID_DonGiaHan DESC ";
        Cursor cursor = database.rawQuery(query,cot);
        return cursor;
    }
}
