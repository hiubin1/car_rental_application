package com.example.pjthuexeapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TEN_DATABASE = "QuanLyAppThueXe";


    // Tên bảng User

    public static final String TEN_BANG_USER = "User";

    public static final String COT_ID_USER = "Id_User";

    public static final String COT_TEN_USER = "Ten_User";

    public static final String COT_SDT_USER = "Sdt_User";

    public static final String COT_PASS_USER = "Pass_User";

    public static final String COT_CCCD_USER = "CCCD_User";

    public static final String COT_GPLX_USER = "GPLX_User";

    public static final String COT_ROLE_USER = "Role_User";

    private static final String TAO_BANG_USER = ""
            + "create table " + TEN_BANG_USER + " ( "
            + COT_ID_USER + " integer primary key autoincrement, "
            + COT_TEN_USER + " text not null, "
            + COT_SDT_USER + " text not null, "
            + COT_PASS_USER + " text not null, "
            + COT_CCCD_USER + " text, "
            + COT_ROLE_USER + " text not null, "
            + COT_GPLX_USER + " text not null );";


    // Tên bảng Xe

    public static final String TEN_BANG_XE = "Xe";

    public static final String COT_ID_XE = "ID_Xe";

    public static final String COT_LOAI_XE = "Loai_Xe";

    public static final String COT_HINH_XE = "Hinh_Xe";

    public static final String COT_TEN_XE = "Ten_Xe";

    public static final String COT_BIENSO_XE = "BienSo_Xe";

    public static final String COT_DIADIEMNHAN_XE = "DiaDiemNhan_Xe";

    public static final String COT_SOGHE_XE = "SoGhe_Xe";

    public static final String COT_TRUYENDONG_XE = "TruyenDong_Xe";

    public static final String COT_NHIENLIEU_XE = "NhienLieu_Xe";

    public static final String COT_NLTIEUHAO_XE = "NLTieuHao_Xe";

    public static final String COT_GIATIEN_XE = "GiaTien_Xe";

    public static final String COT_TRANGTHAI_XE = "TrangThai_Xe";


    private static final String TAO_BANG_XE = ""
            + "create table " + TEN_BANG_XE + " ( "
            + COT_ID_XE + " integer primary key autoincrement, "
            + COT_TEN_XE + " text not null, "
            + COT_LOAI_XE + " text not null, "
            + COT_BIENSO_XE + " text not null, "
            + COT_HINH_XE + " BLOB not null, "
            + COT_DIADIEMNHAN_XE + " text not null, "
            + COT_SOGHE_XE + " text not null, "
            + COT_TRUYENDONG_XE + " text not null, "
            + COT_NHIENLIEU_XE + " text not null, "
            + COT_NLTIEUHAO_XE + " text not null, "
            + COT_GIATIEN_XE + " text not null, "
            + COT_TRANGTHAI_XE + " text not null );";


    // Tên bảng hợp đồng

    public static final String TEN_BANG_HOPDONG = "HopDong";

    public static final String COT_ID_HOPDONG = "ID_HopDong";

    public static final String COT_IDCUS_HOPDONG = "IDCus_HopDong";

    public static final String COT_IDXE_HOPDONG = "IDXe_HopDong";

    public static final String COT_IDTHUEXE_HOPDONG = "IDThueXe_HopDong";

    public static final String COT_TENCUS_HOPDONG = "TenCus_HopDong";

    public static final String COT_GIONHANXE_HOPDONG = "GioNhanXe_HopDong";

    public static final String COT_SOTIENTHUE_HOPDONG = "SoTienThue_HopDong";

    public static final String COT_BIENSOXE_HOPDONG = "BienSoXe_HopDong";

    public static final String COT_SOGHE_HOPDONG = "SoGhe_HopDong";

    public static final String COT_TRUYENDONG_HOPDONG = "TruyenDong_HopDong";

    public static final String COT_LOAIXE_HOPDONG = "LoaiXe_HopDong";

    public static final String COT_TENXE_HOPDONG = "TenXe_HopDong";

    public static final String COT_KYTENCUS_HOPDONG = "KyTenCus_HopDong";

    public static final String COT_CCCD_HOPDONG = "CCCD_HopDong";

    public static final String COT_NGAYDATXE_HOPDONG = "NgayDatXe_HopDong";

    public static final String COT_GIOTRAXE_HOPDONG = "GioTraXe_HopDong";

    public static final String COT_NGAYTRAXE_HOPDONG = "NgayTraXe_HopDong";

    public static final String COT_TONGNGAYTHUE_HOPDONG = "TongNgayThue_HopDong";

    public static final String COT_DIADIEMTRAXE_HOPDONG = "DiaDiemTraXe_HopDong";

    public static final String COT_DIADIEMNHANXE_HOPDONG = "DiaDiemNhanXe_HopDong";

    public static final String COT_NGAYDAT_HOPDONG = "NgatDat_HopDong";

    private static final String TAO_BANG_HOPDONG = ""
            + "CREATE TABLE " + TEN_BANG_HOPDONG + " ("
            + COT_ID_HOPDONG + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COT_IDXE_HOPDONG + " INTEGER NOT NULL, "
            + COT_IDCUS_HOPDONG + " INTEGER NOT NULL, "
            + COT_IDTHUEXE_HOPDONG + " INTEGER NOT NULL, "
            + COT_DIADIEMNHANXE_HOPDONG + " TEXT NOT NULL, "
            + COT_BIENSOXE_HOPDONG + " TEXT NOT NULL, "
            + COT_CCCD_HOPDONG + " TEXT NOT NULL, "
            + COT_DIADIEMTRAXE_HOPDONG + " TEXT NOT NULL, "
            + COT_GIONHANXE_HOPDONG + " TEXT NOT NULL, "
            + COT_GIOTRAXE_HOPDONG + " TEXT NOT NULL, "
            + COT_LOAIXE_HOPDONG + " TEXT NOT NULL, "
            + COT_NGAYDAT_HOPDONG + " TEXT NOT NULL, "
            + COT_NGAYDATXE_HOPDONG + " TEXT NOT NULL, "
            + COT_NGAYTRAXE_HOPDONG + " TEXT NOT NULL, "
            + COT_SOGHE_HOPDONG + " TEXT NOT NULL, "
            + COT_SOTIENTHUE_HOPDONG + " TEXT NOT NULL, "
            + COT_TENCUS_HOPDONG + " TEXT NOT NULL, "
            // + COT_KYTENCUS_HOPDONG + " TEXT NOT NULL, "
            + COT_TENXE_HOPDONG + " TEXT NOT NULL, "
            + COT_TONGNGAYTHUE_HOPDONG + " TEXT NOT NULL, "
            + COT_TRUYENDONG_HOPDONG + " TEXT NOT NULL, "
            + "FOREIGN KEY (" + COT_IDCUS_HOPDONG + ") REFERENCES "
            + TEN_BANG_USER + "(" + COT_ID_USER + "), "
            + "FOREIGN KEY (" + COT_IDXE_HOPDONG + ") REFERENCES "
            + TEN_BANG_XE + "(" + COT_ID_XE + "));";

    public static final String TEN_BANG_DONDATXE = "DonDatXe";

    public static final String COT_ID_HOADON = "ID_HoaDon";

    public static final String COT_IDCUS_HOADON = "IDCus_HoaDon";

    public static final String COT_IDTHUEXE_HOADON = "IDThueXe_HoaDon";

    public static final String COT_IDXE_HOADON = "IDXe_HoaDon";


    public static final String COT_NGAYDATDON_HOADON = "NgayDatDon_HoaDon";

    public static final String COT_TENCUS_HOADON = "TenCus_HoaDon";

    public static final String COT_SDTCUS_HOADON = "SDTCus_HoaDon";

    public static final String COT_CMND_HOADON = "CMNDCus_HoaDon";

    public static final String COT_TENXE_HOADON = "TenXe_HoaDon";

    public static final String COT_BIENSOXE_HOADON = "BienSoXe_HoaDon";

    public static final String COT_DIADIEMNHANXE_HOADON = "DiaDiemNhanXe_HoaDon";

    public static final String COT_DIADIEMTRAXE_HOADON = "DiaDiemTraXe_HoaDon";

    public static final String COT_NGAYNHANXE_HOADON = "NgayNhanXe_HoaDon";

    public static final String COT_GIONHANXE_HOADON = "GioNhanXe_HoaDon";

    public static final String COT_NGAYTRAXE_HOADON = "NgayTraXe_HoaDon";

    public static final String COT_GIOTRAXE_HOADON = "GioTraXe_HoaDon";

    public static final String COT_TONGNGAYTHUE_HOADON = "TongNgayThue_HoaDon";

    public static final String COT_GIATIENTHUEXE_HOADON = "GiaTienThueXe_HoaDon";

    public static final String COT_TONGTIEN_HOADON = "TongTien_HoaDon";

    private static final String TAO_BANG_DONDATXE = ""
            + "CREATE TABLE " + TEN_BANG_DONDATXE + " ("
            + COT_ID_HOADON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COT_IDCUS_HOADON + " INTEGER NOT NULL, "
            + COT_IDTHUEXE_HOADON + " INTEGER NOT NULL, "
            + COT_IDXE_HOADON + " INTEGER NOT NULL, "
            + COT_CMND_HOADON + " TEXT NOT NULL, "
            + COT_BIENSOXE_HOADON + " TEXT NOT NULL, "
            + COT_DIADIEMNHANXE_HOADON + " TEXT NOT NULL, "
            + COT_DIADIEMTRAXE_HOADON + " TEXT NOT NULL, "
            + COT_GIONHANXE_HOADON + " TEXT NOT NULL, "
            + COT_GIOTRAXE_HOADON + " TEXT NOT NULL, "
            + COT_NGAYDATDON_HOADON + " TEXT NOT NULL, "
            + COT_NGAYNHANXE_HOADON + " TEXT NOT NULL, "
            + COT_NGAYTRAXE_HOADON + " TEXT NOT NULL, "
            + COT_SDTCUS_HOADON + " TEXT NOT NULL, "
            + COT_TENCUS_HOADON + " TEXT NOT NULL, "
            + COT_TENXE_HOADON + " TEXT NOT NULL, "
            + COT_GIATIENTHUEXE_HOADON + " TEXT NOT NULL, "
            + COT_TONGNGAYTHUE_HOADON + " TEXT NOT NULL, "
            + COT_TONGTIEN_HOADON + " TEXT NOT NULL, "
            + "FOREIGN KEY (" + COT_IDCUS_HOADON + ") REFERENCES "
            + TEN_BANG_USER + "(" + COT_ID_USER + "), "
            + "FOREIGN KEY (" + COT_IDXE_HOADON + ") REFERENCES "
            + TEN_BANG_XE + "(" + COT_ID_XE + "));";


    // Thông báo
    public static final String TEN_BANG_THONGBAO = "ThongBao";

    public static final String COT_ID_THONGBAO = "ID_ThongBao";

    public static final String COT_IDCUS_THONGBAO = "IDCus_ThongBao";

    public static final String COT_TIEUDE_THONGBAO = "TieuDe_ThongBao";

    public static final String COT_NOIDUNG_THONGBAO = "NoiDung_ThongBao";

    public static final String TAO_BANG_THONGBAO = ""
            + "create table " + TEN_BANG_THONGBAO + " ( "
            + COT_ID_THONGBAO + " integer primary key autoincrement, "
            + COT_IDCUS_THONGBAO + " integer not null, "
            + COT_TIEUDE_THONGBAO + " text not null, "
            + COT_NOIDUNG_THONGBAO + " text not null, "
            + "FOREIGN KEY (" + COT_IDCUS_THONGBAO + ") REFERENCES "
            + TEN_BANG_USER + "(" + COT_ID_USER + "));";


    // tên bảng đơn gia hạn
    public static final String TEN_BANG_DONGIAHAN = "DonGiaHan";

    public static final String COT_ID_DONGIAHAN = "ID_DonGiaHan";

    public static final String COT_IDHOADON_DONGIAHAN = "IDHoaDon_DonGiaHan";

    public static final String COT_NGAYBATDAUGIAHAN_DONGIAHAN = "NgayBatDauGiaHan_DonGiaHan";

    public static final String COT_TONGNGAYGIAHAN_DONGIAHAN = "TongNgayGiaHan_DonGiaHan";

    public static final String COT_TONGTIEN_DONGIAHAN = "TongTien_DonGiaHan";

    public static final String TAO_BANG_DONGIAHAN = ""
            + "create table " + TEN_BANG_DONGIAHAN + " ( "
            + COT_ID_DONGIAHAN + " integer primary key autoincrement, "
            + COT_IDHOADON_DONGIAHAN + " integer not null, "
            + COT_NGAYBATDAUGIAHAN_DONGIAHAN + " text not null, "
            + COT_TONGNGAYGIAHAN_DONGIAHAN + " text not null, "
            + COT_TONGTIEN_DONGIAHAN + " text not null, "
            + "FOREIGN KEY (" + COT_IDHOADON_DONGIAHAN + ") REFERENCES "
            + TEN_BANG_DONDATXE + "(" + COT_ID_HOADON + "));";

    public DBHelper(@Nullable Context context) {
        super(context, TEN_DATABASE, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAO_BANG_USER);
        db.execSQL(TAO_BANG_XE);
        db.execSQL(TAO_BANG_HOPDONG);
        db.execSQL(TAO_BANG_DONDATXE);
        db.execSQL(TAO_BANG_THONGBAO);
        db.execSQL(TAO_BANG_DONGIAHAN);

        ContentValues values = new ContentValues();
        values.put(COT_TEN_USER, "Admin");
        values.put(COT_SDT_USER, "123456");
        values.put(COT_PASS_USER, "123");
        values.put(COT_GPLX_USER, 1);
        values.put(COT_CCCD_USER, "12345656");
        values.put(COT_ROLE_USER, 1);
        db.insert(TEN_BANG_USER,null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion1) {

    }
}
