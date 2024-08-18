package com.example.pjthuexeapp.ActivityCus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pjthuexeapp.ActivityAdmin.AdminActivity;
import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;

public class MainActivity extends AppCompatActivity {

    EditText medtSDT_Login_cus,medtPW_Login_cus;
    CheckBox mchkGhiNhoMK;

    Database_User database_user;

    String fileName = "mySharedPreferences";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medtSDT_Login_cus = (EditText) findViewById(R.id.edtSDT_Login_cus);
        medtPW_Login_cus = (EditText) findViewById(R.id.edtPW_Login_cus);
        mchkGhiNhoMK = (CheckBox) findViewById(R.id.chkGhiNhoMK);
        database_user = new Database_User(this);
        medtSDT_Login_cus.requestFocus();
    }

    @SuppressLint("Range")
    public void btnDangNhap_onclick(View view) {

        String phone = medtSDT_Login_cus.getText().toString();
        String pass = medtPW_Login_cus.getText().toString();


        if (database_user.KiemTraDangNhap(phone,pass)){
            String userRole = database_user.KiemTraRole(phone);

            if (userRole.equals("0")){
                UserSession.isLogged = true;
                UserSession.userPhoneCurrent = phone;


                LayThongTinUser(phone);


                String sdt = UserSession.userPhoneCurrent;
                String Name = "";
                Cursor cursor = database_user.GetNameFromSdt(sdt);
                if (cursor.moveToFirst()){
                    Name = cursor.getString(cursor.getColumnIndex("Ten_User"));
                    String message = "Xin chào " + Name;
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),CustomerActivity.class);
                    startActivity(i);
                }

            } else {
                Intent i = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(i);
            }
        } else {
            Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

        }
    }

    public void btnPageRegister_onclick(View view) {
        Intent i = new Intent(getApplicationContext(),DangKyActivity.class);
        startActivity(i);
    }

    @SuppressLint("Range")
    public void LayThongTinUser(String SdtUser){
        Cursor cursor = database_user.GetUserFromSdt(SdtUser);
        NguoiDung nguoiDung = new NguoiDung();

        if (cursor != null){
            while (cursor.moveToNext()){
                nguoiDung.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_USER)));
                nguoiDung.setSDT(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SDT_USER)));
                nguoiDung.setCCCD(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CCCD_USER)));
            }

            UserSession.userId = nguoiDung.getId();
            UserSession.CCCD = nguoiDung.getCCCD();

        }
        cursor.close();
    }
    private void savingPreferences(){
//        Tạo đối tượng getSharedPreferences
        SharedPreferences pre = this.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        // Tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = pre.edit();

//        lưu trữ dữ liệu dưới dạng key/value
        String sdt = medtSDT_Login_cus.getText().toString();
        String pw = medtPW_Login_cus.getText().toString();
        boolean bchk = mchkGhiNhoMK.isChecked();

        if(!bchk){ // nếu người dùng không check
//            Xóa mọi thứ lưu trữ trước đó
            editor.clear();
        } else { // nếu người dùng check
//            lưu vào editor và hiển thị sẵn
            editor.putString("sdt",sdt);
            editor.putString("pw",pw);
            editor.putBoolean("checked",bchk);
        }
//        Chấp nhận lưu xuống file
        editor.commit();
    }

    public void restoringPreferences(){
        SharedPreferences pre = this.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        if(pre != null){
            boolean bchk = pre.getBoolean("checked",false); // Lấy giá trị đã lưu trữ với key là "checked" nếu không có giá trị nào được lưu với key tương ứng, phương thức sẽ trả về false

            if(bchk){
                //  Lấy giá trị đã lưu trữ với key là "sdt" và "Pw" nếu không có giá trị nào được lưu với key tương ứng, phương thức sẽ trả về chuỗi rỗng
                String sdt = pre.getString("sdt","");
                String pw = pre.getString("pw","");

                medtSDT_Login_cus.setText(sdt);
                medtPW_Login_cus.setText(pw);
            }
            mchkGhiNhoMK.setChecked(bchk);
        }
    }

    @Override
    protected void onResume() {
        // gọi hàm đọc trạng thái
        super.onResume();
        restoringPreferences();
    }

    @Override
    protected void onPause() {
        // gọi hàm lưu trạng thái
        super.onPause();
        savingPreferences();
    }
}