package com.example.pjthuexeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjthuexeapp.Class.NguoiDung;
import com.example.pjthuexeapp.R;

import java.util.ArrayList;
import java.util.Optional;

public class QuanLyNguoiDungAdapter extends RecyclerView.Adapter<QuanLyNguoiDungAdapter.ViewHolder>{

    Context context;
    int layout;
    ArrayList<NguoiDung> nguoiDungArrayList;



    public QuanLyNguoiDungAdapter(Context context, int layout, ArrayList<NguoiDung> nguoiDungArrayList) {
        this.context = context;
        this.layout = layout;
        this.nguoiDungArrayList = nguoiDungArrayList;
    }

    @NonNull
    @Override
    public QuanLyNguoiDungAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NguoiDung nguoiDung = nguoiDungArrayList.get(position);
        holder.mtv_Ten_QuanLyNguoiDung.setText(nguoiDung.getHoTen());
        holder.mtv_SDT_QuanLyNguoiDung.setText(nguoiDung.getSDT());
        holder.mtv_CMND_QuanLyNguoiDung.setText(nguoiDung.getCCCD());
    }




    @Override
    public int getItemCount() {
        return nguoiDungArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView mtv_Ten_QuanLyNguoiDung, mtv_SDT_QuanLyNguoiDung, mtv_CMND_QuanLyNguoiDung;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtv_Ten_QuanLyNguoiDung = itemView.findViewById(R.id.tv_Ten_QuanLyNguoiDung);
            mtv_SDT_QuanLyNguoiDung = itemView.findViewById(R.id.tv_SDT_QuanLyNguoiDung);
            mtv_CMND_QuanLyNguoiDung = itemView.findViewById(R.id.tv_CMND_QuanLyNguoiDung);
        }
    }
}
