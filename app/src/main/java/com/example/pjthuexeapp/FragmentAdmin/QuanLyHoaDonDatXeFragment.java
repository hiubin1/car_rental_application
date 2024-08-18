package com.example.pjthuexeapp.FragmentAdmin;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pjthuexeapp.Adapter.LichSuDatXeAdapter;
import com.example.pjthuexeapp.Adapter.QuanLyHoaDonDatXeAdapter;
import com.example.pjthuexeapp.Adapter.QuanLyHopDongAdapter;
import com.example.pjthuexeapp.Class.HoaDon;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_DonDatXe;
import com.example.pjthuexeapp.Database.Database_DonGiaHan;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class QuanLyHoaDonDatXeFragment extends Fragment {
    RecyclerView mRCview_QuanLyHoaDonDatXe;

    RecyclerView.LayoutManager layoutManager;

    ArrayList<HoaDon> hoaDonArrayList;

    QuanLyHoaDonDatXeAdapter quanLyHoaDonDatXeAdapter;

    Database_DonDatXe databaseDonDatXe;

    Database_DonGiaHan databaseDonGiaHan;

    TextView mtvTongDoanhThu_QuanLyHoaDonDatXe;

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_quan_ly_hoa_don_dat_xe, container, false);
        databaseDonDatXe = new Database_DonDatXe(getContext());
        databaseDonGiaHan = new Database_DonGiaHan(getContext());
        mtvTongDoanhThu_QuanLyHoaDonDatXe = v.findViewById(R.id.tvTongDoanhThu_QuanLyHoaDonDatXe);
        capNhatDuLieu();
        SumTotal();
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        capNhatDuLieu();
    }
    private void SetRV(){
        if (hoaDonArrayList != null){
            mRCview_QuanLyHoaDonDatXe = v.findViewById(R.id.RCview_QuanLyHoaDonDatXe);
            layoutManager = new LinearLayoutManager(getContext());
            mRCview_QuanLyHoaDonDatXe.setLayoutManager(layoutManager);
            quanLyHoaDonDatXeAdapter = new QuanLyHoaDonDatXeAdapter(getContext(),R.layout.list_hoadondatxe, hoaDonArrayList);
            mRCview_QuanLyHoaDonDatXe.setAdapter(quanLyHoaDonDatXeAdapter);
            quanLyHoaDonDatXeAdapter.notifyDataSetChanged();
            mRCview_QuanLyHoaDonDatXe.invalidate();
        }
    }

    @SuppressLint("Range")
    public void SumTotal(){
        Cursor cursor = databaseDonDatXe.SumTotal();
        Cursor cursor1 = databaseDonGiaHan.SumTotal();
        if (cursor != null && cursor.moveToFirst() && cursor1 != null && cursor1.moveToFirst()){
            DecimalFormat formatter = new DecimalFormat("###,###.##");
            double total = cursor.getDouble(0);
            double total1 = cursor1.getDouble(0);
            mtvTongDoanhThu_QuanLyHoaDonDatXe.setText(formatter.format(total + total1));

            cursor.close();
        }
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (hoaDonArrayList == null){
            hoaDonArrayList = new ArrayList<>();
        } else  {
            hoaDonArrayList.removeAll(hoaDonArrayList);
        }
        Cursor cursor = databaseDonDatXe.layTatCaDuLieu();
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()){
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_HOADON)));
                hoaDon.setTenCus(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TENCUS_HOADON)));
                hoaDon.setSDTCus(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SDTCUS_HOADON)));
                hoaDon.setCMND(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CMND_HOADON)));
                hoaDon.setTenXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TENXE_HOADON)));
                hoaDon.setBienSoXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BIENSOXE_HOADON)));
                hoaDon.setDiaDiemNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMNHANXE_HOADON)));
                hoaDon.setDiaDiemTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMTRAXE_HOADON)));
                hoaDon.setNgayNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYNHANXE_HOADON)));
                hoaDon.setGioNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GIONHANXE_HOADON)));
                hoaDon.setNgayTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYTRAXE_HOADON)));
                hoaDon.setGioTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GIOTRAXE_HOADON)));
                hoaDon.setTongNgayThue(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_TONGNGAYTHUE_HOADON)));
                hoaDon.setGiaTienThueXe(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_GIATIENTHUEXE_HOADON)));
                hoaDon.setTongTien(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_TONGTIEN_HOADON)));
                hoaDon.setNgatDatDon(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYDATDON_HOADON)));
                hoaDonArrayList.add(hoaDon);

            } while (cursor.moveToNext());
        }
        SetRV();
    }
}