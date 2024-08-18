package com.example.pjthuexeapp.FragmentCus;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pjthuexeapp.Adapter.DanhSachXeAdapter;
import com.example.pjthuexeapp.Adapter.QuanLyXeAdapter;
import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.XeSession;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class FragmentCar extends Fragment {

   View v;

   RecyclerView mRCview_DanhSachXe;

   RecyclerView.LayoutManager mlayoutManager;

   ArrayList<Xe> xeArrayList;

   DanhSachXeAdapter danhSachXeAdapter;

  EditText medtTimXe_DanhSachXe;



   Database_Xe databaseXe;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_car, container, false);
        medtTimXe_DanhSachXe = v.findViewById(R.id.edtTimXe_DanhSachXe);
        databaseXe = new Database_Xe(getContext());
        capNhatDuLieu();
        medtTimXe_DanhSachXe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String searchText = s.toString();

                if(searchText.isEmpty()) {
                    capNhatDuLieu();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();

                if(searchText.isEmpty()) {
                    capNhatDuLieu();
                } else {
                    Search(searchText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        capNhatDuLieu();
    }

    private void setRV() {
        if (xeArrayList != null){
            mRCview_DanhSachXe = v.findViewById(R.id.RCview_DanhSachXe);
            mlayoutManager = new LinearLayoutManager(getContext());
            mRCview_DanhSachXe.setLayoutManager(mlayoutManager);
            danhSachXeAdapter = new DanhSachXeAdapter(getContext(),R.layout.list_quanlyxe, xeArrayList);
            mRCview_DanhSachXe.setAdapter(danhSachXeAdapter);
            danhSachXeAdapter.notifyDataSetChanged();
            mRCview_DanhSachXe.invalidate();
        }
    }



    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if(xeArrayList == null){
            xeArrayList = new ArrayList<Xe>();
        } else {
            xeArrayList.removeAll(xeArrayList);
        }

        Cursor cursor = databaseXe.LayTatCaDuLieu();
        if(cursor != null){
            while (cursor.moveToNext()){
                Xe xe = new Xe();


                xe.setId(Integer.parseInt
                        (cursor.getString(cursor.getColumnIndex(DBHelper.COT_ID_XE))));



                xe.setTenXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN_XE)));

                xe.setHinhXe(cursor.getBlob(cursor.getColumnIndex(DBHelper.COT_HINH_XE)));

                xe.setNhienLieu(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NHIENLIEU_XE)));

                xe.setDiaDiemNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMNHAN_XE)));

                xe.setBienSoXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BIENSO_XE)));

                xe.setNangLuongTieuHao(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NLTIEUHAO_XE)));

                xe.setSoGhe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SOGHE_XE)));

                xe.setTruyenDong(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TRUYENDONG_XE)));

                xe.setGiaTien(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_GIATIEN_XE)));

                xe.setTrangThai(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TRANGTHAI_XE)));

                xe.setLoaiXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_LOAI_XE)));

                xeArrayList.add(xe);
            }
        }
        setRV();
    }
    @SuppressLint("Range")
    public void Search(String TenXe) {
        if(xeArrayList == null) {
            xeArrayList = new ArrayList<Xe>();
        } else {
            xeArrayList.removeAll(xeArrayList);
        }

        String newText = TenXe;

        Cursor cursor = databaseXe.search(newText);

        if(cursor != null) {
            while (cursor.moveToNext()) {
                Xe xe = new Xe();

                xe.setId(Integer.parseInt
                        (cursor.getString(cursor.getColumnIndex(DBHelper.COT_ID_XE))));



                xe.setTenXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN_XE)));

                xe.setHinhXe(cursor.getBlob(cursor.getColumnIndex(DBHelper.COT_HINH_XE)));

                xe.setNhienLieu(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NHIENLIEU_XE)));

                xe.setDiaDiemNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMNHAN_XE)));

                xe.setBienSoXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BIENSO_XE)));

                xe.setNangLuongTieuHao(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NLTIEUHAO_XE)));

                xe.setSoGhe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SOGHE_XE)));

                xe.setTruyenDong(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TRUYENDONG_XE)));

                xe.setGiaTien(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_GIATIEN_XE)));

                xe.setTrangThai(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TRANGTHAI_XE)));

                xe.setLoaiXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_LOAI_XE)));

                xeArrayList.add(xe);
            }
        } else {
            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
        }
        setRV();
    }
}