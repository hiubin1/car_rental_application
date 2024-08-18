package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pjthuexeapp.Adapter.GiaHanAdapter;
import com.example.pjthuexeapp.Adapter.LichSuDatXeAdapter;
import com.example.pjthuexeapp.Class.DonGiaHan;
import com.example.pjthuexeapp.Class.HoaDon;
import com.example.pjthuexeapp.Class.ThongBao;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_DonGiaHan;
import com.example.pjthuexeapp.Database.Database_ThongBao;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.HoaDonSession;
import com.example.pjthuexeapp.Session.UserSession;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChiTietHoaDonDatXeActivity extends AppCompatActivity {
    Toolbar toolbar;

    TextView tvTitleToolbar;

    TextView mtv_MaDon_ChiTietHoaDonDatXe,mtv_NgayHienTai_ChiTietHoaDonDatXe,mtv_TenUser_ChiTietHoaDonDatXe,mtv_SDTcus_ChiTietHoaDonDatXe
            ,mtv_CMND_ChiTietHoaDonDatXe
            ,mtv_TenXe_ChiTietHoaDonDatXe, mtv_BienSoXe_ChiTietHoaDonDatXe, mtv_DiaDiemGiaoXe_ChiTietHoaDonDatXe,
            mtv_DiaDiemTraXe_ChiTietHoaDonDatXe, mtv_NgayNhanXe_ChiTietHoaDonDatXe, mtv_GioNhanXe_ChiTietHoaDonDatXe,
            mtv_NgayTraXe_ChiTietHoaDonDatXe, mtv_GioTraXe_ChiTietHoaDonDatXe, mtv_TongSoNgayThue_ChiTietHoaDonDatXe
            ,mtv_TongTien_ChiTietHoaDonDatXe, mtv_GiaTienThue_ChiTietHoaDonDatXe;

    RecyclerView mRCview_GiaHanDatXe;

    RecyclerView.LayoutManager layoutManager;

    ArrayList<DonGiaHan> donGiaHanArrayList;

    GiaHanAdapter giaHanAdapter;

    Database_DonGiaHan databaseDonGiaHan;

    String SoNgayGiaHan = "";

    float GiaTienThue;

    String formattedDate;

    long tongTien;

    private long id = -1;



    String NgayKetThucThueXe = "";

    Database_ThongBao databaseThongBao;

    Button mbtnGiaHan_ChiTietHoaDonDatXe;

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don_dat_xe);

        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Chi tiết hóa đơn");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseDonGiaHan = new Database_DonGiaHan(this);
        databaseThongBao = new Database_ThongBao(this);
        capNhatDuLieu();


        mtv_MaDon_ChiTietHoaDonDatXe = findViewById(R.id.tv_MaDon_ChiTietHoaDonDatXe);
        mtv_NgayHienTai_ChiTietHoaDonDatXe = findViewById(R.id.tv_NgayHienTai_ChiTietHoaDonDatXe);
        mtv_TenUser_ChiTietHoaDonDatXe = findViewById(R.id.tv_TenUser_ChiTietHoaDonDatXe);
        mtv_SDTcus_ChiTietHoaDonDatXe = findViewById(R.id.tv_SDTcus_ChiTietHoaDonDatXe);
        mtv_CMND_ChiTietHoaDonDatXe = findViewById(R.id.tv_CMND_ChiTietHoaDonDatXe);
        mtv_TenXe_ChiTietHoaDonDatXe = findViewById(R.id.tv_TenXe_ChiTietHoaDonDatXe);
        mtv_BienSoXe_ChiTietHoaDonDatXe = findViewById(R.id.tv_BienSoXe_ChiTietHoaDonDatXe);
        mtv_DiaDiemGiaoXe_ChiTietHoaDonDatXe = findViewById(R.id.tv_DiaDiemGiaoXe_ChiTietHoaDonDatXe);
        mtv_DiaDiemTraXe_ChiTietHoaDonDatXe = findViewById(R.id.tv_DiaDiemTraXe_ChiTietHoaDonDatXe);
        mtv_NgayNhanXe_ChiTietHoaDonDatXe = findViewById(R.id.tv_NgayNhanXe_ChiTietHoaDonDatXe);
        mtv_GioNhanXe_ChiTietHoaDonDatXe = findViewById(R.id.tv_GioNhanXe_ChiTietHoaDonDatXe);
        mtv_NgayTraXe_ChiTietHoaDonDatXe = findViewById(R.id.tv_NgayTraXe_ChiTietHoaDonDatXe);
        mtv_GioTraXe_ChiTietHoaDonDatXe = findViewById(R.id.tv_GioTraXe_ChiTietHoaDonDatXe);
        mtv_TongSoNgayThue_ChiTietHoaDonDatXe = findViewById(R.id.tv_TongSoNgayThue_ChiTietHoaDonDatXe);
        mtv_TongTien_ChiTietHoaDonDatXe = findViewById(R.id.tv_TongTien_ChiTietHoaDonDatXe);
        mtv_GiaTienThue_ChiTietHoaDonDatXe = findViewById(R.id.tv_GiaTienThue_ChiTietHoaDonDatXe);

        mbtnGiaHan_ChiTietHoaDonDatXe = findViewById(R.id.btnGiaHan_ChiTietHoaDonDatXe);

        Bundle b = getIntent().getExtras();
        if(b == null) {
            return;
        }

        HoaDon hoaDon = (HoaDon) b.get("HoaDon");
        HoaDonSession.id = hoaDon.getId();
        HoaDonSession.NgayBatDauGiaHan = hoaDon.getNgayTraXe();
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
        HoaDonSession.GiaTienThue = decimalFormat.format(hoaDon.getGiaTienThueXe());
        mtv_MaDon_ChiTietHoaDonDatXe.setText(String.valueOf(hoaDon.getId()));
        mtv_NgayHienTai_ChiTietHoaDonDatXe.setText(hoaDon.getNgatDatDon());
        mtv_TenUser_ChiTietHoaDonDatXe.setText(hoaDon.getTenCus());
        mtv_SDTcus_ChiTietHoaDonDatXe.setText(hoaDon.getSDTCus());
        mtv_CMND_ChiTietHoaDonDatXe.setText(hoaDon.getCMND());
        mtv_TenXe_ChiTietHoaDonDatXe.setText(hoaDon.getTenXe());
        mtv_BienSoXe_ChiTietHoaDonDatXe.setText(hoaDon.getBienSoXe());
        mtv_DiaDiemGiaoXe_ChiTietHoaDonDatXe.setText(hoaDon.getDiaDiemNhanXe());
        mtv_DiaDiemTraXe_ChiTietHoaDonDatXe.setText(hoaDon.getDiaDiemTraXe());
        mtv_NgayNhanXe_ChiTietHoaDonDatXe.setText(hoaDon.getNgayNhanXe());
        mtv_GioNhanXe_ChiTietHoaDonDatXe.setText(hoaDon.getGioNhanXe());
        mtv_NgayTraXe_ChiTietHoaDonDatXe.setText(hoaDon.getNgayTraXe());
        mtv_GioTraXe_ChiTietHoaDonDatXe.setText(hoaDon.getGioTraXe());
        mtv_TongSoNgayThue_ChiTietHoaDonDatXe.setText(String.valueOf(hoaDon.getTongNgayThue()));
        mtv_TongTien_ChiTietHoaDonDatXe.setText(decimalFormat.format(hoaDon.getTongTien()));
        mtv_GiaTienThue_ChiTietHoaDonDatXe.setText(decimalFormat.format(hoaDon.getGiaTienThueXe()));

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(currentDate);


        NgayKetThucThueXe = mtv_NgayTraXe_ChiTietHoaDonDatXe.getText().toString();
        try {
            Date currentDateFormatted = dateFormat.parse(formattedDate);
            Date ngayKetThucThueXeFormatted = dateFormat.parse(NgayKetThucThueXe);

            if (currentDateFormatted.equals(ngayKetThucThueXeFormatted)) {

                mbtnGiaHan_ChiTietHoaDonDatXe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenDialogGiaHanThueXe(Gravity.CENTER);
                    }
                });
            } else if (currentDateFormatted.compareTo(ngayKetThucThueXeFormatted) > 0) {
                mbtnGiaHan_ChiTietHoaDonDatXe.setEnabled(false);
            } else if (currentDateFormatted.compareTo(ngayKetThucThueXeFormatted) < 0) {
                mbtnGiaHan_ChiTietHoaDonDatXe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OpenDialogGiaHanThueXe(Gravity.CENTER);
                    }
                });
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean isFinishing() {
        return super.isFinishing();
    }


    private void OpenDialogGiaHanThueXe(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_giahan);

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

        Button btnThanhToan_GiaHan = dialog.findViewById(R.id.btnThanhToan_GiaHan);
        Button btnHuy_GiaHan = dialog.findViewById(R.id.btnHuy_GiaHan);

        Spinner spn_SoNgayGiaHan_GiaHan = dialog.findViewById(R.id.spn_SoNgayGiaHan_GiaHanDialog);
        EditText edt_NgayGiaHan_GiaHan = dialog.findViewById(R.id.edt_NgayGiaHan_GiaHanDialog);
        TextView tv_TongTien_GiaHanDiaLog = dialog.findViewById(R.id.tv_TongTien_GiaHanDiaLog);
        tv_TongTien_GiaHanDiaLog.setText("0");

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = dateFormat.format(currentDate);
        edt_NgayGiaHan_GiaHan.setText(formattedDate);
        edt_NgayGiaHan_GiaHan.setEnabled(false);
        ArrayList<String> soNgay = new ArrayList<String>();
        soNgay.add("Vui lòng chọn số ngày");
        soNgay.add("1");
        soNgay.add("2");
        soNgay.add("3");
        soNgay.add("4");
        soNgay.add("5");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, soNgay);
        spn_SoNgayGiaHan_GiaHan.setAdapter(arrayAdapter);

        spn_SoNgayGiaHan_GiaHan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        SoNgayGiaHan = "Vui lòng chọn số ngày gia hạn";
                        break;
                    case 1:
                        SoNgayGiaHan = "1";
                        break;
                    case 2:
                        SoNgayGiaHan = "2";
                        break;
                    case 3:
                        SoNgayGiaHan = "3";
                        break;
                    case 4:
                        SoNgayGiaHan = "4";
                        break;
                    case 5:
                        SoNgayGiaHan = "5";
                        break;
                }
                if (position != 0) {
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setGroupingSeparator('.');
                    symbols.setDecimalSeparator(',');

                    DecimalFormat decimalFormat = new DecimalFormat("#,###,###", symbols);

                    GiaTienThue = Float.parseFloat(mtv_GiaTienThue_ChiTietHoaDonDatXe.getText().toString().replace(".", ""));
                    tongTien = Long.parseLong(SoNgayGiaHan) * (long) GiaTienThue;
                    tv_TongTien_GiaHanDiaLog.setText(decimalFormat.format(tongTien));
                } else {
                    tv_TongTien_GiaHanDiaLog.setText("0");

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnThanhToan_GiaHan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonGiaHan donGiaHan = layDuLieuNguoiDung();
                if (SoNgayGiaHan.equals("Vui lòng chọn số ngày gia hạn")){
                    Toast.makeText(ChiTietHoaDonDatXeActivity.this, "Vui lòng chọn số ngày gia hạn!", Toast.LENGTH_SHORT).show();
                } else {
                    if (donGiaHan != null){
                        if (databaseDonGiaHan.ThemDonDatXe(donGiaHan) != -1){
                            ThongBao thongBao = new ThongBao();
                            thongBao.setIdCus(UserSession.userId);
                            DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
                            String formattedTongTien = decimalFormat.format(tongTien);
                            thongBao.setTieuDe("Gia hạn xe thành công");
                            thongBao.setNoiDung(" - Gia hạn thêm: " + SoNgayGiaHan + " ngày" + "\n - Ngày gia hạn: " + formattedDate + "\n - tổng tiền: " + formattedTongTien + "đ");
                            if (databaseThongBao.ThemThongBao(thongBao) != -1){
                                OpenDialogThongBaoGiaHanThueXe(Gravity.CENTER);
                                Intent intent = new Intent("cap_nhat_danh_sach_thong_bao");
                                sendBroadcast(intent);
                            }
                        } else {
                            Toast.makeText(ChiTietHoaDonDatXeActivity.this, "1", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChiTietHoaDonDatXeActivity.this, "2", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        btnHuy_GiaHan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public DonGiaHan layDuLieuNguoiDung() {

        DonGiaHan donGiaHan = new DonGiaHan();
        donGiaHan.setId(id);
        donGiaHan.setIdHoaDon(HoaDonSession.id);
        donGiaHan.setNgayBatDauGiaHan(HoaDonSession.NgayBatDauGiaHan);

        if (SoNgayGiaHan.equals("Vui lòng chọn số ngày gia hạn")) {
            // Hiển thị thông báo lỗi nếu người dùng chưa chọn số ngày gia hạn
            Toast.makeText(ChiTietHoaDonDatXeActivity.this, "Vui lòng chọn số ngày gia hạn!", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            donGiaHan.setTongNgayGiaHan(Integer.parseInt(SoNgayGiaHan));
        }

        donGiaHan.setTongTien(Float.parseFloat(String.valueOf((tongTien))));

        return donGiaHan;


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
        Cursor cursor = databaseDonGiaHan.GetDonGiaHanFromId(HoaDonSession.id);
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
            mRCview_GiaHanDatXe = findViewById(R.id.RCview_GiaHanDatXe);
            layoutManager = new LinearLayoutManager(this);
            mRCview_GiaHanDatXe.setLayoutManager(layoutManager);
            giaHanAdapter = new GiaHanAdapter(ChiTietHoaDonDatXeActivity.this,R.layout.list_giahan, donGiaHanArrayList);
            mRCview_GiaHanDatXe.setAdapter(giaHanAdapter);
            giaHanAdapter.notifyDataSetChanged();
            mRCview_GiaHanDatXe.invalidate();
        }
    }

    private void OpenDialogThongBaoGiaHanThueXe(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_thongbao_giahanxe);

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

        Button btnXacNhan_ThongBaoGiaHanXe = dialog.findViewById(R.id.btnXacNhan_ThongBaoGiaHanXe);



        btnXacNhan_ThongBaoGiaHanXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CustomerActivity.class);
                startActivity(i);
            }
        });
        dialog.show();
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