package com.example.pjthuexeapp.ActivityCus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.R;

public class DangKyActivity extends AppCompatActivity {

    EditText medtSDT_DangKy_cus, medtHoTen_DangKy_cus, medtPW_DangKy_cus, medtRPW_DangKy_cus;

    String Hoten, Sdt, Pass, RPass;

    long id = -1;

    long idUser;

    public static Database_User database_user;

    String Phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        medtHoTen_DangKy_cus = findViewById(R.id.edtHoTen_DangKy_cus);
        medtSDT_DangKy_cus = findViewById(R.id.edtSDT_DangKy_cus);
        medtPW_DangKy_cus = findViewById(R.id.edtPW_DangKy_cus);
        medtRPW_DangKy_cus = findViewById(R.id.edtRPW_DangKy_cus);
        database_user = new Database_User(this);
        medtSDT_DangKy_cus.requestFocus();
    }

    public boolean KiemTraDK() {
        boolean isValid = true;
        if (Hoten.length() < 6){
            Toast.makeText(DangKyActivity.this,"Họ tên phải 6 ký tự trở lên",Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if(Pass.length() < 9){
            Toast.makeText(DangKyActivity.this,"Mật khẩu không an toàn",Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (!Pass.matches(".*[a-zA-Z].*")) {
            Toast.makeText(DangKyActivity.this,"Ít nhất phải có 1 ký tự Hoa",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        // Kiểm tra ký tự số
        if (!Pass.matches(".*\\d.*")) {
            Toast.makeText(DangKyActivity.this,"Ít nhất phải có 1 ký tự số",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        // Kiểm tra ký tự đặc biệt
        if (!Pass.matches(".*[@#$%].*")) {
            Toast.makeText(DangKyActivity.this,"Ít nhất phải có 1 ký đặc biệt",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (!Pass.equals(RPass)){
            Toast.makeText(DangKyActivity.this,"Mật khẩu không trùng nhau",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (Sdt.length() < 10 || Sdt.length() > 10 ) {
            Toast.makeText(DangKyActivity.this,"Số điện thoại không đúng",Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    public void btnDangKy_onclick(View view) {
        NguoiDung nguoiDung = layDuLieuNguoiDung();
        Phone = medtSDT_DangKy_cus.getText().toString();
        boolean KiemTraDKPhone  = database_user.KiemTraSDT(Phone);
        if (KiemTraDKPhone){
            Toast.makeText(DangKyActivity.this,"Số điện thoại đã được đăng ký",Toast.LENGTH_SHORT).show();
        } else {
            if (nguoiDung != null){
                if (KiemTraDK()){
                    if (database_user.ThemUser(nguoiDung) != -1){
                        Toast.makeText(DangKyActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        medtHoTen_DangKy_cus.setText(null);
                        medtSDT_DangKy_cus.setText(null);
                        medtPW_DangKy_cus.setText(null);
                        medtRPW_DangKy_cus.setText(null);
                        id = -1;
                    }
                }
            }
        }
    }

    public NguoiDung layDuLieuNguoiDung() {
        Hoten = medtHoTen_DangKy_cus.getText().toString();
        Sdt = medtSDT_DangKy_cus.getText().toString();
        Pass = medtPW_DangKy_cus.getText().toString();
        RPass = medtRPW_DangKy_cus.getText().toString();
        String gplx = "Không";

        NguoiDung nguoiDung = new NguoiDung();
        idUser = nguoiDung.getId();
        nguoiDung.setId(id);
        nguoiDung.setHoTen(Hoten);
        nguoiDung.setSDT(Sdt);
        nguoiDung.setPassword(Pass);
        nguoiDung.setCCCD(null);
        nguoiDung.setGPLX(gplx);

        return nguoiDung;
    }

    public void btnPageLogin_onclick(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}