package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_DonDatXe;
import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;
import com.example.pjthuexeapp.Session.XeSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ChonLichThueXeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Toolbar toolbar;

    TextView tvTitleToolbar;

    ImageButton mimgbtn_ChonNgayNhanXe_ChonLichThueXe, mimgbtn_ChonNgayTraXe_ChonLichThueXe;

    ImageView mimg_HinhXe_ChonLichThueXe;

    TextView mtvNgayNhanXe_ChonLichThueXe, mtvNgayTraXe_ChonLichThueXe;

    TextView mtvGioNhanXe_ChonLichThueXe, mtvGioTraXe_ChonLichThueXe;

    TextView mtv_TenXe_ChonLichThueXe, mtv_GiaTien_ChonLichThueXe, mtv_BienSoXe_ChonLichThueXe, mtv_TruyenDong_ChonLichThueXe, mtv_SoGhe_ChonLichThueXe;


    EditText medt_HoTenUser_ChonLichThueXe, medt_DiaDiemNhanXe_ChonLichThueXe, medt_DiaDiemTraXe_ChonLichThueXe;

    int GioNhan, PhutNhan;

    Database_User database_user;

    Database_Xe databaseXe;


    String TenXe, GiaTien, BienSoXe, HoTen, DiaDiemNhanXe, DiaDiemTraXe, NgayNhanXe, NgayTraXe, GioNhanXe, GioTraXe, SoGhe, TruyenDong;


    byte[] anhXeBytes;

    private boolean isNgayNhanXeSelected = false;

    Database_DonDatXe databaseDonDatXe;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_lich_thue_xe);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);
        mimgbtn_ChonNgayNhanXe_ChonLichThueXe = findViewById(R.id.imgbtn_ChonNgayNhanXe_ChonLichThueXe);
        mimgbtn_ChonNgayTraXe_ChonLichThueXe = findViewById(R.id.imgbtn_ChonNgayTraXe_ChonLichThueXe);
        mtvNgayNhanXe_ChonLichThueXe = findViewById(R.id.tvNgayNhanXe_ChonLichThueXe);
        mtvNgayTraXe_ChonLichThueXe = findViewById(R.id.tvNgayTraXe_ChonLichThueXe);
        mtvGioNhanXe_ChonLichThueXe = findViewById(R.id.tvGioNhanXe_ChonLichThueXe);
        mtvGioTraXe_ChonLichThueXe = findViewById(R.id.tvGioTraXe_ChonLichThueXe);
        mtv_TenXe_ChonLichThueXe = findViewById(R.id.tv_TenXe_ChonLichThueXe);
        mtv_TruyenDong_ChonLichThueXe = findViewById(R.id.tv_TruyenDong_ChonLichThueXe);
        mtv_SoGhe_ChonLichThueXe = findViewById(R.id.tv_SoGhe_ChonLichThueXe);
        database_user = new Database_User(this);
        databaseXe = new Database_Xe(this);
        databaseDonDatXe = new Database_DonDatXe(this);

        mimg_HinhXe_ChonLichThueXe = findViewById(R.id.img_HinhXe_ChonLichThueXe);
        mtv_BienSoXe_ChonLichThueXe = findViewById(R.id.tv_BienSoXe_ChonLichThueXe);
        mtv_GiaTien_ChonLichThueXe = findViewById(R.id.tv_GiaTien_ChonLichThueXe);

        medt_HoTenUser_ChonLichThueXe = findViewById(R.id.edt_HoTenUser_ChonLichThueXe);
        medt_DiaDiemNhanXe_ChonLichThueXe = findViewById(R.id.edt_DiaDiemNhanXe_ChonLichThueXe);
        medt_DiaDiemTraXe_ChonLichThueXe = findViewById(R.id.edt_DiaDiemTraXe_ChonLichThueXe);

        SetUserInfo();
        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Chọn lịch thuê xe");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        mtv_TenXe_ChonLichThueXe.setText(bundle.getString("TenXe"));
        anhXeBytes = bundle.getByteArray("AnhXe");
        Bitmap anhXeBitmap = BitmapFactory.decodeByteArray(anhXeBytes, 0, anhXeBytes.length);
        mimg_HinhXe_ChonLichThueXe.setImageBitmap(anhXeBitmap);
        mtv_BienSoXe_ChonLichThueXe.setText(bundle.getString("BienSoXe"));
        mtv_GiaTien_ChonLichThueXe.setText(bundle.getString("GiaTien"));
        medt_DiaDiemNhanXe_ChonLichThueXe.setText(bundle.getString("DiaDiemNhanXe"));
        mtv_SoGhe_ChonLichThueXe.setText(bundle.getString("SoGhe"));
        mtv_TruyenDong_ChonLichThueXe.setText(bundle.getString("TruyenDong"));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar mCalendar = Calendar.getInstance();
        datePicker.setMaxDate(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);

        String dateTime = simpleDateFormat.format(mCalendar.getTime());

        if (isNgayNhanXeSelected) {
            mtvNgayNhanXe_ChonLichThueXe.setText(dateTime);
            NgayNhanXe = mtvNgayNhanXe_ChonLichThueXe.getText().toString();
        } else {
            mtvNgayTraXe_ChonLichThueXe.setText(dateTime);
            NgayTraXe = mtvNgayTraXe_ChonLichThueXe.getText().toString();
        }
    }


    public void openDateNhan_onclick(View view) {
        isNgayNhanXeSelected = true;
        com.example.pjthuexeapp.Fragment.DatePicker mDatePickerDialogFragmentNhan;
        mDatePickerDialogFragmentNhan = new com.example.pjthuexeapp.Fragment.DatePicker();
        mDatePickerDialogFragmentNhan.show(getSupportFragmentManager(), "DATE PICK");
    }

    public void openDateTra_onclick(View view) {
        isNgayNhanXeSelected = false;
        com.example.pjthuexeapp.Fragment.DatePicker2 mDatePickerDialogFragmentTra;
        mDatePickerDialogFragmentTra = new com.example.pjthuexeapp.Fragment.DatePicker2();
        mDatePickerDialogFragmentTra.show(getSupportFragmentManager(), "DATE PICKER");
    }


    @SuppressLint("Range")
    public void SetUserInfo() {
        Cursor cursor = database_user.GetUserFromSdt(UserSession.userPhoneCurrent);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_USER)));
                nguoiDung.setHoTen(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN_USER)));

                medt_HoTenUser_ChonLichThueXe.setText(nguoiDung.getHoTen());
            }
        }
    }


    public void openTimeNhan_onclick(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                ChonLichThueXeActivity.this,
                android.R.style.Theme_Holo_Dialog_MinWidth,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        GioNhan = hourOfDay;
                        PhutNhan = minute;
                        String time = GioNhan + ":" + PhutNhan;
                        SimpleDateFormat format24h = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = format24h.parse(time);
                            SimpleDateFormat format12h = new SimpleDateFormat("hh:mm aa");
                            mtvGioNhanXe_ChonLichThueXe.setText(format12h.format(date));
                            mtvGioTraXe_ChonLichThueXe.setText(format12h.format(date));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, 12, 0, false
        );
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(GioNhan, PhutNhan);
        timePickerDialog.show();
    }

    public void btnTiepTuc_ChonLichXe_onclick(View view) {
        TenXe = mtv_TenXe_ChonLichThueXe.getText().toString();
        GiaTien = mtv_GiaTien_ChonLichThueXe.getText().toString();
        DiaDiemNhanXe = medt_DiaDiemNhanXe_ChonLichThueXe.getText().toString();
        DiaDiemTraXe = medt_DiaDiemTraXe_ChonLichThueXe.getText().toString();
        HoTen = medt_HoTenUser_ChonLichThueXe.getText().toString();
        GioTraXe = mtvGioTraXe_ChonLichThueXe.getText().toString();
        GioNhanXe = mtvGioNhanXe_ChonLichThueXe.getText().toString();
        BienSoXe = mtv_BienSoXe_ChonLichThueXe.getText().toString();
        TruyenDong = mtv_TruyenDong_ChonLichThueXe.getText().toString();
        SoGhe = mtv_SoGhe_ChonLichThueXe.getText().toString();


        if (HoTen.isEmpty() || DiaDiemNhanXe.isEmpty() || DiaDiemTraXe.isEmpty() ||
                NgayNhanXe == null || NgayNhanXe.equals("") || NgayTraXe == null ||
                NgayTraXe.equals("") || GioNhanXe.equals("") || GioTraXe.equals("") ||
                GioNhanXe == null || GioTraXe == null) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return; // Kết thúc phương thức nếu có thông tin bị thiếu
        }

        // Kiểm tra ngày nhận xe không được nhỏ hơn ngày hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date currentDate = Calendar.getInstance().getTime();
            Date startDate = sdf.parse(NgayNhanXe);
            if (startDate.before(currentDate)) {
                Toast.makeText(this, "Ngày nhận xe không hợp lệ", Toast.LENGTH_SHORT).show();
                return; // Kết thúc phương thức nếu ngày nhận xe không hợp lệ
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Kiểm tra ngày trả xe phải lớn hơn ngày nhận xe
        try {
            Date startDate = sdf.parse(NgayNhanXe);
            Date endDate = sdf.parse(NgayTraXe);
            if (startDate.compareTo(endDate) >= 0) {
                Toast.makeText(this, "Ngày trả xe không hợp lệ", Toast.LENGTH_SHORT).show();
                return; // Kết thúc phương thức nếu ngày trả xe không hợp lệ
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long IdXe = XeSession.id;
        boolean KiemTraXe = databaseDonDatXe.KiemTraXe(IdXe, GioNhanXe, GioTraXe, NgayNhanXe, NgayTraXe);
        if (KiemTraXe) {
            OpenDialogThongBaoXeDuocDat(Gravity.CENTER);
            return;
        } else {
            Intent intent = new Intent(getApplicationContext(), HopDongThueXeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("TenXe", TenXe);
            bundle.putString("GiaTien", GiaTien);
            bundle.putString("DiaDiemNhanXe", DiaDiemNhanXe);
            bundle.putString("DiaDiemTraXe", DiaDiemTraXe);
            bundle.putString("HoTen", HoTen);
            bundle.putString("GioTraXe", GioTraXe);
            bundle.putString("GioNhanXe", GioNhanXe);
            bundle.putString("BienSoXe", BienSoXe);
            bundle.putString("TruyenDong", TruyenDong);
            bundle.putString("SoGhe", SoGhe);
            bundle.putString("NgayNhanXe", NgayNhanXe);
            bundle.putString("NgayTraXe", NgayTraXe);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    private void OpenDialogThongBaoXeDuocDat(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_thongbao_dattruocxe);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = gravity;
        window.setAttributes(windowAtrributes);

        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        Button btnHuy_DatTruocXe = dialog.findViewById(R.id.btnHuy_DatTruocXe);


        btnHuy_DatTruocXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
