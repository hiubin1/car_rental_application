package com.example.pjthuexeapp.Database;

import static com.example.pjthuexeapp.Database.DBHelper.COT_IDXE_HOPDONG;
import static com.example.pjthuexeapp.Database.DBHelper.TEN_BANG_XE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pjthuexeapp.Class.HopDong;

public class Database_HopDong {
    SQLiteDatabase database;

    DBHelper helper;

    public Database_HopDong (Context context){
        helper = new DBHelper(context);

        database = helper.getWritableDatabase();
    }

    public Cursor layTatCaDuLieu(){
        String[] cot = {DBHelper.COT_ID_HOPDONG,
                DBHelper.COT_IDCUS_HOPDONG,
                DBHelper.COT_IDXE_HOPDONG,
                DBHelper.COT_IDTHUEXE_HOPDONG,
                DBHelper.COT_BIENSOXE_HOPDONG,
                DBHelper.COT_CCCD_HOPDONG,
                DBHelper.COT_DIADIEMNHANXE_HOPDONG,
                DBHelper.COT_GIONHANXE_HOPDONG,
                DBHelper.COT_DIADIEMTRAXE_HOPDONG,
                DBHelper.COT_GIONHANXE_HOPDONG,
                DBHelper.COT_DIADIEMTRAXE_HOPDONG,
                DBHelper.COT_GIOTRAXE_HOPDONG,
                DBHelper.COT_LOAIXE_HOPDONG,
                DBHelper.COT_NGAYDAT_HOPDONG,
                DBHelper.COT_NGAYDATXE_HOPDONG,
                DBHelper.COT_NGAYTRAXE_HOPDONG,
                DBHelper.COT_SOGHE_HOPDONG,
                DBHelper.COT_SOTIENTHUE_HOPDONG,
                DBHelper.COT_TENCUS_HOPDONG,
                DBHelper.COT_TENXE_HOPDONG,
                DBHelper.COT_TONGNGAYTHUE_HOPDONG,
                DBHelper.COT_TRUYENDONG_HOPDONG};

        Cursor cursor = null;
        cursor = database.query(DBHelper.TEN_BANG_HOPDONG ,cot, null,null,null,null,DBHelper.COT_ID_HOPDONG +" DESC");

        return  cursor;
    }

    public long ThemHopDong(HopDong hopDong){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_IDCUS_HOPDONG, hopDong.getIdCus());
        values.put(DBHelper.COT_IDXE_HOPDONG, hopDong.getIdXe());
        values.put(DBHelper.COT_IDTHUEXE_HOPDONG, hopDong.getIdThueXe());
        values.put(DBHelper.COT_BIENSOXE_HOPDONG, hopDong.getBienSoXe());
        values.put(DBHelper.COT_CCCD_HOPDONG, hopDong.getCCCD());
        values.put(DBHelper.COT_DIADIEMNHANXE_HOPDONG, hopDong.getDiaDiemNhanXe());
        values.put(DBHelper.COT_GIONHANXE_HOPDONG, hopDong.getGioNhanXe());
        values.put(DBHelper.COT_DIADIEMTRAXE_HOPDONG, hopDong.getDiaDiemTraXe());
        values.put(DBHelper.COT_GIOTRAXE_HOPDONG, hopDong.getGioTraXe());
        values.put(DBHelper.COT_LOAIXE_HOPDONG, hopDong.getLoaiXe());
        values.put(DBHelper.COT_NGAYDAT_HOPDONG,hopDong.getNgayDat());
        values.put(DBHelper.COT_NGAYDATXE_HOPDONG, hopDong.getNgayDatXe());
        values.put(DBHelper.COT_NGAYTRAXE_HOPDONG, hopDong.getNgayTraXe());
        values.put(DBHelper.COT_SOGHE_HOPDONG, hopDong.getSoGhe());
        values.put(DBHelper.COT_SOTIENTHUE_HOPDONG, hopDong.getSoTienThue());
        values.put(DBHelper.COT_TENCUS_HOPDONG,hopDong.getTenCus());
        values.put(DBHelper.COT_TENXE_HOPDONG,hopDong.getTenXe());
        values.put(DBHelper.COT_TONGNGAYTHUE_HOPDONG,hopDong.getTongNgayThue());
        values.put(DBHelper.COT_TRUYENDONG_HOPDONG,hopDong.getTruyenDong());

        return database.insert(DBHelper.TEN_BANG_HOPDONG,null,values);
    }
    public Cursor GetHopDongFromId(long id){
        String[] cot = {String.valueOf(id)};
        String query = "select * from HopDong where IDCus_HopDong = ? order by ID_HopDong DESC ";
        Cursor cursor = database.rawQuery(query,cot);
        return cursor;
    }

    public Cursor search( String TenKH) {
        String[] columns = {"%" + TenKH + "%"};

        String query = "SELECT * FROM HopDong WHERE TenCus_HopDong LIKE ?";

        Cursor cursor = database.rawQuery(query, columns);

        return cursor;
    }


}


