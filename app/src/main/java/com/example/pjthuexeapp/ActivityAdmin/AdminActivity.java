package com.example.pjthuexeapp.ActivityAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pjthuexeapp.ActivityCus.MainActivity;
import com.example.pjthuexeapp.FragmentAdmin.QuanLyHoaDonDatXeFragment;
import com.example.pjthuexeapp.FragmentAdmin.QuanLyHopDongFragment;
import com.example.pjthuexeapp.FragmentAdmin.QuanLyNguoiDungFragment;
import com.example.pjthuexeapp.FragmentAdmin.QuanLyXeFrament;
import com.example.pjthuexeapp.R;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mdrawer_layout_admin;
    NavigationView navdraw_admin;

    TextView mtv_TenAdmin_Admin;

    TextView mtv_SdtAdmin_Admin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar mtoolbar_drawlayout_admin = findViewById(R.id.toolbar_drawlayout_admin);
        setSupportActionBar(mtoolbar_drawlayout_admin);

        mdrawer_layout_admin = findViewById(R.id.drawer_layout_admin);
        navdraw_admin = findViewById(R.id.navdraw_admin);
        mtv_TenAdmin_Admin = findViewById(R.id.tv_TenAdmin_Admin);
        mtv_SdtAdmin_Admin = findViewById(R.id.tv_SdtAdmin_Admin);

        navdraw_admin.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mdrawer_layout_admin, mtoolbar_drawlayout_admin, R.string.open_nav,R.string.close_nav);
        mdrawer_layout_admin.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new QuanLyXeFrament()).commit();
            navdraw_admin.setCheckedItem(R.id.nav_QuanLyXe);
        }
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.nav_QuanLyXe) {
            loadFragment(new QuanLyXeFrament());
        } else if (item.getItemId() == R.id.nav_QuanLyNguoiDung) {
            loadFragment(new QuanLyNguoiDungFragment());
        } else if (item.getItemId() == R.id.nav_QuanLyHopDong) {
            loadFragment(new QuanLyHopDongFragment());
        } else if (item.getItemId() == R.id.nav_LogOut) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.nav_QuanLyHoaDonDatXe){
            loadFragment(new QuanLyHoaDonDatXeFragment());
        }
        mdrawer_layout_admin.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mdrawer_layout_admin.isDrawerOpen(GravityCompat.START)){
            mdrawer_layout_admin.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void btnChiTietXeAdmin_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), ThemXeAdminActivity.class);
        startActivity(i);
    }
}