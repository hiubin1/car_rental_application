package com.example.pjthuexeapp.Database;

import static com.example.pjthuexeapp.Database.DBHelper.COT_IDXE_HOPDONG;
import static com.example.pjthuexeapp.Database.DBHelper.COT_ID_HOPDONG;
import static com.example.pjthuexeapp.Database.DBHelper.TEN_BANG_HOPDONG;
import static com.example.pjthuexeapp.Database.DBHelper.TEN_BANG_XE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pjthuexeapp.Class.Xe;

public class Database_Xe {

    SQLiteDatabase database;

    DBHelper helper;

    public Database_Xe (Context context){
        helper = new DBHelper(context);

        database = helper.getWritableDatabase();
    }

    public long ThemXe(Xe xe){
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_LOAI_XE, xe.getLoaiXe());
        values.put(DBHelper.COT_TEN_XE, xe.getTenXe());
        values.put(DBHelper.COT_SOGHE_XE, xe.getSoGhe());
        values.put(DBHelper.COT_BIENSO_XE, xe.getBienSoXe());
        values.put(DBHelper.COT_HINH_XE,xe.getHinhXe());
        values.put(DBHelper.COT_DIADIEMNHAN_XE, xe.getDiaDiemNhanXe());
        values.put(DBHelper.COT_TRUYENDONG_XE, xe.getTruyenDong());
        values.put(DBHelper.COT_NHIENLIEU_XE, xe.getNhienLieu());
        values.put(DBHelper.COT_NLTIEUHAO_XE, xe.getNangLuongTieuHao());
        values.put(DBHelper.COT_GIATIEN_XE, xe.getGiaTien());
        values.put(DBHelper.COT_TRANGTHAI_XE, xe.getTrangThai());

        return database.insert(DBHelper.TEN_BANG_XE, null,values);
    }

    public long SuaXe(Xe xe){
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_LOAI_XE, xe.getLoaiXe());
        values.put(DBHelper.COT_TEN_XE, xe.getTenXe());
        values.put(DBHelper.COT_SOGHE_XE, xe.getSoGhe());
        values.put(DBHelper.COT_BIENSO_XE, xe.getBienSoXe());
        values.put(DBHelper.COT_DIADIEMNHAN_XE, xe.getDiaDiemNhanXe());
        values.put(DBHelper.COT_TRUYENDONG_XE, xe.getTruyenDong());
        values.put(DBHelper.COT_NHIENLIEU_XE, xe.getNhienLieu());
        values.put(DBHelper.COT_NLTIEUHAO_XE, xe.getNangLuongTieuHao());
        values.put(DBHelper.COT_GIATIEN_XE, xe.getGiaTien());
        values.put(DBHelper.COT_TRANGTHAI_XE, xe.getTrangThai());
        values.put(DBHelper.COT_HINH_XE,xe.getHinhXe());

        return database.update(DBHelper.TEN_BANG_XE,values,
                DBHelper.COT_ID_XE + " = " + xe.getId(), null);
    }

    public long XoaXe(Xe xe){
        return database.delete(DBHelper.TEN_BANG_XE
        ,DBHelper.COT_ID_XE + " = " + xe.getId(),null);
    }


    public Cursor LayTatCaDuLieu(){
        String[] cot = {
                DBHelper.COT_ID_XE,
                DBHelper.COT_LOAI_XE,
                DBHelper.COT_TEN_XE,
                DBHelper.COT_BIENSO_XE,
                DBHelper.COT_NHIENLIEU_XE,
                DBHelper.COT_SOGHE_XE,
                DBHelper.COT_TRUYENDONG_XE,
                DBHelper.COT_HINH_XE,
                DBHelper.COT_DIADIEMNHAN_XE,
                DBHelper.COT_NLTIEUHAO_XE,
                DBHelper.COT_GIATIEN_XE,
                DBHelper.COT_TRANGTHAI_XE};

            Cursor cursor = null;

            cursor = database.query(DBHelper.TEN_BANG_XE,cot,null,null,null,null,null);

            return cursor;
    }

    public Cursor GetXeFromId(long id){
        String[] COT = {String.valueOf(id)};
        String query = "select * from Xe where ID_Xe = ?";
        Cursor cursor = database.rawQuery(query,COT);
        return cursor;
    }

    public boolean KiemTraBienSoXe(String BienSoXe){
        String[] cot = new String[]{BienSoXe};
        boolean TonTai = false;
        String query = "Select BienSo_Xe from Xe where BienSo_Xe = ?";

        Cursor cursor = database.rawQuery(query,cot);
        if (cursor.moveToFirst()){
            TonTai = true;
        }
        cursor.close();
        return TonTai;
    }

    public Cursor GeXeFromBienSoXe(String BienSoXe){
        String[] cot = {BienSoXe};

        Cursor cursor = null;

        String query = "Select * from Xe where BienSo_Xe = ?";

        cursor = database.rawQuery(query, cot);

        return cursor;
    }

    public Cursor search( String tenXe) {
        String[] columns = {"%" + tenXe + "%"};

        String query = "SELECT * FROM Xe WHERE Ten_Xe LIKE ?";

        Cursor cursor = database.rawQuery(query, columns);

        return cursor;
    }

}
