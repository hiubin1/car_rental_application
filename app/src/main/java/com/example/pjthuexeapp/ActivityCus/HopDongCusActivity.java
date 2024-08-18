package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pjthuexeapp.Adapter.DanhSachHopDongAdapter;
import com.example.pjthuexeapp.Adapter.QuanLyHopDongAdapter;
import com.example.pjthuexeapp.Class.HopDong;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_HopDong;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;

import java.util.ArrayList;

public class HopDongCusActivity extends AppCompatActivity {


    ArrayList<HopDong> hopDongArrayList;

    DanhSachHopDongAdapter danhSachHopDongAdapter;

    RecyclerView.LayoutManager layoutManager;

    RecyclerView mRCview_DanhSachHopDong;

    Database_HopDong databaseHopDong;

    Toolbar toolbar;

    TextView tvTitleToolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong_cus);
        databaseHopDong = new Database_HopDong(this);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);
        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Danh sách hợp đồng");
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
        if (hopDongArrayList == null){
            hopDongArrayList = new ArrayList<>();
        } else  {
            hopDongArrayList.removeAll(hopDongArrayList);
        }
        Cursor cursor = databaseHopDong.GetHopDongFromId(UserSession.userId);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()){
            do {
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
            } while (cursor.moveToNext());
        }
        SetRV();
    }
    private void SetRV(){
        if (hopDongArrayList != null){
            mRCview_DanhSachHopDong = findViewById(R.id.RCview_DanhSachHopDong);
            layoutManager = new LinearLayoutManager(this);
            mRCview_DanhSachHopDong.setLayoutManager(layoutManager);
            danhSachHopDongAdapter = new DanhSachHopDongAdapter(HopDongCusActivity.this,R.layout.list_hopdong, hopDongArrayList);
            mRCview_DanhSachHopDong.setAdapter(danhSachHopDongAdapter);
            danhSachHopDongAdapter.notifyDataSetChanged();
            mRCview_DanhSachHopDong.invalidate();
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