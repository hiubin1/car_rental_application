package com.example.pjthuexeapp.FragmentCus;

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

import com.example.pjthuexeapp.Adapter.DanhSachXeAdapter;
import com.example.pjthuexeapp.Adapter.DiaDiemNoiBatAdapter;
import com.example.pjthuexeapp.Class.DiaDiemNoiBat;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;
import com.example.pjthuexeapp.Session.XeSession;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FragmentHome extends Fragment {

    View v;

    RecyclerView mRCview_QuanLyXe, mRCview_DiaDiemNoiBat;
    RecyclerView.LayoutManager mlayoutManager, mlayoutManagerDDNB;
    ArrayList<Xe> xeArrayList;

    ArrayList<DiaDiemNoiBat> diaDiemNoiBatArrayList;
    DanhSachXeAdapter danhSachXeAdapter;

    DiaDiemNoiBatAdapter diaDiemNoiBatAdapter;

    TextView mtv_TenUser_TrangChu;

    Database_User database_user;

    Database_Xe databaseXe;

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_home, container, false);
        databaseXe = new Database_Xe(getContext());
        setRVDiaDiem();
        mtv_TenUser_TrangChu = v.findViewById(R.id.tv_TenUser_TrangChu);
        database_user = new Database_User(getContext());
        String sdt = UserSession.userPhoneCurrent;
        String UserName = "";
        Cursor cursor = database_user.GetNameFromSdt(sdt);
        if (cursor.moveToFirst()){
            UserName = cursor.getString(cursor.getColumnIndex("Ten_User"));
        }
        mtv_TenUser_TrangChu.setText(UserName);
        capNhatDuLieu();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        capNhatDuLieu();
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
        setRVXe();
    }

    private void setRVXe() {
        mRCview_QuanLyXe = v.findViewById(R.id.RCview_QuanLyXe);
        mlayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mRCview_QuanLyXe.setLayoutManager(mlayoutManager);
        danhSachXeAdapter = new DanhSachXeAdapter(getContext(),R.layout.list_quanlyxe, xeArrayList);
        mRCview_QuanLyXe.setAdapter(danhSachXeAdapter);
    }

    private void setRVDiaDiem() {
        mRCview_DiaDiemNoiBat = v.findViewById(R.id.RCview_DiaDiemNoiBat);
        mlayoutManagerDDNB = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mRCview_DiaDiemNoiBat.setLayoutManager(mlayoutManagerDDNB);
        diaDiemNoiBatArrayList = DiaDiemNoiBat.init();
        diaDiemNoiBatAdapter = new DiaDiemNoiBatAdapter(getContext(),R.layout.list_diadiemnoibat, diaDiemNoiBatArrayList);
        mRCview_DiaDiemNoiBat.setAdapter(diaDiemNoiBatAdapter);
    }
}