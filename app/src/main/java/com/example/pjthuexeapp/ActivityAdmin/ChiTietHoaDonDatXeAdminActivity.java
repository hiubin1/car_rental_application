package com.example.pjthuexeapp.ActivityAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pjthuexeapp.ActivityCus.ChiTietHoaDonDatXeActivity;
import com.example.pjthuexeapp.Adapter.GiaHanAdapter;
import com.example.pjthuexeapp.Class.DonGiaHan;
import com.example.pjthuexeapp.Class.HoaDon;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_DonGiaHan;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.HoaDonSession;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietHoaDonDatXeAdminActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView tvTitleToolbar;

    TextView mtv_MaDon_ChiTietHoaDonDatXeAdmin,mtv_NgayHienTai_ChiTietHoaDonDatXeAdmin,mtv_TenUser_ChiTietHoaDonDatXeAdmin,mtv_SDTcus_ChiTietHoaDonDatXeAdmin
            ,mtv_CMND_ChiTietHoaDonDatXeAdmin
            ,mtv_TenXe_ChiTietHoaDonDatXeAdmin, mtv_BienSoXe_ChiTietHoaDonDatXeAdmin, mtv_DiaDiemGiaoXe_ChiTietHoaDonDatXeAdmin,
            mtv_DiaDiemTraXe_ChiTietHoaDonDatXeAdmin, mtv_NgayNhanXe_ChiTietHoaDonDatXeAdmin, mtv_GioNhanXe_ChiTietHoaDonDatXeAdmin,
            mtv_NgayTraXe_ChiTietHoaDonDatXeAdmin, mtv_GioTraXe_ChiTietHoaDonDatXeAdmin, mtv_TongSoNgayThue_ChiTietHoaDonDatXeAdmin
            ,mtv_TongTien_ChiTietHoaDonDatXeAdmin, mtv_GiaTienThue_ChiTietHoaDonDatXeAdmin;

    RecyclerView mRCview_GiaHanDatXeAdmin;

    RecyclerView.LayoutManager layoutManager;

    ArrayList<DonGiaHan> donGiaHanArrayList;

    GiaHanAdapter giaHanAdapter;

    Database_DonGiaHan databaseDonGiaHan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don_dat_xe_admin);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Chi tiết hóa đơn");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseDonGiaHan = new Database_DonGiaHan(this);
        capNhatDuLieu();
        mtv_MaDon_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_MaDon_ChiTietHoaDonDatXeAdmin);
        mtv_NgayHienTai_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_NgayHienTai_ChiTietHoaDonDatXeAdmin);
        mtv_TenUser_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_TenUser_ChiTietHoaDonDatXeAdmin);
        mtv_SDTcus_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_SDTcus_ChiTietHoaDonDatXeAdmin);
        mtv_CMND_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_CMND_ChiTietHoaDonDatXeAdmin);
        mtv_TenXe_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_TenXe_ChiTietHoaDonDatXeAdmin);
        mtv_BienSoXe_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_BienSoXe_ChiTietHoaDonDatXeAdmin);
        mtv_DiaDiemGiaoXe_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_DiaDiemGiaoXe_ChiTietHoaDonDatXeAdmin);
        mtv_DiaDiemTraXe_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_DiaDiemTraXe_ChiTietHoaDonDatXeAdmin);
        mtv_NgayNhanXe_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_NgayNhanXe_ChiTietHoaDonDatXeAdmin);
        mtv_GioNhanXe_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_GioNhanXe_ChiTietHoaDonDatXeAdmin);
        mtv_NgayTraXe_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_NgayTraXe_ChiTietHoaDonDatXeAdmin);
        mtv_GioTraXe_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_GioTraXe_ChiTietHoaDonDatXeAdmin);
        mtv_TongSoNgayThue_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_TongSoNgayThue_ChiTietHoaDonDatXeAdmin);
        mtv_TongTien_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_TongTien_ChiTietHoaDonDatXeAdmin);
        mtv_GiaTienThue_ChiTietHoaDonDatXeAdmin = findViewById(R.id.tv_GiaTienThue_ChiTietHoaDonDatXeAdmin);

        Bundle b = getIntent().getExtras();
        if(b == null) {
            return;
        }

        HoaDon hoaDon = (HoaDon) b.get("HoaDon");
        HoaDonSession.id1 = hoaDon.getId();
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
        mtv_MaDon_ChiTietHoaDonDatXeAdmin.setText(String.valueOf(hoaDon.getId()));
        mtv_NgayHienTai_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getNgatDatDon());
        mtv_TenUser_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getTenCus());
        mtv_SDTcus_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getSDTCus());
        mtv_CMND_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getCMND());
        mtv_TenXe_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getTenXe());
        mtv_BienSoXe_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getBienSoXe());
        mtv_DiaDiemGiaoXe_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getDiaDiemNhanXe());
        mtv_DiaDiemTraXe_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getDiaDiemTraXe());
        mtv_NgayNhanXe_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getNgayNhanXe());
        mtv_GioNhanXe_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getGioNhanXe());
        mtv_NgayTraXe_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getNgayTraXe());
        mtv_GioTraXe_ChiTietHoaDonDatXeAdmin.setText(hoaDon.getGioTraXe());
        mtv_TongSoNgayThue_ChiTietHoaDonDatXeAdmin.setText(String.valueOf(hoaDon.getTongNgayThue()));
        mtv_TongTien_ChiTietHoaDonDatXeAdmin.setText(decimalFormat.format(hoaDon.getTongTien()));
        mtv_GiaTienThue_ChiTietHoaDonDatXeAdmin.setText(decimalFormat.format(hoaDon.getGiaTienThueXe()));

    }

    @Override
    public void onResume() {
        super.onResume();
        capNhatDuLieu();
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (donGiaHanArrayList == null){
            donGiaHanArrayList = new ArrayList<>();
        } else  {
            donGiaHanArrayList.removeAll(donGiaHanArrayList);
        }
        Cursor cursor = databaseDonGiaHan.GetDonGiaHanFromId(HoaDonSession.id1);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()){
            do {
                DonGiaHan donGiaHan = new DonGiaHan();
                donGiaHan.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_DONGIAHAN)));
                donGiaHan.setTongNgayGiaHan(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_TONGNGAYGIAHAN_DONGIAHAN)));
                donGiaHan.setTongTien(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_TONGTIEN_DONGIAHAN)));
                donGiaHan.setNgayBatDauGiaHan(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NGAYBATDAUGIAHAN_DONGIAHAN)));

                donGiaHanArrayList.add(donGiaHan);
            } while (cursor.moveToNext());
        }
        setRV();
    }

    private void setRV() {
        if (donGiaHanArrayList != null){
            mRCview_GiaHanDatXeAdmin = findViewById(R.id.RCview_GiaHanDatXeAdmin);
            layoutManager = new LinearLayoutManager(this);
            mRCview_GiaHanDatXeAdmin.setLayoutManager(layoutManager);
            giaHanAdapter = new GiaHanAdapter(ChiTietHoaDonDatXeAdminActivity.this,R.layout.list_giahan, donGiaHanArrayList);
            mRCview_GiaHanDatXeAdmin.setAdapter(giaHanAdapter);
            giaHanAdapter.notifyDataSetChanged();
            mRCview_GiaHanDatXeAdmin.invalidate();
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