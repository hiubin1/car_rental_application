package com.example.pjthuexeapp.FragmentAdmin;

import android.annotation.SuppressLint;
import android.database.Cursor;
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

import com.example.pjthuexeapp.Adapter.QuanLyHopDongAdapter;
import com.example.pjthuexeapp.Class.HopDong;
import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_HopDong;
import com.example.pjthuexeapp.R;

import java.util.ArrayList;


public class QuanLyHopDongFragment extends Fragment {



    View v;

    RecyclerView mRCview_QuanLyHopDong;

    RecyclerView.LayoutManager layoutManager;

    ArrayList<HopDong> hopDongArrayList;

    QuanLyHopDongAdapter quanLyHopDongAdapter;

    EditText medtTimHopDong_QuanLyHopDong;

    Database_HopDong databaseHopDong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_quan_ly_hop_dong, container, false);
        medtTimHopDong_QuanLyHopDong = v.findViewById(R.id.edtTimHopDong_QuanLyHopDong);
        databaseHopDong = new Database_HopDong(getContext());
        capNhatDuLieu();
        medtTimHopDong_QuanLyHopDong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String searchText = s.toString();

                if (searchText.isEmpty()){
                    capNhatDuLieu();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();
                if (searchText.isEmpty()){
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

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (hopDongArrayList == null){
            hopDongArrayList = new ArrayList<>();
        } else  {
            hopDongArrayList.removeAll(hopDongArrayList);
        }
        Cursor cursor = databaseHopDong.layTatCaDuLieu();
        if (cursor != null){
            while (cursor.moveToNext()){
                HopDong hopDong = new HopDong();
                hopDong.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_HOPDONG)));
                hopDong.setTenCus(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TENCUS_HOPDONG)));
                hopDong.setCCCD(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CCCD_HOPDONG)));
                hopDong.setNgayDat(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYDAT_HOPDONG)));
                hopDong.setDiaDiemNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMNHANXE_HOPDONG)));
                hopDong.setTenXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TENXE_HOPDONG)));
                hopDong.setLoaiXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_LOAIXE_HOPDONG)));
                hopDong.setTruyenDong(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TRUYENDONG_HOPDONG)));
                hopDong.setSoGhe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SOGHE_HOPDONG)));
                hopDong.setTongNgayThue(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_TONGNGAYTHUE_HOPDONG)));
                hopDong.setSoTienThue(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_SOTIENTHUE_HOPDONG)));
                hopDong.setNgayDatXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYDATXE_HOPDONG)));
                hopDong.setGioNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GIONHANXE_HOPDONG)));
                hopDong.setNgayTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYTRAXE_HOPDONG)));
                hopDong.setGioTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GIOTRAXE_HOPDONG)));
                hopDong.setDiaDiemTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMTRAXE_HOPDONG)));
                hopDong.setBienSoXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BIENSOXE_HOPDONG)));
                hopDong.setDiaDiemNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMNHANXE_HOPDONG)));
                hopDongArrayList.add(hopDong);
            }
        }
        SetRV();
    }

    @SuppressLint("Range")
    public void Search(String nameUser){
        if (hopDongArrayList == null){
            hopDongArrayList = new ArrayList<HopDong>();
        } else {
            hopDongArrayList.removeAll(hopDongArrayList);
        }

        String newText = nameUser;
        Cursor cursor = databaseHopDong.search(newText);

        if (cursor!= null){
            while (cursor.moveToNext()){
                HopDong hopDong = new HopDong();
                hopDong.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_HOPDONG)));
                hopDong.setTenCus(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TENCUS_HOPDONG)));
                hopDong.setCCCD(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CCCD_HOPDONG)));
                hopDong.setNgayDat(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYDAT_HOPDONG)));
                hopDong.setDiaDiemNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMNHANXE_HOPDONG)));
                hopDong.setTenXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TENXE_HOPDONG)));
                hopDong.setLoaiXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_LOAIXE_HOPDONG)));
                hopDong.setTruyenDong(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TRUYENDONG_HOPDONG)));
                hopDong.setSoGhe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SOGHE_HOPDONG)));
                hopDong.setTongNgayThue(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_TONGNGAYTHUE_HOPDONG)));
                hopDong.setSoTienThue(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_SOTIENTHUE_HOPDONG)));
                hopDong.setNgayDatXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYDATXE_HOPDONG)));
                hopDong.setGioNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GIONHANXE_HOPDONG)));
                hopDong.setNgayTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYTRAXE_HOPDONG)));
                hopDong.setGioTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GIOTRAXE_HOPDONG)));
                hopDong.setDiaDiemTraXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMTRAXE_HOPDONG)));
                hopDong.setBienSoXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BIENSOXE_HOPDONG)));
                hopDong.setDiaDiemNhanXe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIADIEMNHANXE_HOPDONG)));
                hopDongArrayList.add(hopDong);
            }
        } else {
            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
        }
        SetRV();
    }

    private void SetRV(){
        if (hopDongArrayList != null){
            mRCview_QuanLyHopDong = v.findViewById(R.id.RCview_QuanLyHopDong);
            layoutManager = new LinearLayoutManager(getContext());
            mRCview_QuanLyHopDong.setLayoutManager(layoutManager);
            quanLyHopDongAdapter = new QuanLyHopDongAdapter(getContext(),R.layout.list_hopdong, hopDongArrayList);
            mRCview_QuanLyHopDong.setAdapter(quanLyHopDongAdapter);
            quanLyHopDongAdapter.notifyDataSetChanged();
            mRCview_QuanLyHopDong.invalidate();
        }
    }
}