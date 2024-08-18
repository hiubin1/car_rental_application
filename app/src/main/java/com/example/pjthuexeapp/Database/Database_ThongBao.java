package com.example.pjthuexeapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pjthuexeapp.Class.ThongBao;

public class Database_ThongBao {
    SQLiteDatabase database;

    DBHelper helper;

    public Database_ThongBao (Context context){
        helper = new DBHelper(context);

        database = helper.getWritableDatabase();
    }

    public Cursor layTatCaDuLieu(){
        String[] cot = {DBHelper.COT_ID_THONGBAO,
                DBHelper.COT_IDCUS_THONGBAO,
                DBHelper.COT_TIEUDE_THONGBAO,
                DBHelper.COT_NOIDUNG_THONGBAO};

        Cursor cursor = null;
        cursor = database.query(DBHelper.TEN_BANG_THONGBAO,cot,null,null,null,null,DBHelper.COT_ID_THONGBAO + " DESC");

        return cursor;
    }

    public long ThemThongBao(ThongBao thongBao){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_IDCUS_THONGBAO,thongBao.getIdCus());
        values.put(DBHelper.COT_TIEUDE_THONGBAO,thongBao.getTieuDe());
        values.put(DBHelper.COT_NOIDUNG_THONGBAO,thongBao.getNoiDung());

        return database.insert(DBHelper.TEN_BANG_THONGBAO,null,values);

    }

    public Cursor GetThongBaoFromId(long id) {
        String[] cot = {String.valueOf(id)};
        String query = "select * from ThongBao where IDCus_ThongBao = ? order by ID_ThongBao DESC";
        Cursor cursor = database.rawQuery(query, cot);
        return cursor;
    }

}
