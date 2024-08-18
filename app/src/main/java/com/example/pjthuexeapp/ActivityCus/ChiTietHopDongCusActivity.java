package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pjthuexeapp.Class.HopDong;
import com.example.pjthuexeapp.R;

import java.text.DecimalFormat;

public class ChiTietHopDongCusActivity extends AppCompatActivity {
    Toolbar toolbar;

    TextView tvTitleToolbar;
    TextView mtvMaHopDong_ChiTietHopDongCus, mtv_NgayHienTai1_ChiTietHopDongCus, mtv_TenUser_ChiTietHopDongCus,
            mtv_CCCD_ChiTietHopDongCus, mtv_TenXe_ChiTietHopDongCus, mtv_LoaiXe_ChiTietHopDongCus, mtv_TruyenDong_ChiTietHopDongCus,
            mtv_SoGhe_ChiTietHopDongCus, mtv_BienSoXe_ChiTietHopDongCus, mtv_DiaDiemGiaoXe_ChiTietHopDongCus,
            mtv_DiaDiemTraXe_ChiTietHopDongCus, mtv_NgayNhanXe_ChiTietHopDongCus, mtv_GioNhanXe_ChiTietHopDongCus,
            mtv_NgayTraXe_ChiTietHopDongCus, mtv_GioTraXe_ChiTietHopDongCus, mtv_TongSoNgayThue_ChiTietHopDongCus,
            mtv_SoTienThue_ChiTietHopDongCus, mtv_NgayHienTai2_ChiTietHopDongCus, mtv_NgayHienTai3_ChiTietHopDongCus,
            mtv_KyTenUser_ChiTietHopDongCus;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hop_dong_cus);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Chi tiết hợp đồng");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mtvMaHopDong_ChiTietHopDongCus = findViewById(R.id.tvMaHopDong_ChiTietHopDongCus);
        mtv_DiaDiemGiaoXe_ChiTietHopDongCus = findViewById(R.id.tv_DiaDiemGiaoXe_ChiTietHopDongCus);
        mtv_DiaDiemTraXe_ChiTietHopDongCus = findViewById(R.id.tv_DiaDiemTraXe_ChiTietHopDongCus);
        mtv_GioTraXe_ChiTietHopDongCus = findViewById(R.id.tv_GioTraXe_ChiTietHopDongCus);
        mtv_NgayTraXe_ChiTietHopDongCus = findViewById(R.id.tv_NgayTraXe_ChiTietHopDongCus);
        mtv_GioNhanXe_ChiTietHopDongCus = findViewById(R.id.tv_GioNhanXe_ChiTietHopDongCus);
        mtv_NgayNhanXe_ChiTietHopDongCus = findViewById(R.id.tv_NgayNhanXe_ChiTietHopDongCus);
        mtv_KyTenUser_ChiTietHopDongCus = findViewById(R.id.tv_KyTenUser_ChiTietHopDongCus);
        mtv_NgayHienTai3_ChiTietHopDongCus = findViewById(R.id.tv_NgayHienTai3_ChiTietHopDongCus);
        mtv_NgayHienTai2_ChiTietHopDongCus = findViewById(R.id.tv_NgayHienTai2_ChiTietHopDongCus);
        mtv_SoTienThue_ChiTietHopDongCus = findViewById(R.id.tv_SoTienThue_ChiTietHopDongCus);
        mtv_TongSoNgayThue_ChiTietHopDongCus = findViewById(R.id.tv_TongSoNgayThue_ChiTietHopDongCus);
        mtv_BienSoXe_ChiTietHopDongCus = findViewById(R.id.tv_BienSoXe_ChiTietHopDongCus);
        mtv_SoGhe_ChiTietHopDongCus = findViewById(R.id.tv_SoGhe_ChiTietHopDongCus);
        mtv_TruyenDong_ChiTietHopDongCus = findViewById(R.id.tv_TruyenDong_ChiTietHopDongCus);
        mtv_LoaiXe_ChiTietHopDongCus = findViewById(R.id.tv_LoaiXe_ChiTietHopDongCus);
        mtv_TenXe_ChiTietHopDongCus = findViewById(R.id.tv_TenXe_ChiTietHopDongCus);
        mtv_CCCD_ChiTietHopDongCus = findViewById(R.id.tv_CCCD_ChiTietHopDongCus);
        mtv_TenUser_ChiTietHopDongCus = findViewById(R.id.tv_TenUser_ChiTietHopDongCus);
        mtv_NgayHienTai1_ChiTietHopDongCus = findViewById(R.id.tv_NgayHienTai1_ChiTietHopDongCus);

        Bundle b = getIntent().getExtras();
        if (b == null){
            return;
        }

        HopDong hopDong = (HopDong) b.get("HopDong");
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");

        mtv_TenUser_ChiTietHopDongCus.setText(hopDong.getTenCus());
        mtv_TenXe_ChiTietHopDongCus.setText(hopDong.getTenXe());
        mtv_LoaiXe_ChiTietHopDongCus.setText(hopDong.getLoaiXe());
        mtv_TruyenDong_ChiTietHopDongCus.setText(hopDong.getTruyenDong());
        mtv_SoGhe_ChiTietHopDongCus.setText(hopDong.getSoGhe());
        mtv_TongSoNgayThue_ChiTietHopDongCus.setText(String.valueOf(hopDong.getTongNgayThue()));
        mtv_SoTienThue_ChiTietHopDongCus.setText(String.valueOf(decimalFormat.format(hopDong.getSoTienThue())));
        mtv_NgayHienTai3_ChiTietHopDongCus.setText(hopDong.getNgayDat());
        mtv_NgayHienTai2_ChiTietHopDongCus.setText(hopDong.getNgayDat());
        mtv_NgayHienTai1_ChiTietHopDongCus.setText(hopDong.getNgayDat());
        mtv_KyTenUser_ChiTietHopDongCus.setText(hopDong.getTenCus());
        mtv_NgayNhanXe_ChiTietHopDongCus.setText(hopDong.getNgayDatXe());
        mtv_GioNhanXe_ChiTietHopDongCus.setText(hopDong.getGioNhanXe());
        mtv_NgayTraXe_ChiTietHopDongCus.setText(hopDong.getNgayTraXe());
        mtv_GioTraXe_ChiTietHopDongCus.setText(hopDong.getGioTraXe());
        mtvMaHopDong_ChiTietHopDongCus.setText(String.valueOf(hopDong.getId()));
        mtv_DiaDiemTraXe_ChiTietHopDongCus.setText(hopDong.getDiaDiemTraXe());
        mtv_BienSoXe_ChiTietHopDongCus.setText(hopDong.getBienSoXe());
        mtv_CCCD_ChiTietHopDongCus.setText(hopDong.getCCCD());
        mtv_DiaDiemGiaoXe_ChiTietHopDongCus.setText(hopDong.getDiaDiemNhanXe());
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