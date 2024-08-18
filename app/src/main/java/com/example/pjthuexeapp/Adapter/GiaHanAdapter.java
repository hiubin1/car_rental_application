package com.example.pjthuexeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjthuexeapp.Class.DiaDiemNoiBat;
import com.example.pjthuexeapp.Class.DonGiaHan;
import com.example.pjthuexeapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiaHanAdapter extends RecyclerView.Adapter<GiaHanAdapter.ViewHolder> {

    Context context;
    int layout;
    ArrayList<DonGiaHan> donGiaHanArrayList;

    public GiaHanAdapter(Context context, int layout, ArrayList<DonGiaHan> donGiaHanArrayList) {
        this.context = context;
        this.layout = layout;
        this.donGiaHanArrayList = donGiaHanArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new GiaHanAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonGiaHan donGiaHan = donGiaHanArrayList.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0");
        holder.mtv_MaDonGiaHan_GiaHan.setText(String.valueOf(donGiaHan.getId()));
        holder.mtv_NgayBatDauGiaHan_GiaHan.setText(donGiaHan.getNgayBatDauGiaHan());
        holder.mtv_TongTien_GiaHan.setText(decimalFormat.format(donGiaHan.getTongTien()));
        holder.mtv_TongNgayGiaHan_GiaHan.setText(String.valueOf(donGiaHan.getTongNgayGiaHan()));

    }

    @Override
    public int getItemCount() {
        return donGiaHanArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mtv_MaDonGiaHan_GiaHan, mtv_NgayBatDauGiaHan_GiaHan,
        mtv_TongNgayGiaHan_GiaHan, mtv_TongTien_GiaHan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtv_MaDonGiaHan_GiaHan = itemView.findViewById(R.id.tv_MaDonGiaHan_GiaHan);
            mtv_NgayBatDauGiaHan_GiaHan = itemView.findViewById(R.id.tv_NgayBatDauGiaHan_GiaHan);
            mtv_TongNgayGiaHan_GiaHan = itemView.findViewById(R.id.tv_TongNgayGiaHan_GiaHan);
            mtv_TongTien_GiaHan = itemView.findViewById(R.id.tv_TongTien_GiaHan);

        }
    }
}
