package com.example.pjthuexeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjthuexeapp.ActivityAdmin.ChinhSuaXeAdminActivity;
import com.example.pjthuexeapp.Class.Xe;
import com.example.pjthuexeapp.Database.Database_Xe;
import com.example.pjthuexeapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLyXeAdapter extends RecyclerView.Adapter<QuanLyXeAdapter.ViewHolder>{
    Context context;
    int layout;
    ArrayList<Xe> xeList;

    Database_Xe databaseXe;

    public QuanLyXeAdapter(Context context, int layout, ArrayList<Xe> xeList) {
        this.context = context;
        this.layout = layout;
        this.xeList = xeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);

        databaseXe = new Database_Xe(v.getContext());
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
                Intent i = new Intent(context,ChinhSuaXeAdminActivity.class);
                Bundle b = new Bundle();
                b.putLong("ID", xe.getId());
                i.putExtras(b);
                context.startActivity(i);

            }
        });


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
