package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pjthuexeapp.Class.HoaDon;
import com.example.pjthuexeapp.Class.HopDong;
import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.Class.ThongBao;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_DonDatXe;
import com.example.pjthuexeapp.Database.Database_HopDong;
import com.example.pjthuexeapp.Database.Database_ThongBao;
import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.HoaDonSession;
import com.example.pjthuexeapp.Session.HopDongSession;
import com.example.pjthuexeapp.Session.UserSession;
import com.example.pjthuexeapp.Session.XeSession;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HopDongThueXeActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView tvTitleToolbar;

    TextView mtv_NgayHienTai1_HopDongThueXe,  mtv_TenUser_HopDongThueXe
            ,mtv_CCCD_HopDongThueXe, mtv_TenXe_HopDongThueXe, mtv_LoaiXe_HopDongThueXe
            ,mtv_TruyenDong_HopDongThueXe, mtv_SoGhe_HopDongThueXe, mtv_BienSoXe_HopDongThueXe
            ,mtv_TongSoNgayThue_HopDongThueXe, mtv_SoTienThue_HopDongThueXe, mtv_NgayHienTai2_HopDongThueXe,
    mtv_NgayHienTai3_HopDongThueXe, mtv_KyTenUser_HopDongThueXe, mtv_NgayNhanXe_HopDongThueXe
            ,mtv_GioNhanXe_HopDongThueXe, mtv_NgayTraXe_HopDongThueXe, mtv_GioTraXe_HopDongThueXe
            ,mtv_DiaDiemTraXe_HopDongThueXe, mtv_DiaDiemGiaoXe_HopDongThueXe;

    CheckBox mchkXacNhanHopDong_HopDongThueXe;

    Database_User database_user;

    private long id = -1;


    String NgayHienTai1, TenCus, CCCD, TenXe, LoaiXe, TruyenDong, SoGhe, BienSoXe,NgayHienTai2, NgayHienTai3, KyTenUser, NgayNhanXe
            , GioNhanXe, NgayTraXe, GioTraXe, DiaDiemTraXe, DiaDiemGiaoXe;

    String NgayDatDon, HoTen, TenXeHoaDon, BienSoXeHoaDon, DiaDiemNhanXeHoaDon, DiaDiemTraXeHoaDon, NgayNhanXeHoaDon, NgayTraXeHoaDon, GioNhanXeHoaDon, GioTraXeHoaDon;


    float TongTien = 0;

    float GiaTienThueXe = 0;

    int TongSoNgayThue = 0, SoTienThue = 0;

    Database_HopDong databaseHopDong;

    Database_DonDatXe databaseDonDatXe;

    Database_Xe databaseXe;

    Database_ThongBao databaseThongBao;

    int songay1 = 0;

    TextView mtv_NgayHienTai_HoaDon,mtv_TenUser_HoaDon,mtv_SDTcus_HoaDon,mtv_CMND_HoaDon
            ,mtv_TenXe_HoaDon, mtv_BienSoXe_HoaDon, mtv_DiaDiemGiaoXe_HoaDon,
            mtv_DiaDiemTraXe_HoaDon, mtv_NgayNhanXe_HoaDon, mtv_GioNhanXe_HoaDon,
            mtv_NgayTraXe_HoaDon, mtv_GioTraXe_HoaDon, mtv_TongSoNgayThue_HoaDon
            ,mtv_TongTien_HoaDon, mtv_GiaTienThue_HoaDon;



    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong_thue_xe);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Hợp đồng thuê xe");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHopDong = new Database_HopDong(this);
        databaseXe = new Database_Xe(this);
        databaseDonDatXe = new Database_DonDatXe(this);
        databaseThongBao = new Database_ThongBao(this);
        mtv_DiaDiemGiaoXe_HopDongThueXe = findViewById(R.id.tv_DiaDiemGiaoXe_HopDongThueXe);
        mtv_DiaDiemTraXe_HopDongThueXe = findViewById(R.id.tv_DiaDiemTraXe_HopDongThueXe);
        mtv_GioTraXe_HopDongThueXe = findViewById(R.id.tv_GioTraXe_HopDongThueXe);
        mtv_NgayTraXe_HopDongThueXe = findViewById(R.id.tv_NgayTraXe_HopDongThueXe);
        mtv_GioNhanXe_HopDongThueXe = findViewById(R.id.tv_GioNhanXe_HopDongThueXe);
        mtv_NgayNhanXe_HopDongThueXe = findViewById(R.id.tv_NgayNhanXe_HopDongThueXe);
        mchkXacNhanHopDong_HopDongThueXe = findViewById(R.id.chkXacNhanHopDong_HopDongThueXe);
        mtv_KyTenUser_HopDongThueXe = findViewById(R.id.tv_KyTenUser_HopDongThueXe);
        mtv_NgayHienTai3_HopDongThueXe = findViewById(R.id.tv_NgayHienTai3_HopDongThueXe);
        mtv_NgayHienTai2_HopDongThueXe = findViewById(R.id.tv_NgayHienTai2_HopDongThueXe);
        mtv_SoTienThue_HopDongThueXe = findViewById(R.id.tv_SoTienThue_HopDongThueXe);
        mtv_TongSoNgayThue_HopDongThueXe = findViewById(R.id.tv_TongSoNgayThue_HopDongThueXe);
        mtv_BienSoXe_HopDongThueXe = findViewById(R.id.tv_BienSoXe_HopDongThueXe);
        mtv_SoGhe_HopDongThueXe = findViewById(R.id.tv_SoGhe_HopDongThueXe);
        mtv_TruyenDong_HopDongThueXe = findViewById(R.id.tv_TruyenDong_HopDongThueXe);
        mtv_LoaiXe_HopDongThueXe = findViewById(R.id.tv_LoaiXe_HopDongThueXe);
        mtv_TenXe_HopDongThueXe = findViewById(R.id.tv_TenXe_HopDongThueXe);
        mtv_CCCD_HopDongThueXe = findViewById(R.id.tv_CCCD_HopDongThueXe);
        mtv_TenUser_HopDongThueXe = findViewById(R.id.tv_TenUser_HopDongThueXe);
        mtv_NgayHienTai1_HopDongThueXe = findViewById(R.id.tv_NgayHienTai1_HopDongThueXe);



        database_user = new Database_User(this);
        SetCCCDUser();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mtv_TenUser_HopDongThueXe.setText(bundle.getString("HoTen"));
            mtv_TenXe_HopDongThueXe.setText(bundle.getString("TenXe"));
            mtv_SoTienThue_HopDongThueXe.setText(bundle.getString("GiaTien"));
            mtv_TruyenDong_HopDongThueXe.setText(bundle.getString("TruyenDong"));
            mtv_SoGhe_HopDongThueXe.setText(bundle.getString("SoGhe"));
            mtv_BienSoXe_HopDongThueXe.setText(bundle.getString("BienSoXe"));
            mtv_KyTenUser_HopDongThueXe.setText(bundle.getString("HoTen"));
            mtv_NgayNhanXe_HopDongThueXe.setText(bundle.getString("NgayNhanXe"));
            mtv_NgayTraXe_HopDongThueXe.setText(bundle.getString("NgayTraXe"));
            mtv_GioNhanXe_HopDongThueXe.setText(bundle.getString("GioNhanXe"));
            mtv_GioTraXe_HopDongThueXe.setText(bundle.getString("GioTraXe"));
            mtv_DiaDiemGiaoXe_HopDongThueXe.setText(bundle.getString("DiaDiemNhanXe"));
            mtv_DiaDiemTraXe_HopDongThueXe.setText(bundle.getString("DiaDiemTraXe"));
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(currentDate);
            mtv_NgayHienTai1_HopDongThueXe.setText(formattedDate);
            mtv_NgayHienTai2_HopDongThueXe.setText(formattedDate);
            mtv_NgayHienTai3_HopDongThueXe.setText(formattedDate);
            String ngayNhanXeStr = mtv_NgayNhanXe_HopDongThueXe.getText().toString();
            String ngayTraXeStr = mtv_NgayTraXe_HopDongThueXe.getText().toString();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            try {
                // Chuyển đổi chuỗi ngày nhận xe thành đối tượng Date
                Date ngayNhanXe = format.parse(ngayNhanXeStr);

                // Chuyển đổi chuỗi ngày trả xe thành đối tượng Date
                Date ngayTraXe = format.parse(ngayTraXeStr);

                // Tính số mili giây giữa ngày trả xe và ngày nhận xe
                long thoiGian = ngayTraXe.getTime() - ngayNhanXe.getTime();

                // Chuyển đổi thời gian từ mili giây sang số ngày
                long soNgay = TimeUnit.MILLISECONDS.toDays(thoiGian);

                // Đặt giá trị tổng số ngày vào TextView
                mtv_TongSoNgayThue_HopDongThueXe.setText(String.valueOf(soNgay));
                songay1 = Integer.parseInt(mtv_TongSoNgayThue_HopDongThueXe.getText().toString());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            NgayDatDon = formattedDate;
            DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
            HoTen = (bundle.getString("HoTen"));
            TenXeHoaDon = (bundle.getString("TenXe"));
            BienSoXeHoaDon = (bundle.getString("BienSoXe"));
            DiaDiemNhanXeHoaDon = (bundle.getString("DiaDiemNhanXe"));
            DiaDiemTraXeHoaDon = (bundle.getString("DiaDiemTraXe"));
            NgayNhanXeHoaDon = (bundle.getString("NgayNhanXe"));
            GioNhanXeHoaDon = (bundle.getString("GioNhanXe"));
            NgayTraXeHoaDon = (bundle.getString("NgayTraXe"));
            GioTraXeHoaDon = (bundle.getString("GioTraXe"));
            GiaTienThueXe = Integer.parseInt(bundle.getString("GiaTien"));
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

    @SuppressLint("Range")
    public void SetCCCDUser(){
        Cursor cursor = database_user.GetUserFromSdt(UserSession.userPhoneCurrent);
        if (cursor != null){
            while (cursor.moveToNext()){
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_USER)));
                nguoiDung.setCCCD(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CCCD_USER)));

                mtv_CCCD_HopDongThueXe.setText(nguoiDung.getCCCD());

            }
        }
    }
    @SuppressLint("Range")
    public HopDong layDuLieuNguoiDung(){
        NgayHienTai1 = mtv_NgayHienTai1_HopDongThueXe.getText().toString();
        TenCus = mtv_TenUser_HopDongThueXe.getText().toString();
        CCCD = mtv_CCCD_HopDongThueXe.getText().toString();
        TenXe = mtv_TenXe_HopDongThueXe.getText().toString();
        LoaiXe = mtv_LoaiXe_HopDongThueXe.getText().toString();
        TruyenDong = mtv_TruyenDong_HopDongThueXe.getText().toString();
        SoGhe = mtv_SoGhe_HopDongThueXe.getText().toString();
        BienSoXe = mtv_BienSoXe_HopDongThueXe.getText().toString();
        NgayHienTai2 = mtv_NgayHienTai2_HopDongThueXe.getText().toString();
        NgayHienTai3 = mtv_NgayHienTai3_HopDongThueXe.getText().toString();
        KyTenUser = mtv_KyTenUser_HopDongThueXe.getText().toString();
        NgayNhanXe = mtv_NgayNhanXe_HopDongThueXe.getText().toString();
        GioNhanXe = mtv_GioNhanXe_HopDongThueXe.getText().toString();
        NgayTraXe = mtv_NgayTraXe_HopDongThueXe.getText().toString();
        GioTraXe = mtv_GioTraXe_HopDongThueXe.getText().toString();
        DiaDiemGiaoXe = mtv_DiaDiemGiaoXe_HopDongThueXe.getText().toString();
        DiaDiemTraXe = mtv_DiaDiemTraXe_HopDongThueXe.getText().toString();
        TongSoNgayThue = Integer.parseInt(mtv_TongSoNgayThue_HopDongThueXe.getText().toString());
        SoTienThue = Integer.parseInt(mtv_SoTienThue_HopDongThueXe.getText().toString());

        HopDong hopDong = new HopDong();
        hopDong.setId(id);
        hopDong.setIdThueXe(1);
        hopDong.setIdCus(UserSession.userId);
        hopDong.setIdXe(XeSession.id);
        hopDong.setTenCus(TenCus);
        hopDong.setCCCD(CCCD);
        hopDong.setNgayDat(NgayHienTai1);
        hopDong.setTenXe(TenXe);
        hopDong.setLoaiXe(LoaiXe);
        hopDong.setTruyenDong(TruyenDong);
        hopDong.setSoGhe(SoGhe);
        hopDong.setBienSoXe(BienSoXe);
        hopDong.setNgayDatXe(NgayNhanXe);
        hopDong.setNgayTraXe(NgayTraXe);
        hopDong.setGioTraXe(GioTraXe);
        hopDong.setGioNhanXe(GioNhanXe);
        hopDong.setDiaDiemNhanXe(DiaDiemGiaoXe);
        hopDong.setDiaDiemTraXe(DiaDiemTraXe);
        hopDong.setTongNgayThue(TongSoNgayThue);
        hopDong.setSoTienThue(SoTienThue);

        return hopDong;
    }


    private void OpenDialogThongBaoThueXe(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_thongbao_dangky);

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

        Button btnOKThongBaoDialog = dialog.findViewById(R.id.btnXacNhan_ThanhToan);



        btnOKThongBaoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CustomerActivity.class);
                startActivity(i);
            }
        });
        dialog.show();
    }

    private void OpenDialogHoaDonThueXe(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_hoadon);

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

        Button btnThanhToanHoaDonDialog = dialog.findViewById(R.id.btnThanhToan_HoaDon);
        Button btnHuyHoaDonDialog = dialog.findViewById(R.id.btnHuy_HoaDon);

        mtv_NgayHienTai_HoaDon = dialog.findViewById(R.id.tv_NgayHienTai_HoaDon);
        mtv_TenUser_HoaDon = dialog.findViewById(R.id.tv_TenUser_HoaDon);
        mtv_SDTcus_HoaDon = dialog.findViewById(R.id.tv_SDTcus_HoaDon);
        mtv_CMND_HoaDon = dialog.findViewById(R.id.tv_CMND_HoaDon);
        mtv_TenXe_HoaDon = dialog.findViewById(R.id.tv_TenXe_HoaDon);
        mtv_BienSoXe_HoaDon = dialog.findViewById(R.id.tv_BienSoXe_HoaDon);
        mtv_DiaDiemGiaoXe_HoaDon = dialog.findViewById(R.id.tv_DiaDiemGiaoXe_HoaDon);
        mtv_DiaDiemTraXe_HoaDon = dialog.findViewById(R.id.tv_DiaDiemTraXe_HoaDon);
        mtv_NgayNhanXe_HoaDon = dialog.findViewById(R.id.tv_NgayNhanXe_HoaDon);
        mtv_GioNhanXe_HoaDon = dialog.findViewById(R.id.tv_GioNhanXe_HoaDon);
        mtv_NgayTraXe_HoaDon = dialog.findViewById(R.id.tv_NgayTraXe_HoaDon);
        mtv_GioTraXe_HoaDon = dialog.findViewById(R.id.tv_GioTraXe_HoaDon);
        mtv_TongSoNgayThue_HoaDon = dialog.findViewById(R.id.tv_TongSoNgayThue_HoaDon);
        mtv_TongTien_HoaDon = dialog.findViewById(R.id.tv_TongTien_HoaDon);
        mtv_GiaTienThue_HoaDon = dialog.findViewById(R.id.tv_GiaTienThue_HoaDon);


        mtv_NgayHienTai_HoaDon.setText(NgayDatDon);
        mtv_TenUser_HoaDon.setText(HoTen);
        mtv_SDTcus_HoaDon.setText(UserSession.userPhoneCurrent);
        mtv_CMND_HoaDon.setText(mtv_CCCD_HopDongThueXe.getText().toString());
        mtv_TenXe_HoaDon.setText(TenXeHoaDon);
        mtv_BienSoXe_HoaDon.setText(BienSoXeHoaDon);
        mtv_DiaDiemGiaoXe_HoaDon.setText(DiaDiemNhanXeHoaDon);
        mtv_DiaDiemTraXe_HoaDon.setText(DiaDiemTraXeHoaDon);
        mtv_NgayNhanXe_HoaDon.setText(NgayNhanXeHoaDon);
        mtv_GioNhanXe_HoaDon.setText(GioNhanXeHoaDon);
        mtv_NgayTraXe_HoaDon.setText(NgayTraXeHoaDon);
        mtv_GioTraXe_HoaDon.setText(GioTraXeHoaDon);
        mtv_TongSoNgayThue_HoaDon.setText(mtv_TongSoNgayThue_HopDongThueXe.getText().toString());

        String tongSoNgayThueString = mtv_TongSoNgayThue_HopDongThueXe.getText().toString();
        int tongSoNgayThue = 0;
        try {
            if (!tongSoNgayThueString.isEmpty()) {
                tongSoNgayThue = Integer.parseInt(tongSoNgayThueString);
            }

            // Tiếp tục xử lý khác và tính toán tổng tiền
            TongTien = tongSoNgayThue * GiaTienThueXe;

            DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
            String formattedTongTien = decimalFormat.format(TongTien);
            String formattedGiaTienThue = decimalFormat.format(GiaTienThueXe);
            mtv_TongTien_HoaDon.setText(formattedTongTien);
            mtv_GiaTienThue_HoaDon.setText(formattedGiaTienThue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý khi chuỗi không thể chuyển đổi thành số nguyên
        }

        btnThanhToanHoaDonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HopDong hopDong = layDuLieuNguoiDung();
                HoaDon hoaDon = layDuLieuNguoiDung1();
                if (hopDong != null) {
                    if (databaseHopDong.ThemHopDong(hopDong) != -1 && databaseDonDatXe.ThemHoaDon(hoaDon) != -1) {
                        // Thêm thông báo vào cơ sở dữ liệu
                        ThongBao thongBao = new ThongBao();
                        thongBao.setIdCus(UserSession.userId);
                        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
                        String formattedTongTien = decimalFormat.format(TongTien);
                        thongBao.setTieuDe("Đặt xe thành công");
                        thongBao.setNoiDung(" - Đặt vào ngày: " +  NgayDatDon + "\n - tổng tiền: " + formattedTongTien + "đ");
                        if (databaseThongBao.ThemThongBao(thongBao) != -1) {
                            OpenDialogThongBaoThueXe(Gravity.CENTER);
                            // Gửi broadcast để cập nhật danh sách thông báo
                            Intent intent = new Intent("cap_nhat_danh_sach_thong_bao");
                            sendBroadcast(intent);
                        } else {
                            Toast.makeText(HopDongThueXeActivity.this, "Không thể thêm thông báo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(HopDongThueXeActivity.this, "Lỗi khi thêm hợp đồng hoặc hóa đơn", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(HopDongThueXeActivity.this, "Hợp đồng không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

        btnHuyHoaDonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public HoaDon layDuLieuNguoiDung1() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(id);
        hoaDon.setIdCus(UserSession.userId);
        hoaDon.setIdXe(XeSession.id);
        hoaDon.setIdThueXe(1);
        hoaDon.setNgatDatDon(NgayHienTai1);
        hoaDon.setTenCus(HoTen);
        hoaDon.setSDTCus(UserSession.userPhoneCurrent);
        hoaDon.setCMND(CCCD);
        hoaDon.setTenXe(TenXeHoaDon);
        hoaDon.setBienSoXe(BienSoXeHoaDon);
        hoaDon.setDiaDiemNhanXe(DiaDiemNhanXeHoaDon);
        hoaDon.setDiaDiemTraXe(DiaDiemTraXeHoaDon);
        hoaDon.setNgayNhanXe(NgayNhanXeHoaDon);
        hoaDon.setNgayTraXe(NgayTraXeHoaDon);
        hoaDon.setGioNhanXe(GioNhanXeHoaDon);
        hoaDon.setGioTraXe(GioTraXeHoaDon);
        hoaDon.setTongNgayThue(TongSoNgayThue);
        hoaDon.setTongTien(Float.valueOf(TongTien));
        hoaDon.setGiaTienThueXe(Float.valueOf(GiaTienThueXe));

        return hoaDon;
    }

    public void btnThanhToan_HopDongThueXe_onclick(View view) {
        if (mchkXacNhanHopDong_HopDongThueXe.isChecked()){
                OpenDialogHoaDonThueXe(Gravity.CENTER);
        } else {
            Toast.makeText(HopDongThueXeActivity.this,"Vui lòng xác nhận hợp đồng",Toast.LENGTH_SHORT).show();
        }
    }
}