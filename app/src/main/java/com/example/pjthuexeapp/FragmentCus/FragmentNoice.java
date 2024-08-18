package com.example.pjthuexeapp.FragmentCus;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pjthuexeapp.Adapter.QuanLyHopDongAdapter;
import com.example.pjthuexeapp.Adapter.QuanLyThongBaoAdapter;
import com.example.pjthuexeapp.Class.HopDong;
import com.example.pjthuexeapp.Class.ThongBao;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_ThongBao;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;

import java.util.ArrayList;

public class FragmentNoice extends Fragment {

    View v;

    RecyclerView mRCview_QuanLyThongBao;

    RecyclerView.LayoutManager layoutManager;

    ArrayList<ThongBao> thongBaoArrayList;

    QuanLyThongBaoAdapter quanLyThongBaoAdapter;

    Database_ThongBao databaseThongBao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_noice, container, false);
        databaseThongBao = new Database_ThongBao(getContext());
        capNhatDuLieu();
        return v;
    }

    private BroadcastReceiver capNhatDanhSachThongBaoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            capNhatDuLieu();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("cap_nhat_danh_sach_thong_bao");
        getActivity().registerReceiver(capNhatDanhSachThongBaoReceiver, intentFilter);
        capNhatDuLieu();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(capNhatDanhSachThongBaoReceiver);
    }


    @SuppressLint("Range")
    public void capNhatDuLieu() {
        if (thongBaoArrayList == null) {
            thongBaoArrayList = new ArrayList<>();
        } else {
            thongBaoArrayList.clear();
        }
        Cursor cursor = databaseThongBao.GetThongBaoFromId(UserSession.userId);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                ThongBao thongBao = new ThongBao();
                thongBao.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_THONGBAO)));
                thongBao.setIdCus(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_IDCUS_THONGBAO)));
                thongBao.setTieuDe(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TIEUDE_THONGBAO)));
                thongBao.setNoiDung(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NOIDUNG_THONGBAO)));
                thongBaoArrayList.add(thongBao);
            } while (cursor.moveToNext());
        }
        SetRV();
    }

    private void SetRV(){
        if (thongBaoArrayList != null){
            mRCview_QuanLyThongBao = v.findViewById(R.id.RCview_QuanLyThongBao);
            layoutManager = new LinearLayoutManager(getContext());
            mRCview_QuanLyThongBao.setLayoutManager(layoutManager);
            quanLyThongBaoAdapter = new QuanLyThongBaoAdapter(getContext(),R.layout.list_thongbao, thongBaoArrayList);
            mRCview_QuanLyThongBao.setAdapter(quanLyThongBaoAdapter);
            quanLyThongBaoAdapter.notifyDataSetChanged();
            mRCview_QuanLyThongBao.invalidate();
        }
    }
}