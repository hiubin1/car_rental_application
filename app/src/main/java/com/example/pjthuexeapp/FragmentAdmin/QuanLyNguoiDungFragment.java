package com.example.pjthuexeapp.FragmentAdmin;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pjthuexeapp.Adapter.QuanLyNguoiDungAdapter;
import com.example.pjthuexeapp.Adapter.QuanLyXeAdapter;
import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.DBHelper;
import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.R;

import java.util.ArrayList;


public class QuanLyNguoiDungFragment extends Fragment {

    View v;

    RecyclerView mRCview_QuanLyNguoiDung;

    RecyclerView.LayoutManager mlayoutManager;

    ArrayList<NguoiDung> nguoiDungArrayList;

    QuanLyNguoiDungAdapter quanLyNguoiDungAdapter;

    EditText medtTimNguoi_QuanLyNguoiDung;

    Database_User database_user;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_quan_ly_nguoi_dung, container, false);
        medtTimNguoi_QuanLyNguoiDung = v.findViewById(R.id.edtTimNguoi_QuanLyNguoiDung);
        database_user = new Database_User(getContext());
        capNhatDuLieu();
        medtTimNguoi_QuanLyNguoiDung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String searchText = s.toString();

                if (searchText.isEmpty()){
                    capNhatDuLieu();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();
                if (searchText.isEmpty()){
                    capNhatDuLieu();
                } else {
                    Search(searchText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        capNhatDuLieu();
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if(nguoiDungArrayList == null){
            nguoiDungArrayList = new ArrayList<>();
        } else {
            nguoiDungArrayList.removeAll(nguoiDungArrayList);
        }
        Cursor cursor = database_user.layTatCaDuLieu();
        if (cursor != null){
            while (cursor.moveToNext()){
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_USER)));
                nguoiDung.setHoTen(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN_USER)));
                nguoiDung.setSDT(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SDT_USER)));
                nguoiDung.setCCCD(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CCCD_USER)));

                nguoiDungArrayList.add(nguoiDung);
            }
        }
        SetRV();
    }

    private void SetRV(){
        if (nguoiDungArrayList != null){
            mRCview_QuanLyNguoiDung = v.findViewById(R.id.RCview_QuanLyNguoiDung);
            mlayoutManager = new LinearLayoutManager(getContext());
            mRCview_QuanLyNguoiDung.setLayoutManager(mlayoutManager);
            quanLyNguoiDungAdapter = new QuanLyNguoiDungAdapter(getContext(),R.layout.list_quanlynguoidung, nguoiDungArrayList);
            mRCview_QuanLyNguoiDung.setAdapter(quanLyNguoiDungAdapter);
            quanLyNguoiDungAdapter.notifyDataSetChanged();
            mRCview_QuanLyNguoiDung.invalidate();
        }
    }

    @SuppressLint("Range")
    public void Search(String nameUser){
        if (nguoiDungArrayList == null){
            nguoiDungArrayList = new ArrayList<NguoiDung>();
        } else {
            nguoiDungArrayList.removeAll(nguoiDungArrayList);
        }

        String newText = nameUser;
        Cursor cursor = database_user.Search(newText);

        if (cursor!= null){
            while (cursor.moveToNext()){
                NguoiDung nguoiDung = new NguoiDung();

                nguoiDung.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_USER)));
                nguoiDung.setHoTen(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN_USER)));
                nguoiDung.setSDT(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SDT_USER)));
                nguoiDung.setCCCD(cursor.getString(cursor.getColumnIndex(DBHelper.COT_CCCD_USER)));
                nguoiDungArrayList.add(nguoiDung);
            }
        } else {
            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
        }
        SetRV();
    }

}