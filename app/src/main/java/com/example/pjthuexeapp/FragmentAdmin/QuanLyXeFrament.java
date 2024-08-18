package com.example.pjthuexeapp.FragmentAdmin;

import static com.example.pjthuexeapp.Session.XeSession.HinhXe;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pjthuexeapp.Adapter.QuanLyXeAdapter;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.XeSession;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class QuanLyXeFrament extends Fragment {
    View v;

    RecyclerView mRCview_QuanLyXe;
    RecyclerView.LayoutManager mlayoutManager;
    ArrayList<Xe> xeArrayList;
    QuanLyXeAdapter quanLyXeAdapter;

    EditText medtTimXe_QuanLyXe;

    Database_Xe databaseXe;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_quan_ly_xe_frament, container, false);
        medtTimXe_QuanLyXe = (EditText) v.findViewById(R.id.edtTimXe_QuanLyXe);

        databaseXe = new Database_Xe(getContext());
        capNhatDuLieu();
        medtTimXe_QuanLyXe.addTextChangedListener(new TextWatcher() {
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
            mRCview_QuanLyXe = v.findViewById(R.id.RCview_QuanLyXe);
            mlayoutManager = new LinearLayoutManager(getContext());
            mRCview_QuanLyXe.setLayoutManager(mlayoutManager);
            quanLyXeAdapter = new QuanLyXeAdapter(getContext(),R.layout.list_quanlyxe, xeArrayList);
            mRCview_QuanLyXe.setAdapter(quanLyXeAdapter);
            quanLyXeAdapter.notifyDataSetChanged();
            mRCview_QuanLyXe.invalidate();
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