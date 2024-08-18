package com.example.pjthuexeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjthuexeapp.ActivityCus.ChiTietXeActivity;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.R;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;

public class DanhSachXeAdapter extends RecyclerView.Adapter<DanhSachXeAdapter.ViewHolder> {
    Context context;
    int layout;
    ArrayList<Xe> xeList;



    public DanhSachXeAdapter(Context context, int layout, ArrayList<Xe> xeList) {
        this.context = context;
        this.layout = layout;
        this.xeList = xeList;
    }

    @NonNull
    @Override
    public DanhSachXeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new DanhSachXeAdapter.ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull DanhSachXeAdapter.ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
        Xe xe = xeList.get(position);
        holder.mtv_TruyenDong_QuanLyXe.setText(String.valueOf(xe.getTruyenDong()));
        holder.mtv_TenXe_QuanLyXe.setText(String.valueOf(xe.getTenXe()));
        holder.mtv_DiaDiemGiaoXe_QuanLyXe.setText(String.valueOf(xe.getDiaDiemNhanXe()));
        holder.mtv_ChoNgoi_QuanLyXe.setText(String.valueOf(xe.getSoGhe()));
        holder.mtv_GiaTien_QuanLyXe.setText(String.valueOf(decimalFormat.format(xe.getGiaTien())));
        byte[] hinhXeBytes = xe.getHinhXe();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhXeBytes, 0, hinhXeBytes.length);
        holder.mimgCar_QuanLyXe.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToXeDetail(xe);
            }
        });



    }
    private void onClickGoToXeDetail(Xe xe){
        Intent i = new Intent(context, ChiTietXeActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("Xe", xe);
        i.putExtras(b);
        context.startActivity(i);
    }



    @Override
    public int getItemCount() {
        return xeList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mimgCar_QuanLyXe;

        TextView mtv_TruyenDong_QuanLyXe,mtv_TenXe_QuanLyXe,mtv_DiaDiemGiaoXe_QuanLyXe,
                mtv_ChoNgoi_QuanLyXe,mtv_GiaTien_QuanLyXe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mimgCar_QuanLyXe = itemView.findViewById(R.id.imgCar_QuanLyXe);
            mtv_TruyenDong_QuanLyXe = itemView.findViewById(R.id.tv_TruyenDong_QuanLyXe);
            mtv_TenXe_QuanLyXe = itemView.findViewById(R.id.tv_TenXe_QuanLyXe);
            mtv_DiaDiemGiaoXe_QuanLyXe = itemView.findViewById(R.id.tv_DiaDiemGiaoXe_QuanLyXe);
            mtv_ChoNgoi_QuanLyXe = itemView.findViewById(R.id.tv_ChoNgoi_QuanLyXe);
            mtv_GiaTien_QuanLyXe = itemView.findViewById(R.id.tv_GiaTien_QuanLyXe);
        }
    }
}
