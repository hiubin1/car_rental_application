package com.example.pjthuexeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjthuexeapp.Class.DiaDiemNoiBat;
import com.example.pjthuexeapp.R;

import java.util.ArrayList;

public class DiaDiemNoiBatAdapter extends RecyclerView.Adapter<DiaDiemNoiBatAdapter.ViewHolder> {

    Context context;
    int layout;
    ArrayList<DiaDiemNoiBat> diaDiemNoiBatArrayList;

    public DiaDiemNoiBatAdapter(Context context, int layout, ArrayList<DiaDiemNoiBat> diaDiemNoiBatArrayList) {
        this.context = context;
        this.layout = layout;
        this.diaDiemNoiBatArrayList = diaDiemNoiBatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiaDiemNoiBat diaDiemNoiBat = diaDiemNoiBatArrayList.get(position);
        holder.mtv_tendiadiem.setText(String.valueOf(diaDiemNoiBat.getTenDiaDiem()));
        holder.mimg_diadiemnoibat.setImageResource(diaDiemNoiBat.getPic());
    }

    @Override
    public int getItemCount() {
        return diaDiemNoiBatArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mimg_diadiemnoibat;

        TextView mtv_tendiadiem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mimg_diadiemnoibat = itemView.findViewById(R.id.img_diadiemnoibat);
            mtv_tendiadiem = itemView.findViewById(R.id.tv_tendiadiem);
        }
    }
}
