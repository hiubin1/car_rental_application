package com.example.pjthuexeapp.ActivityAdmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;
import com.example.pjthuexeapp.Session.XeSession;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ChinhSuaXeAdminActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView tvTitleToolbar;

    ImageView mimgAnhXe_ChinhXeAdmin;

    TextView mtv_LoaiXe_ChinhSuaXeAdmin, mtv_SoGhe_ChinhSuaXeAdmin, mtv_TruyenDong_ChinhSuaXeAdmin,
    mtv_NhienLieu_ChinhSuaXeAdmin, mtv_NangLuongTieuHao_ChinhSuaXeAdmin,mtv_Trangthai_ChinhSuaXeAdmin;

    EditText medt_TenXe_ChinhSuaXeAdmin, medt_BienSoXe_ChinhSuaXeAdmin, medt_DiaDiemNhanXe_ChinhSuaXeAdmin
            ,medt_GiaTien_ChinhSuaXeAdmin;

    Spinner mspn_LoaiXe_ChinhSuaXeAdmin, mspn_SoGhe_ChinhSuaXeAdmin, mspn_TruyenDong_ChinhSuaXeAdmin
            ,mspn_NhienLieu_ChinhSuaXeAdmin, mspn_NangLuongTieuHao_ChinhSuaXeAdmin, mspn_Trangthai_ChinhSuaXeAdmin;

    int SELECT_IMAGE_CODE = 1;

    String TenXe, BienSoXe, DiaDiemNhanXe, GiaTien, LoaiXe,SoGhe,TruyenDong,NhienLieu,NangLuong,Trangthai;

    ArrayList<Xe> xeArrayList;


    Database_Xe databaseXe;

    Xe xe;

    int REQUEST_CODE_FOLDER = 456;

    Xe xoa = new Xe();

    long id = -1;

    long ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_xe_admin);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);

        mimgAnhXe_ChinhXeAdmin = findViewById(R.id.imgAnhXe_ChinhXeAdmin);

        mspn_LoaiXe_ChinhSuaXeAdmin = findViewById(R.id.spn_LoaiXe_ChinhSuaXeAdmin);
        mspn_SoGhe_ChinhSuaXeAdmin = findViewById(R.id.spn_SoGhe_ChinhSuaXeAdmin);
        mspn_TruyenDong_ChinhSuaXeAdmin = findViewById(R.id.spn_TruyenDong_ChinhSuaXeAdmin);
        mspn_NhienLieu_ChinhSuaXeAdmin = findViewById(R.id.spn_NhienLieu_ChinhSuaXeAdmin);
        mspn_NangLuongTieuHao_ChinhSuaXeAdmin = findViewById(R.id.spn_NangLuongTieuHao_ChinhSuaXeAdmin);
        mspn_Trangthai_ChinhSuaXeAdmin = findViewById(R.id.spn_Trangthai_ChinhSuaXeAdmin);

        medt_TenXe_ChinhSuaXeAdmin = findViewById(R.id.edt_TenXe_ChinhSuaXeAdmin);
        medt_BienSoXe_ChinhSuaXeAdmin = findViewById(R.id.edt_BienSoXe_ChinhSuaXeAdmin);
        medt_DiaDiemNhanXe_ChinhSuaXeAdmin = findViewById(R.id.edt_DiaDiemNhanXe_ChinhSuaXeAdmin);
        medt_GiaTien_ChinhSuaXeAdmin = findViewById(R.id.edt_GiaTien_ChinhSuaXeAdmin);

        mtv_LoaiXe_ChinhSuaXeAdmin = findViewById(R.id.tv_LoaiXe_ChinhSuaXeAdmin);
        mtv_SoGhe_ChinhSuaXeAdmin = findViewById(R.id.tv_SoGhe_ChinhSuaXeAdmin);
        mtv_TruyenDong_ChinhSuaXeAdmin = findViewById(R.id.tv_TruyenDong_ChinhSuaXeAdmin);
        mtv_NhienLieu_ChinhSuaXeAdmin = findViewById(R.id.tv_NhienLieu_ChinhSuaXeAdmin);
        mtv_NangLuongTieuHao_ChinhSuaXeAdmin = findViewById(R.id.tv_NangLuongTieuHao_ChinhSuaXeAdmin);
        mtv_Trangthai_ChinhSuaXeAdmin = findViewById(R.id.tv_Trangthai_ChinhSuaXeAdmin);

        xe = new Xe();

        databaseXe = new Database_Xe(this);
        capNhatDuLieu();

        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Chỉnh sửa xe");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<String> dsLoaiXe = new ArrayList<String>();
        dsLoaiXe.add("TOYOTA");
        dsLoaiXe.add("HONDA");
        dsLoaiXe.add("VINFAST");
        dsLoaiXe.add("KIA");
        dsLoaiXe.add("MORRIS GARAGES");
        ArrayAdapter adapLoaiXe = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsLoaiXe);
        mspn_LoaiXe_ChinhSuaXeAdmin.setAdapter(adapLoaiXe);
        int positon = adapLoaiXe.getPosition(XeSession.LoaiXe);
        mspn_LoaiXe_ChinhSuaXeAdmin.setSelection(positon);
        mspn_LoaiXe_ChinhSuaXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        LoaiXe = "TOYOTA";
                        break;
                    case 1:
                        LoaiXe = "HONDA";
                        break;
                    case 2:
                        LoaiXe = "VINFAST";
                        break;
                    case 3:
                        LoaiXe = "KIA";
                        break;
                    case 4:
                        LoaiXe = "MORRIS GARAGES";
                        break;
                }
                mtv_LoaiXe_ChinhSuaXeAdmin.setText(LoaiXe);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsSoGhe = new ArrayList<String>();
        dsSoGhe.add("4 chỗ");
        dsSoGhe.add("8 chỗ");
        dsSoGhe.add("16 chỗ");
        dsSoGhe.add("30 chỗ");
        dsSoGhe.add("45 chỗ");
        ArrayAdapter adapSoGhe = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsSoGhe);
        mspn_SoGhe_ChinhSuaXeAdmin.setAdapter(adapSoGhe);
        int positon1 = adapSoGhe.getPosition(XeSession.SoGhe);
        mspn_SoGhe_ChinhSuaXeAdmin.setSelection(positon1);
        mspn_SoGhe_ChinhSuaXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        SoGhe = "4 chỗ";
                        break;
                    case 1:
                        SoGhe = "8 chỗ";
                        break;
                    case 2:
                        SoGhe = "16 chỗ";
                        break;
                    case 3:
                        SoGhe = "30 chỗ";
                        break;
                    case 4:
                        SoGhe = "45 chỗ";
                        break;
                }
                mtv_SoGhe_ChinhSuaXeAdmin.setText(SoGhe);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsTruyenDong = new ArrayList<String>();
        dsTruyenDong.add("Số tự động");
        dsTruyenDong.add("Số sàn");
        ArrayAdapter adapTruyenDong = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsTruyenDong);
        mspn_TruyenDong_ChinhSuaXeAdmin.setAdapter(adapTruyenDong);
        int positon2 = adapTruyenDong.getPosition(XeSession.TruyenDong);
        mspn_TruyenDong_ChinhSuaXeAdmin.setSelection(positon2);
        mspn_TruyenDong_ChinhSuaXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        TruyenDong = "Số tự động";
                        break;
                    case 1:
                        TruyenDong = "Số sàn";
                        break;
                }
                mtv_TruyenDong_ChinhSuaXeAdmin.setText(TruyenDong);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsNhienLieu = new ArrayList<String>();
        dsNhienLieu.add("Xăng");
        dsNhienLieu.add("Dầu Diesel");
        ArrayAdapter adapNhienLieu = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsNhienLieu);
        mspn_NhienLieu_ChinhSuaXeAdmin.setAdapter(adapNhienLieu);
        int positon3 = adapNhienLieu.getPosition(XeSession.NhienLieu);
        mspn_NhienLieu_ChinhSuaXeAdmin.setSelection(positon3);
        mspn_NhienLieu_ChinhSuaXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        NhienLieu = "Xăng";
                        break;
                    case 1:
                        NhienLieu = "Dầu Diesel";
                        break;
                }
                mtv_NhienLieu_ChinhSuaXeAdmin.setText(NhienLieu);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsNangLuongTieuHao = new ArrayList<String>();
        dsNangLuongTieuHao.add("6 lít");
        dsNangLuongTieuHao.add("7 lít");
        dsNangLuongTieuHao.add("8 lít");
        dsNangLuongTieuHao.add("9 lít");
        ArrayAdapter adapNangLuong = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsNangLuongTieuHao);
        mspn_NangLuongTieuHao_ChinhSuaXeAdmin.setAdapter(adapNangLuong);
        int positon4 = adapNangLuong.getPosition(XeSession.NangLuong);
        mspn_NangLuongTieuHao_ChinhSuaXeAdmin.setSelection(positon4);
        mspn_NangLuongTieuHao_ChinhSuaXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        NangLuong = "6 lít";
                        break;
                    case 1:
                        NangLuong = "7 lít";
                        break;
                    case 2:
                        NangLuong = "8 lít";
                        break;
                    case 3:
                        NangLuong = "9 lít";
                        break;
                }
                mtv_NangLuongTieuHao_ChinhSuaXeAdmin.setText(NangLuong);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> dsTrangThai = new ArrayList<String>();
        dsTrangThai.add("Còn xe");
        dsTrangThai.add("Hết xe");
        ArrayAdapter adapTrangthai = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsTrangThai);
        mspn_Trangthai_ChinhSuaXeAdmin.setAdapter(adapTrangthai);
        int positon5 = adapTrangthai.getPosition(XeSession.TrangThai);
        mspn_Trangthai_ChinhSuaXeAdmin.setSelection(positon5);
        mspn_Trangthai_ChinhSuaXeAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Trangthai = "Còn xe";
                        break;
                    case 1:
                        Trangthai = "Hết xe";
                        break;
                }
                mtv_Trangthai_ChinhSuaXeAdmin.setText(Trangthai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent i = getIntent();
        Bundle b = i.getExtras();
        id = b.getLong("ID");


    }

    @Override
    protected void onResume() {
        super.onResume();
        capNhatDuLieu();
    }

    public Xe layDuLieuNguoiDung(){
        TenXe = medt_TenXe_ChinhSuaXeAdmin.getText().toString();
        GiaTien = medt_GiaTien_ChinhSuaXeAdmin.getText().toString();
        DiaDiemNhanXe = medt_DiaDiemNhanXe_ChinhSuaXeAdmin.getText().toString();
        BienSoXe = medt_BienSoXe_ChinhSuaXeAdmin.getText().toString();
        LoaiXe = mtv_LoaiXe_ChinhSuaXeAdmin.getText().toString();
        TruyenDong = mtv_TruyenDong_ChinhSuaXeAdmin.getText().toString();
        NhienLieu = mtv_NhienLieu_ChinhSuaXeAdmin.getText().toString();
        NangLuong = mtv_NangLuongTieuHao_ChinhSuaXeAdmin.getText().toString();
        SoGhe = mtv_SoGhe_ChinhSuaXeAdmin.getText().toString();
        Trangthai = mtv_Trangthai_ChinhSuaXeAdmin.getText().toString();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) mimgAnhXe_ChinhXeAdmin.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] HinhAnh = byteArrayOutputStream.toByteArray();



        Xe xe = new Xe();
        xe.setId(id);
        xe.setLoaiXe(LoaiXe);
        xe.setTenXe(TenXe);
        xe.setGiaTien(Integer.parseInt(GiaTien));
        xe.setBienSoXe(BienSoXe);
        xe.setTruyenDong(TruyenDong);
        xe.setNhienLieu(NhienLieu);
        xe.setNangLuongTieuHao(NangLuong);
        xe.setSoGhe(SoGhe);
        xe.setHinhXe(HinhAnh);
        xe.setTrangThai(Trangthai);
        xe.setDiaDiemNhanXe(DiaDiemNhanXe);

        return xe;
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if(xeArrayList == null){
            xeArrayList = new ArrayList<Xe>();
        } else {
            xeArrayList.removeAll(xeArrayList);
        }

        Cursor cursor = databaseXe.GetXeFromId(id);
        if(cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()){
            do {
                int idXe = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_XE));
                if (idXe == id){
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


                    medt_TenXe_ChinhSuaXeAdmin.setText(xe.getTenXe());

                    medt_BienSoXe_ChinhSuaXeAdmin.setText(xe.getBienSoXe());

                    medt_GiaTien_ChinhSuaXeAdmin.setText(String.valueOf(xe.getGiaTien()));

                    medt_DiaDiemNhanXe_ChinhSuaXeAdmin.setText(xe.getDiaDiemNhanXe());

                    mtv_LoaiXe_ChinhSuaXeAdmin.setText(xe.getLoaiXe());

                    mtv_NhienLieu_ChinhSuaXeAdmin.setText(xe.getNhienLieu());

                    mtv_SoGhe_ChinhSuaXeAdmin.setText(xe.getSoGhe());

                    mtv_TruyenDong_ChinhSuaXeAdmin.setText(xe.getTruyenDong());

                    mtv_NangLuongTieuHao_ChinhSuaXeAdmin.setText(xe.getNangLuongTieuHao());

//                    byte[] hinhXeBytes = xe.getHinhXe();
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinhXeBytes, 0, hinhXeBytes.length);
//                    mimgAnhXe_ChinhXeAdmin.setImageBitmap(bitmap);

                    mtv_Trangthai_ChinhSuaXeAdmin.setText(xe.getTrangThai());

                    xoa.setId(xe.getId());

                    xeArrayList.add(xe);

                    break;
                }
            } while (cursor.moveToNext());
        }
       cursor.close();
    }

    public void btnChonAnhXe_ChinhSuaXeAdmin(View view) {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,REQUEST_CODE_FOLDER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


                mimgAnhXe_ChinhXeAdmin.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void btnChinhSuaXe_ChinhSuaXeAdmin(View view) {
        Xe xe = layDuLieuNguoiDung();
        if (xe != null){
            if (xe != null && ID != -1){
                databaseXe.SuaXe(xe);
                Toast.makeText(ChinhSuaXeAdminActivity.this,"Chỉnh sửa thông tin thành công",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void btnXoaXe_ChinhSuaXeAdmin(View view) {
       OpenDialogThongBaoXoaXe(Gravity.CENTER);
    }

    private void OpenDialogThongBaoXoaXe(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_thongbao_xoaxe);

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

        Button btnCo_XoaXe = dialog.findViewById(R.id.btnCo_XoaXe);
        Button btnKhong_XoaXe = dialog.findViewById(R.id.btnKhong_XoaXe);

        btnCo_XoaXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseXe.XoaXe(xoa);
                Toast.makeText(ChinhSuaXeAdminActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        btnKhong_XoaXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

}