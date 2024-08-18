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

import com.example.pjthuexeapp.ActivityAdmin.ChiTietHopDongActivity;
import com.example.pjthuexeapp.ActivityCus.ChiTietHopDongCusActivity;
import com.example.pjthuexeapp.Class.HopDong;
import com.example.pjthuexeapp.R;

import java.util.ArrayList;

public class DanhSachHopDongAdapter extends RecyclerView.Adapter<DanhSachHopDongAdapter.ViewHolder>  {

    Context context;
    int layout;
    ArrayList<HopDong> hopDongArrayList;

    public DanhSachHopDongAdapter(Context context, int layout, ArrayList<HopDong> hopDongArrayList) {
        this.context = context;
        this.layout = layout;
        this.hopDongArrayList = hopDongArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new DanhSachHopDongAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HopDong hopDong = hopDongArrayList.get(position);
        holder.mtvTen_QuanLyHopDong.setText(hopDong.getTenCus());
        holder.mtvMaHopDong_QuanLyHopDong.setText((String.valueOf(hopDong.getId())));
        holder.mtvCMND_QuanLyHopDong.setText(hopDong.getCCCD());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToHopDongDetail(hopDong);
            }
        });
    }

    private void onClickGoToHopDongDetail(HopDong hopDong){
        Intent i = new Intent(context, ChiTietHopDongCusActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("HopDong", hopDong);
        i.putExtras(b);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return hopDongArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mtvMaHopDong_QuanLyHopDong, mtvTen_QuanLyHopDong, mtvCMND_QuanLyHopDong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvMaHopDong_QuanLyHopDong = itemView.findViewById(R.id.tvMaHopDong_QuanLyHopDong);
            mtvTen_QuanLyHopDong = itemView.findViewById(R.id.tvTen_QuanLyHopDong);
            mtvCMND_QuanLyHopDong = itemView.findViewById(R.id.tvCMND_QuanLyHopDong);
        }
    }
}
