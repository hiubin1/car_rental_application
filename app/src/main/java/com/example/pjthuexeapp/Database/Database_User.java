package com.example.pjthuexeapp.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pjthuexeapp.Class.NguoiDung;

public class Database_User {

    SQLiteDatabase database;

    DBHelper helper;

    public Database_User (Context context){
        helper = new DBHelper(context);

        database = helper.getWritableDatabase();
    }

    public long ThemUser(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_TEN_USER, nguoiDung.getHoTen());
        values.put(DBHelper.COT_SDT_USER, nguoiDung.getSDT());
        values.put(DBHelper.COT_CCCD_USER, nguoiDung.getCCCD());
        values.put(DBHelper.COT_PASS_USER, nguoiDung.getPassword());
        values.put(DBHelper.COT_GPLX_USER, nguoiDung.getGPLX());
        values.put(DBHelper.COT_ROLE_USER, nguoiDung.getRole());

        return database.insert(DBHelper.TEN_BANG_USER,null,values);
    }

    public long SuaUser(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_TEN_USER, nguoiDung.getHoTen());
        values.put(DBHelper.COT_SDT_USER, nguoiDung.getSDT());
        values.put(DBHelper.COT_CCCD_USER, nguoiDung.getCCCD());
        values.put(DBHelper.COT_GPLX_USER, nguoiDung.getGPLX());

        return database.update(DBHelper.TEN_BANG_USER, values,
                DBHelper.COT_ID_USER + " = " + nguoiDung.getId(),null);
    }

    public Boolean KiemTraDangNhap(String sdt, String pass){
        boolean KiemtraDN = false;
        String[] cot = {sdt,pass};
        String query = "Select * from User where Sdt_User =? And Pass_User =? ";

        Cursor cursor = database.rawQuery(query,cot);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            KiemtraDN = true;
        } else {
            KiemtraDN = false;
        }
        cursor.close();
        return KiemtraDN;
    }


    public String KiemTraRole(String sdt) {
        String userRole = "";
        String[] cot = new String[]{sdt};

        String query = "Select Role_User from User where Sdt_User =? ";

        Cursor cursor = database.rawQuery(query, cot);

        if(cursor.moveToNext()) {
            userRole = cursor.getString(0);
        }

        cursor.close();
        return userRole;
    }

    @SuppressLint("Range")
    public String KiemTraGPLX(String sdt) {
        String userGPLX = "";

        String[] cot = new String[]{sdt};

        String query = "SELECT GPLX_User FROM User WHERE Sdt_User= ?";

        Cursor cursor = database.rawQuery(query, cot);

        if (cursor.moveToFirst()) {
            userGPLX = cursor.getString(0);
        }

        cursor.close();
        return userGPLX;
    }

    public boolean KiemTraSDT(String sdt) {
        String[] cot = new String[]{sdt};
        boolean TonTai = false;

        String query = "Select Sdt_User from User where Sdt_User =?";

        Cursor cursor = database.rawQuery(query, cot);

        if(cursor.moveToFirst()) {
            TonTai = true;
        }

        cursor.close();
        return TonTai;
    }

    public Cursor layTatCaDuLieu(){
        String[] cot = {DBHelper.COT_ID_USER,
                DBHelper.COT_TEN_USER,
                DBHelper.COT_SDT_USER,
                DBHelper.COT_PASS_USER,
                DBHelper.COT_GPLX_USER,
                DBHelper.COT_CCCD_USER,
                DBHelper.COT_ROLE_USER};


        Cursor cursor = null;

        cursor = database.query(DBHelper.TEN_BANG_USER, cot, null,null,null,null,DBHelper.COT_ID_USER + " DESC");

        return cursor;
    }

    public Cursor GetNameFromSdt(String sdt){
        String[] COT = {sdt};
        String query = "select Ten_User from User where Sdt_User = ?";
        Cursor cursor = database.rawQuery(query,COT);
        return cursor;
    }

    public Cursor GetUserFromSdt(String sdt){
        String[] COT = {sdt};
        String query = "select * from User where Sdt_User = ?";
        Cursor cursor = database.rawQuery(query,COT);
        return cursor;
    }

    public Cursor Search(String nameUser) {
        String[] cot = {"%" + nameUser + "%"};

        String query = "select * from User where Ten_User like ?";

        Cursor cursor = null;

        cursor = database.rawQuery(query, cot);

        return cursor;
    }
}
