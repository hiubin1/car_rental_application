package com.example.pjthuexeapp.ActivityAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pjthuexeapp.ActivityCus.DangKyActivity;
import com.example.pjthuexeapp.ActivityCus.MainActivity;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.XeSession;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;





public class ThemXeAdminActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView tvTitleToolbar;

    Button mbtnChonAnhXe_ThemXeAdmin;
    ImageView mimgAnhXe_ThemXeAdmin;

    EditText medt_TenXe_ThemXeAdmin, medt_BienSoXe_ThemXeAdmin, medt_DiaDiemNhanXe_ThemXeAdmin,
    medt_GiaTien_ThemXeAdmin;

    Spinner mspn_LoaiXe_ThemXeAdmin, mspn_SoGhe_ThemXeAdmin, mspn_TruyenDong_ThemXeAdmin,
    mspn_NhienLieu_ThemXeAdmin, mspn_NangLuongTieuHao_ThemXeAdmin, mspn_TrangThai_ThemXeAdmin;

    String Tenxe,BienSoXe,GiaTien, DiaDiemNhanXe,LoaiXe,SoGhe,TruyenDong,NhienLieu,NangLuong,Trangthai;



    private long id = -1;

    int REQUEST_CODE_FOLDER = 456;

    byte[] HinhAnh;

    Database_Xe databaseXe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_xe_admin);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);

        mbtnChonAnhXe_ThemXeAdmin = findViewById(R.id.btnChonAnhXe_ThemXeAdmin);
        mimgAnhXe_ThemXeAdmin = findViewById(R.id.imgAnhXe_ThemXeAdmin);

        medt_TenXe_ThemXeAdmin = findViewById(R.id.edt_TenXe_ThemXeAdmin);
        medt_BienSoXe_ThemXeAdmin = findViewById(R.id.edt_BienSoXe_ThemXeAdmin);
        medt_DiaDiemNhanXe_ThemXeAdmin = findViewById(R.id.edt_DiaDiemNhanXe_ThemXeAdmin);
        medt_GiaTien_ThemXeAdmin = findViewById(R.id.edt_GiaTien_ThemXeAdmin);

        mspn_LoaiXe_ThemXeAdmin = findViewById(R.id.spn_LoaiXe_ThemXeAdmin);
        mspn_SoGhe_ThemXeAdmin = findViewById(R.id.spn_SoGhe_ThemXeAdmin);
        mspn_TruyenDong_ThemXeAdmin = findViewById(R.id.spn_TruyenDong_ThemXeAdmin);
        mspn_NhienLieu_ThemXeAdmin = findViewById(R.id.spn_NhienLieu_ThemXeAdmin);
        mspn_NangLuongTieuHao_ThemXeAdmin = findViewById(R.id.spn_NangLuongTieuHao_ThemXeAdmin);
        mspn_TrangThai_ThemXeAdmin = findViewById(R.id.spn_TrangThai_ThemXeAdmin);

        databaseXe = new Database_Xe(this);

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Thêm xe");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mbtnChonAnhXe_ThemXeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,REQUEST_CODE_FOLDER);
            }
        });

        ArrayList<String> dsLoaiXe = new ArrayList<String>();
        dsLoaiXe.add("Vui lòng chọn loại xe");
        dsLoaiXe.add("TOYOTA");
        dsLoaiXe.add("HONDA");
        dsLoaiXe.add("VINFAST");
        dsLoaiXe.add("KIA");
        dsLoaiXe.add("MORRIS GARAGES");
        ArrayAdapter adapLoaiXe = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsLoaiXe);
        mspn_LoaiXe_ThemXeAdmin.setAdapter(adapLoaiXe);

        mspn_LoaiXe_ThemXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        LoaiXe = "Vui lòng chọn loại xe";
                        break;
                    case 1:
                        LoaiXe = "TOYOTA";
                        break;
                    case 2:
                        LoaiXe = "HONDA";
                        break;
                    case 3:
                        LoaiXe = "VINFAST";
                        break;
                    case 4:
                        LoaiXe = "KIA";
                        break;
                    case 5:
                        LoaiXe = "MORRIS GARAGES";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsSoGhe = new ArrayList<String>();
        dsSoGhe.add("Vui lòng chọn số ghế");
        dsSoGhe.add("4 chỗ");
        dsSoGhe.add("8 chỗ");
        dsSoGhe.add("16 chỗ");
        dsSoGhe.add("30 chỗ");
        dsSoGhe.add("45 chỗ");
        ArrayAdapter adapSoGhe = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsSoGhe);
        mspn_SoGhe_ThemXeAdmin.setAdapter(adapSoGhe);

        mspn_SoGhe_ThemXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        SoGhe = "Vui lòng chọn số ghế";
                        break;
                    case 1:
                        SoGhe = "4 chỗ";
                        break;
                    case 2:
                        SoGhe = "8 chỗ";
                        break;
                    case 3:
                        SoGhe = "16 chỗ";
                        break;
                    case 4:
                        SoGhe = "30 chỗ";
                        break;
                    case 5:
                        SoGhe = "45 chỗ";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsTruyenDong = new ArrayList<String>();
        dsTruyenDong.add("Vui lòng chọn loại truyền động");
        dsTruyenDong.add("Số tự động");
        dsTruyenDong.add("Số sàn");
        ArrayAdapter adapTruyenDong = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsTruyenDong);
        mspn_TruyenDong_ThemXeAdmin.setAdapter(adapTruyenDong);

        mspn_TruyenDong_ThemXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        TruyenDong = "Vui lòng chọn loại truyền động";
                        break;
                    case 1:
                        TruyenDong = "Số tự động";
                        break;
                    case 2:
                        TruyenDong = "Số sàn";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsNhienLieu = new ArrayList<String>();
        dsNhienLieu.add("Vui lòng chọn nhiên liệu");
        dsNhienLieu.add("Xăng");
        dsNhienLieu.add("Dầu Diesel");
        ArrayAdapter adapNhienLieu = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsNhienLieu);
        mspn_NhienLieu_ThemXeAdmin.setAdapter(adapNhienLieu);

        mspn_NhienLieu_ThemXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        NhienLieu = "Vui lòng chọn nhiên liệu";
                        break;
                    case 1:
                        NhienLieu = "Xăng";
                        break;
                    case 2:
                        NhienLieu = "Dầu Diesel";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsNangLuongTieuHao = new ArrayList<String>();
        dsNangLuongTieuHao.add("Vui lòng chọn năng lượng tiêu hao");
        dsNangLuongTieuHao.add("6 lít");
        dsNangLuongTieuHao.add("7 lít");
        dsNangLuongTieuHao.add("8 lít");
        dsNangLuongTieuHao.add("9 lít");
        ArrayAdapter adapNangLuong = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsNangLuongTieuHao);
        mspn_NangLuongTieuHao_ThemXeAdmin.setAdapter(adapNangLuong);

        mspn_NangLuongTieuHao_ThemXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        NangLuong = "Vui lòng chọn năng lượng tiêu hao";
                        break;
                    case 1:
                        NangLuong = "6 lít";
                        break;
                    case 2:
                        NangLuong = "7 lít";
                        break;
                    case 3:
                        NangLuong = "8 lít";
                        break;
                    case 4:
                        NangLuong = "9 lít";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsTrangThai = new ArrayList<String>();
        dsTrangThai.add("Vui lòng chọn trạng thái");
        dsTrangThai.add("Còn xe");
        ArrayAdapter adapTrangthai = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsTrangThai);
        mspn_TrangThai_ThemXeAdmin.setAdapter(adapTrangthai);

        mspn_TrangThai_ThemXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Trangthai = "Vui lòng chọn trạng thái";
                        break;
                    case 1:
                        Trangthai = "Còn xe";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mimgAnhXe_ThemXeAdmin.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
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

    public Xe layDuLieuNguoiDung(){
        Tenxe = medt_TenXe_ThemXeAdmin.getText().toString();
        BienSoXe = medt_BienSoXe_ThemXeAdmin.getText().toString();
        DiaDiemNhanXe = medt_DiaDiemNhanXe_ThemXeAdmin.getText().toString();
        GiaTien = medt_GiaTien_ThemXeAdmin.getText().toString();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) mimgAnhXe_ThemXeAdmin.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        HinhAnh = byteArrayOutputStream.toByteArray();

        Xe xe = new Xe();
        xe.setId(id);
        xe.setTenXe(Tenxe);
        xe.setBienSoXe(BienSoXe);
        xe.setLoaiXe(LoaiXe);
        xe.setNhienLieu(NhienLieu);
        xe.setHinhXe(HinhAnh);
        xe.setDiaDiemNhanXe(DiaDiemNhanXe);
        xe.setNangLuongTieuHao(NangLuong);
        xe.setTruyenDong(TruyenDong);
        xe.setTrangThai(Trangthai);

        if (!GiaTien.isEmpty()) {
            try {
                xe.setGiaTien(Integer.parseInt(GiaTien));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Xử lý lỗi chuyển đổi không thành công (nếu cần thiết)
            }
        }

        xe.setSoGhe(SoGhe);
        return xe;
    }


    public void btnThemXe_Admin_onClick(View view) {
        String giatien = medt_GiaTien_ThemXeAdmin.getText().toString();
        Xe xe = layDuLieuNguoiDung();
        boolean KiemTraDKXe = databaseXe.KiemTraBienSoXe(BienSoXe);
        if(Tenxe.isEmpty() || LoaiXe.equals("Vui lòng chọn loại xe") || BienSoXe.isEmpty()
                || NhienLieu.equals("Vui lòng chọn nhiên liệu") || DiaDiemNhanXe.isEmpty() || NangLuong.equals("Vui lòng chọn năng lượng tiêu hao")
                || TruyenDong.equals("Vui lòng chọn loại truyền động") || Trangthai.equals("Vui lòng chọn trạng thái")
                || TextUtils.isEmpty(giatien)|| SoGhe.equals("Vui lòng chọn số ghế") || HinhAnh.length < 0 || HinhAnh == null){
            Toast.makeText(ThemXeAdminActivity.this,"Vui lòng nhập đầy đủ thông tin xe", Toast.LENGTH_SHORT).show();

        } else {
            if (xe != null){
                if (KiemTraDKXe){
                    Toast.makeText(ThemXeAdminActivity.this,"Biển số xe đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    if (databaseXe.ThemXe(xe) != -1){
                        id = -1;
                        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }
    }
}