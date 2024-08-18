package com.example.pjthuexeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjthuexeapp.ActivityAdmin.ChiTietHoaDonDatXeAdminActivity;
import com.example.pjthuexeapp.ActivityCus.ChiTietHoaDonDatXeActivity;
import com.example.pjthuexeapp.Class.HoaDon;
import com.example.pjthuexeapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLyHoaDonDatXeAdapter extends RecyclerView.Adapter<QuanLyHoaDonDatXeAdapter.ViewHolder>{

    Context context;
    int layout;
    ArrayList<HoaDon> hoaDonArrayList;

    public QuanLyHoaDonDatXeAdapter(Context context, int layout, ArrayList<HoaDon> hoaDonArrayList) {
        this.context = context;
        this.layout = layout;
        this.hoaDonArrayList = hoaDonArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new QuanLyHoaDonDatXeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDon hoaDon = hoaDonArrayList.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
        holder.mtv_MaDon_LichSuDatXe.setText(String.valueOf(hoaDon.getId()));
        holder.mtv_NgayHienTai_LichSuDatXe.setText(hoaDon.getNgatDatDon());
        holder.mtv_TenUser_LichSuDatXe.setText(hoaDon.getTenCus());
        holder.mtv_SDTcus_LichSuDatXe.setText(hoaDon.getSDTCus());
        holder.mtv_CMND_LichSuDatXe.setText(hoaDon.getCMND());
        holder.mtv_TenXe_LichSuDatXe.setText(hoaDon.getTenXe());
        holder.mtv_BienSoXe_LichSuDatXe.setText(hoaDon.getBienSoXe());
        holder.mtv_DiaDiemGiaoXe_LichSuDatXe.setText(hoaDon.getDiaDiemNhanXe());
        holder.mtv_DiaDiemTraXe_LichSuDatXe.setText(hoaDon.getDiaDiemTraXe());
        holder.mtv_NgayNhanXe_LichSuDatXe.setText(hoaDon.getNgayNhanXe());
        holder.mtv_GioNhanXe_LichSuDatXe.setText(hoaDon.getGioNhanXe());
        holder.mtv_NgayTraXe_LichSuDatXe.setText(hoaDon.getNgayTraXe());
        holder.mtv_GioTraXe_LichSuDatXe.setText(hoaDon.getGioTraXe());
        holder.mtv_TongSoNgayThue_LichSuDatXe.setText(String.valueOf(hoaDon.getTongNgayThue()));
        holder.mtv_TongTien_LichSuDatXe.setText(String.valueOf(decimalFormat.format(hoaDon.getTongTien())));
        holder.mtv_GiaTienThue_LichSuDatXe.setText(String.valueOf(decimalFormat.format(hoaDon.getGiaTienThueXe())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToHoaDonDetail(hoaDon);
            }
        });
    }

    private void onClickGoToHoaDonDetail(HoaDon hoadon){
        Intent i = new Intent(context, ChiTietHoaDonDatXeAdminActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("HoaDon", hoadon);
        i.putExtras(b);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return hoaDonArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mtv_NgayHienTai_LichSuDatXe,mtv_TenUser_LichSuDatXe,mtv_SDTcus_LichSuDatXe,mtv_CMND_LichSuDatXe
                ,mtv_TenXe_LichSuDatXe, mtv_BienSoXe_LichSuDatXe, mtv_DiaDiemGiaoXe_LichSuDatXe,
                mtv_DiaDiemTraXe_LichSuDatXe, mtv_NgayNhanXe_LichSuDatXe, mtv_GioNhanXe_LichSuDatXe,
                mtv_NgayTraXe_LichSuDatXe, mtv_GioTraXe_LichSuDatXe, mtv_TongSoNgayThue_LichSuDatXe
                ,mtv_TongTien_LichSuDatXe, mtv_GiaTienThue_LichSuDatXe,mtv_MaDon_LichSuDatXe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtv_MaDon_LichSuDatXe = itemView.findViewById(R.id.tv_MaDon_LichSuDatXe);
            mtv_NgayHienTai_LichSuDatXe = itemView.findViewById(R.id.tv_NgayHienTai_LichSuDatXe);
            mtv_TenUser_LichSuDatXe = itemView.findViewById(R.id.tv_TenUser_LichSuDatXe);
            mtv_SDTcus_LichSuDatXe = itemView.findViewById(R.id.tv_SDTcus_LichSuDatXe);
            mtv_CMND_LichSuDatXe = itemView.findViewById(R.id.tv_CMND_LichSuDatXe);
            mtv_TenXe_LichSuDatXe = itemView.findViewById(R.id.tv_TenXe_LichSuDatXe);
            mtv_BienSoXe_LichSuDatXe = itemView.findViewById(R.id.tv_BienSoXe_LichSuDatXe);
            mtv_DiaDiemGiaoXe_LichSuDatXe = itemView.findViewById(R.id.tv_DiaDiemGiaoXe_LichSuDatXe);
            mtv_DiaDiemTraXe_LichSuDatXe = itemView.findViewById(R.id.tv_DiaDiemTraXe_LichSuDatXe);
            mtv_NgayNhanXe_LichSuDatXe = itemView.findViewById(R.id.tv_NgayNhanXe_LichSuDatXe);
            mtv_GioNhanXe_LichSuDatXe = itemView.findViewById(R.id.tv_GioNhanXe_LichSuDatXe);
            mtv_NgayTraXe_LichSuDatXe = itemView.findViewById(R.id.tv_NgayTraXe_LichSuDatXe);
            mtv_GioTraXe_LichSuDatXe = itemView.findViewById(R.id.tv_GioTraXe_LichSuDatXe);
            mtv_TongSoNgayThue_LichSuDatXe = itemView.findViewById(R.id.tv_TongSoNgayThue_LichSuDatXe);
            mtv_TongTien_LichSuDatXe = itemView.findViewById(R.id.tv_TongTien_LichSuDatXe);
            mtv_GiaTienThue_LichSuDatXe = itemView.findViewById(R.id.tv_GiaTienThue_LichSuDatXe);
        }
    }
}
