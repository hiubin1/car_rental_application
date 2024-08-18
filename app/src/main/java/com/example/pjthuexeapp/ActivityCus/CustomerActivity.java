package com.example.pjthuexeapp.ActivityCus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.example.pjthuexeapp.FragmentCus.FragmentCar;
import com.example.pjthuexeapp.FragmentCus.FragmentHome;
import com.example.pjthuexeapp.FragmentCus.FragmentNoice;
import com.example.pjthuexeapp.FragmentCus.FragmentProfile;
import com.example.pjthuexeapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);



        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_nav_cus);

        loadFragment(new FragmentHome());
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_home) {
                    loadFragment(new FragmentHome());
                } else if (item.getItemId() == R.id.nav_car){
                    loadFragment(new FragmentCar());
                } else if (item.getItemId() == R.id.nav_noice) {
                    loadFragment(new FragmentNoice());
                } else if (item.getItemId() == R.id.nav_profile) {
                    loadFragment(new FragmentProfile());
                }
                return true;
            }
        };
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    private void loadFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

    public void btnDangXuat_Cus_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void btnThongTinCaNhan_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), ThongTinCaNhanActivity.class);
        startActivity(i);
    }

    public void btnXeCanThue_Cus_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), LichSuDatXeActivity.class);
        startActivity(i);
    }


    public void btnHopDong_Cus_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), HopDongCusActivity.class);
        startActivity(i);
    }

    public static interface DatTruocXeActivity {
        void onDateSet(DatePicker datePicker, int year, int month, int day);
    }
}