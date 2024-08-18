package com.example.pjthuexeapp.FragmentCus;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pjthuexeapp.Database.Database_User;
import com.example.pjthuexeapp.R;
import com.example.pjthuexeapp.Session.UserSession;


public class FragmentProfile extends Fragment {

    TextView mtv_TenUser_Profile;

    Database_User database_user;

    View v;

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        mtv_TenUser_Profile = v.findViewById(R.id.tv_TenUser_Profile);
        database_user = new Database_User(getContext());
        String sdt = UserSession.userPhoneCurrent;
        String UserName = "";
        Cursor cursor = database_user.GetNameFromSdt(sdt);
        if (cursor.moveToFirst()){
            UserName = cursor.getString(cursor.getColumnIndex("Ten_User"));
        }
        mtv_TenUser_Profile.setText(UserName);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}