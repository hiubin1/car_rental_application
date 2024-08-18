package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pjthuexeapp.Adapter.LichSuDatXeAdapter;
import com.example.pjthuexeapp.Class.HoaDon;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_DonDatXe;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;

import java.util.ArrayList;

public class LichSuDatXeActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView tvTitleToolbar;

    RecyclerView mRCview_LichSuXeDaDat;

    RecyclerView.LayoutManager layoutManager;

    ArrayList<HoaDon> hoaDonArrayList;

    LichSuDatXeAdapter lichSuXeDaDatAdapter;

    Database_DonDatXe databaseDonDatXe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_dat_xe);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);
        databaseDonDatXe = new Database_DonDatXe(this);

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Lịch sử đặt xe");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        capNhatDuLieu();
    }
    @Override
    public void onResume() {
        super.onResume();
        capNhatDuLieu();
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (hoaDonArrayList == null){
            hoaDonArrayList = new ArrayList<>();
        } else  {
            hoaDonArrayList.removeAll(hoaDonArrayList);
        }
        Cursor cursor = databaseDonDatXe.GetHoaDonFromId(UserSession.userId);
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
        setRV();
        }

    private void setRV() {
        if (hoaDonArrayList != null){
            mRCview_LichSuXeDaDat = findViewById(R.id.RCview_LichSuDatXe);
            layoutManager = new LinearLayoutManager(this);
            mRCview_LichSuXeDaDat.setLayoutManager(layoutManager);
            lichSuXeDaDatAdapter = new LichSuDatXeAdapter(LichSuDatXeActivity.this,R.layout.list_hoadondatxe, hoaDonArrayList);
            mRCview_LichSuXeDaDat.setAdapter(lichSuXeDaDatAdapter);
            lichSuXeDaDatAdapter.notifyDataSetChanged();
            mRCview_LichSuXeDaDat.invalidate();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}