package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;

import java.util.ArrayList;

public class ThongTinCaNhanActivity extends AppCompatActivity {
    Toolbar toolbar;

    TextView tvTitleToolbar;

    EditText medtSDT_ThongTinCaNhan_cus, medtHoTen_ThongTinCaNhan_cus, medtCCCD_ThongTinCaNhan_cus;

    Spinner mspnGPLX_ThongTinCaNhan_cus;

    TextView mtvGPLX_ThongTinCaNhan_cus;

    String HoTen, SDT, CCCD, GPLX;

    Database_User database_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        toolbar = findViewById(R.id.Toolbar_app);
        tvTitleToolbar = findViewById(R.id.toolbar_title);

        medtHoTen_ThongTinCaNhan_cus = findViewById(R.id.edtHoTen_ThongTinCaNhan_cus);
        medtSDT_ThongTinCaNhan_cus = findViewById(R.id.edtSDT_ThongTinCaNhan_cus);
        medtCCCD_ThongTinCaNhan_cus = findViewById(R.id.edtCCCD_ThongTinCaNhan_cus);
        mspnGPLX_ThongTinCaNhan_cus = findViewById(R.id.spnGPLX_ThongTinCaNhan_cus);
        mtvGPLX_ThongTinCaNhan_cus = findViewById(R.id.tvGPLX_ThongTinCaNhan_cus);
        database_user = new Database_User(this);
        AppCompatActivity activity = (AppCompatActivity) this;
        activity.setSupportActionBar(toolbar);
        tvTitleToolbar.setText("Thông tin cá nhân");
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetUserInfo();
        ArrayList<String> gplx = new ArrayList<String>();
        gplx.add("Có");
        gplx.add("Không");
        ArrayAdapter adapterGPLX = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gplx);
        mspnGPLX_ThongTinCaNhan_cus.setAdapter(adapterGPLX);
        int positon = adapterGPLX.getPosition(UserSession.GPLX);
        mspnGPLX_ThongTinCaNhan_cus.setSelection(positon);
        mspnGPLX_ThongTinCaNhan_cus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        GPLX = "Có";
                        break;
                    case 1:
                        GPLX = "Không";
                        break;
                }
                mtvGPLX_ThongTinCaNhan_cus.setText(GPLX);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    public boolean KiemTraDK() {
        boolean isValid = true;
        if (HoTen.length() < 6){
            Toast.makeText(ThongTinCaNhanActivity.this,"Họ tên phải 6 ký tự trở lên",Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (SDT.length() < 10 || SDT.length() > 10 ) {
            Toast.makeText(ThongTinCaNhanActivity.this,"Số điện thoại không đúng",Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (CCCD.length() < 12 || CCCD.length() > 12){
            Toast.makeText(ThongTinCaNhanActivity.this,"Căn cước công dân không đúng",Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }


    public void btnChinhSua_ThonTinCaNhan_Cus_onclick(View view) {
        OpenDialogThongBaoChinhSua(Gravity.CENTER);
    }

    @SuppressLint("Range")
    public void SetUserInfo(){
        Cursor cursor = database_user.GetUserFromSdt(UserSession.userPhoneCurrent);
        if (cursor != null){
            while (cursor.moveToNext()){
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_USER)));
                nguoiDung.setHoTen(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN_USER)));
                nguoiDung.setSDT(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SDT_USER)));
                nguoiDung.setCCCD(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CCCD_USER)));
                nguoiDung.setGPLX(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GPLX_USER)));

                UserSession.CCCD = nguoiDung.getCCCD();
                UserSession.GPLX = nguoiDung.getGPLX();
                medtHoTen_ThongTinCaNhan_cus.setText(nguoiDung.getHoTen());
                medtCCCD_ThongTinCaNhan_cus.setText(nguoiDung.getCCCD());
                medtSDT_ThongTinCaNhan_cus.setText(nguoiDung.getSDT());
                mtvGPLX_ThongTinCaNhan_cus.setText(nguoiDung.getGPLX());
            }
        }
    }

    public NguoiDung layDuLieuNguoiDung(){
        HoTen = medtHoTen_ThongTinCaNhan_cus.getText().toString();
        SDT = medtSDT_ThongTinCaNhan_cus.getText().toString();
        CCCD = medtCCCD_ThongTinCaNhan_cus.getText().toString();
        GPLX = mtvGPLX_ThongTinCaNhan_cus.getText().toString();

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(UserSession.userId);
        nguoiDung.setHoTen(HoTen);
        nguoiDung.setSDT(SDT);
        nguoiDung.setGPLX(GPLX);
        nguoiDung.setCCCD(CCCD);
        return nguoiDung;
    }
    private void OpenDialogThongBaoChinhSua(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_thongbao_chinhsua);

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

        Button btnCo_ChinhSua = dialog.findViewById(R.id.btnCo_ChinhSua);
        Button btnKhong_ChinhSua = dialog.findViewById(R.id.btnKhong_ChinhSua);


        btnCo_ChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguoiDung nguoiDung = layDuLieuNguoiDung();
                if (nguoiDung != null){
                    if (nguoiDung != null){
                        if (KiemTraDK()){
                            if (database_user.SuaUser(nguoiDung) != -1){
                                Toast.makeText(ThongTinCaNhanActivity.this,"Thông tin của bạn đã được thay đổi thành công",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }
                }
            }
        });

        btnKhong_ChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}