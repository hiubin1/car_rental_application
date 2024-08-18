package com.example.pjthuexeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjthuexeapp.Class.HoaDon;
import com.example.pjthuexeapp.Class.ThongBao;
import com.example.pjthuexeapp.R;

import java.util.ArrayList;

public class QuanLyThongBaoAdapter extends RecyclerView.Adapter<QuanLyThongBaoAdapter.ViewHolder>{

    Context context;
    int layout;
    ArrayList<ThongBao> thongBaoArrayList;

    public QuanLyThongBaoAdapter(Context context, int layout, ArrayList<ThongBao> thongBaoArrayList) {
        this.context = context;
        this.layout = layout;
        this.thongBaoArrayList = thongBaoArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new QuanLyThongBaoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThongBao thongBao = thongBaoArrayList.get(position);
        holder.mtvTieuDe_ThongBao.setText(thongBao.getTieuDe());
        holder.mtvNoiDung_ThongBao.setText(thongBao.getNoiDung());

    }

    @Override
    public int getItemCount() {
        return thongBaoArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mtvTieuDe_ThongBao, mtvNoiDung_ThongBao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvTieuDe_ThongBao = itemView.findViewById(R.id.tvTieuDe_ThongBao);
            mtvNoiDung_ThongBao = itemView.findViewById(R.id.tvNoiDung_ThongBao);
        }
    }
}
