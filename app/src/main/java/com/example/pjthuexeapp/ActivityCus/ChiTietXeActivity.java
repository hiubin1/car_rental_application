package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;
import com.example.pjthuexeapp.Session.XeSession;

import java.text.DecimalFormat;

public class ChiTietXeActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView tvTitleToolbar;

    ImageView mimg_HinhXe_ChiTietXe;

    TextView mtv_TenXe_ChiTietXe, mtv_GiaTien_ChiTietXe, mtv_Loaixe_ChiTietXe, mtv_DiaDiemGiaoXe_ChiTietXe
            ,mtv_BienSoXe_ChiTietXe, mtv_TrangThai_ChiTietXe, mtv_TruyenDong_ChiTietXe, mtv_SoGhe_ChiTietXe
            ,mtv_NhienLieu_ChiTietXe ,mtv_NangLuongTieuHao_ChiTietXe;

    byte[] hinhXeBytes;

    Database_User database_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_xe);

        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);
        mimg_HinhXe_ChiTietXe = findViewById(R.id.img_HinhXe_ChiTietXe);
        mtv_TenXe_ChiTietXe = findViewById(R.id.tv_TenXe_ChiTietXe);
        mtv_GiaTien_ChiTietXe = findViewById(R.id.tv_GiaTien_ChiTietXe);
        mtv_Loaixe_ChiTietXe = findViewById(R.id.tv_Loaixe_ChiTietXe);
        mtv_DiaDiemGiaoXe_ChiTietXe = findViewById(R.id.tv_DiaDiemGiaoXe_ChiTietXe);
        mtv_BienSoXe_ChiTietXe = findViewById(R.id.tv_BienSoXe_ChiTietXe);
        mtv_TrangThai_ChiTietXe = findViewById(R.id.tv_TrangThai_ChiTietXe);
        mtv_TruyenDong_ChiTietXe = findViewById(R.id.tv_TruyenDong_ChiTietXe);
        mtv_SoGhe_ChiTietXe = findViewById(R.id.tv_SoGhe_ChiTietXe);
        mtv_NhienLieu_ChiTietXe = findViewById(R.id.tv_NhienLieu_ChiTietXe);
        mtv_NangLuongTieuHao_ChiTietXe = findViewById(R.id.tv_NangLuongTieuHao_ChiTietXe);
        database_user = new Database_User(this);

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Chi tiết xe");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        if(b == null) {
            return;
        }



        Xe xe = (Xe) b.get("Xe");
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
        XeSession.id = xe.getId();
        XeSession.LoaiXe = xe.getLoaiXe();
        XeSession.SoGhe = xe.getSoGhe();
        XeSession.TruyenDong = xe.getTruyenDong();
        XeSession.NhienLieu = xe.getNhienLieu();
        XeSession.NangLuong = xe.getNangLuongTieuHao();
        XeSession.TrangThai = xe.getTrangThai();
        hinhXeBytes = xe.getHinhXe();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhXeBytes, 0, hinhXeBytes.length);
        mimg_HinhXe_ChiTietXe.setImageBitmap(bitmap);
        mtv_TenXe_ChiTietXe.setText(xe.getTenXe());
        mtv_NhienLieu_ChiTietXe.setText(xe.getNhienLieu());
        mtv_NangLuongTieuHao_ChiTietXe.setText(xe.getNangLuongTieuHao());
        mtv_BienSoXe_ChiTietXe.setText(xe.getBienSoXe());
        mtv_DiaDiemGiaoXe_ChiTietXe.setText(xe.getDiaDiemNhanXe());
        int giaTien = xe.getGiaTien();
        String giaTienChuoi = String.valueOf((giaTien));
        mtv_GiaTien_ChiTietXe.setText(giaTienChuoi);
        mtv_SoGhe_ChiTietXe.setText(xe.getSoGhe());
        mtv_TrangThai_ChiTietXe.setText(xe.getTrangThai());
        mtv_TruyenDong_ChiTietXe.setText(xe.getTruyenDong());
        mtv_Loaixe_ChiTietXe.setText(xe.getLoaiXe());
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



    public void btnChonThueXe_ChiTietXe_onclick(View view) {
        String sdt = UserSession.userPhoneCurrent;

        String userGPLX = database_user.KiemTraGPLX(sdt);

        if (userGPLX.equals("Không")){
            OpenDialogThongBaoGPLX(Gravity.CENTER);
            return;
        }
        Intent i = new Intent(getApplicationContext(), ChonLichThueXeActivity.class);
        Bundle b = new Bundle();
        b.putByteArray("AnhXe", hinhXeBytes);
        b.putString("TenXe", mtv_TenXe_ChiTietXe.getText().toString());
        b.putString("GiaTien", mtv_GiaTien_ChiTietXe.getText().toString());
        b.putString("BienSoXe", mtv_BienSoXe_ChiTietXe.getText().toString());
        b.putString("DiaDiemNhanXe", mtv_DiaDiemGiaoXe_ChiTietXe.getText().toString());
        b.putString("TruyenDong", mtv_TruyenDong_ChiTietXe.getText().toString());
        b.putString("SoGhe", mtv_SoGhe_ChiTietXe.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }
    private void OpenDialogThongBaoGPLX(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_thongbao_gplx);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = gravity;
        window.setAttributes(windowAtrributes);

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        Button btnXacNhan_GPLX = dialog.findViewById(R.id.btnXacNhan_GPLX);


        btnXacNhan_GPLX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}