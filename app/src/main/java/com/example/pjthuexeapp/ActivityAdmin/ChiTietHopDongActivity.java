package com.example.pjthuexeapp.ActivityAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pjthuexeapp.Class.HopDong;
import com.example.pjthuexeapp.Database.Database_HopDong;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.HopDongSession;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ChiTietHopDongActivity extends AppCompatActivity {
    Toolbar toolbar;

    TextView tvTitleToolbar;

    TextView mtvMaHopDong_ChiTietHopDong, mtv_NgayHienTai1_ChiTietHopDong, mtv_TenUser_ChiTietHopDong,
    mtv_CCCD_ChiTietHopDong, mtv_TenXe_ChiTietHopDong, mtv_LoaiXe_ChiTietHopDong, mtv_TruyenDong_ChiTietHopDong,
    mtv_SoGhe_ChiTietHopDong, mtv_BienSoXe_ChiTietHopDong, mtv_DiaDiemGiaoXe_ChiTietHopDong,
    mtv_DiaDiemTraXe_ChiTietHopDong, mtv_NgayNhanXe_ChiTietHopDong, mtv_GioNhanXe_ChiTietHopDong,
    mtv_NgayTraXe_ChiTietHopDong, mtv_GioTraXe_ChiTietHopDong, mtv_TongSoNgayThue_ChiTietHopDong,
    mtv_SoTienThue_ChiTietHopDong, mtv_NgayHienTai2_ChiTietHopDong, mtv_NgayHienTai3_ChiTietHopDong,
    mtv_KyTenUser_ChiTietHopDong;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hop_dong);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Chi tiết hợp đồng");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mtvMaHopDong_ChiTietHopDong = findViewById(R.id.tvMaHopDong_ChiTietHopDong);
        mtv_DiaDiemGiaoXe_ChiTietHopDong = findViewById(R.id.tv_DiaDiemGiaoXe_ChiTietHopDong);
        mtv_DiaDiemTraXe_ChiTietHopDong = findViewById(R.id.tv_DiaDiemTraXe_ChiTietHopDong);
        mtv_GioTraXe_ChiTietHopDong = findViewById(R.id.tv_GioTraXe_ChiTietHopDong);
        mtv_NgayTraXe_ChiTietHopDong = findViewById(R.id.tv_NgayTraXe_ChiTietHopDong);
        mtv_GioNhanXe_ChiTietHopDong = findViewById(R.id.tv_GioNhanXe_ChiTietHopDong);
        mtv_NgayNhanXe_ChiTietHopDong = findViewById(R.id.tv_NgayNhanXe_ChiTietHopDong);
        mtv_KyTenUser_ChiTietHopDong = findViewById(R.id.tv_KyTenUser_ChiTietHopDong);
        mtv_NgayHienTai3_ChiTietHopDong = findViewById(R.id.tv_NgayHienTai3_ChiTietHopDong);
        mtv_NgayHienTai2_ChiTietHopDong = findViewById(R.id.tv_NgayHienTai2_ChiTietHopDong);
        mtv_SoTienThue_ChiTietHopDong = findViewById(R.id.tv_SoTienThue_ChiTietHopDong);
        mtv_TongSoNgayThue_ChiTietHopDong = findViewById(R.id.tv_TongSoNgayThue_ChiTietHopDong);
        mtv_BienSoXe_ChiTietHopDong = findViewById(R.id.tv_BienSoXe_ChiTietHopDong);
        mtv_SoGhe_ChiTietHopDong = findViewById(R.id.tv_SoGhe_ChiTietHopDong);
        mtv_TruyenDong_ChiTietHopDong = findViewById(R.id.tv_TruyenDong_ChiTietHopDong);
        mtv_LoaiXe_ChiTietHopDong = findViewById(R.id.tv_LoaiXe_ChiTietHopDong);
        mtv_TenXe_ChiTietHopDong = findViewById(R.id.tv_TenXe_ChiTietHopDong);
        mtv_CCCD_ChiTietHopDong = findViewById(R.id.tv_CCCD_ChiTietHopDong);
        mtv_TenUser_ChiTietHopDong = findViewById(R.id.tv_TenUser_ChiTietHopDong);
        mtv_NgayHienTai1_ChiTietHopDong = findViewById(R.id.tv_NgayHienTai1_ChiTietHopDong);

        Bundle b = getIntent().getExtras();
        if (b == null){
            return;
        }

        HopDong hopDong = (HopDong) b.get("HopDong");
        HopDongSession.Id = hopDong.getId();
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");

        mtv_TenUser_ChiTietHopDong.setText(hopDong.getTenCus());
        mtv_TenXe_ChiTietHopDong.setText(hopDong.getTenXe());
        mtv_LoaiXe_ChiTietHopDong.setText(hopDong.getLoaiXe());
        mtv_TruyenDong_ChiTietHopDong.setText(hopDong.getTruyenDong());
        mtv_SoGhe_ChiTietHopDong.setText(hopDong.getSoGhe());
        mtv_TongSoNgayThue_ChiTietHopDong.setText(String.valueOf(hopDong.getTongNgayThue()));
        mtv_SoTienThue_ChiTietHopDong.setText(String.valueOf(decimalFormat.format(hopDong.getSoTienThue())));
        mtv_NgayHienTai3_ChiTietHopDong.setText(hopDong.getNgayDat());
        mtv_NgayHienTai2_ChiTietHopDong.setText(hopDong.getNgayDat());
        mtv_NgayHienTai1_ChiTietHopDong.setText(hopDong.getNgayDat());
        mtv_KyTenUser_ChiTietHopDong.setText(hopDong.getTenCus());
        mtv_NgayNhanXe_ChiTietHopDong.setText(hopDong.getNgayDatXe());
        mtv_GioNhanXe_ChiTietHopDong.setText(hopDong.getGioNhanXe());
        mtv_NgayTraXe_ChiTietHopDong.setText(hopDong.getNgayTraXe());
        mtv_GioTraXe_ChiTietHopDong.setText(hopDong.getGioTraXe());
        mtvMaHopDong_ChiTietHopDong.setText(String.valueOf(hopDong.getId()));
        mtv_DiaDiemTraXe_ChiTietHopDong.setText(hopDong.getDiaDiemTraXe());
        mtv_BienSoXe_ChiTietHopDong.setText(hopDong.getBienSoXe());
        mtv_CCCD_ChiTietHopDong.setText(hopDong.getCCCD());
        mtv_DiaDiemGiaoXe_ChiTietHopDong.setText(hopDong.getDiaDiemNhanXe());


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