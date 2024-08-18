package com.example.pjthuexeapp.Class;

import com.example.pjthuexeapp.R;

import java.util.ArrayList;

public class DiaDiemNoiBat {
    int pic;
    String TenDiaDiem;

    public DiaDiemNoiBat(int pic, String tenDiaDiem) {
        this.pic = pic;
        TenDiaDiem = tenDiaDiem;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTenDiaDiem() {
        return TenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        TenDiaDiem = tenDiaDiem;
    }

    public static ArrayList<DiaDiemNoiBat> init(){
        String[] tendiadiem = {"TP.Hồ Chí Minh","Hà Nội","Đà Nãng","Bình Dương"};
        int[] anhdiadiem = {
                R.drawable.hochiminh,
                R.drawable.hanoi,
                R.drawable.danang,
                R.drawable.binhduong,
        };
        ArrayList<DiaDiemNoiBat> diaDiemNoiBatArrayList = new ArrayList<>();
        for(int i = 0;i < tendiadiem.length;i++){
            DiaDiemNoiBat diaDiemNoiBat = new DiaDiemNoiBat(anhdiadiem[i],tendiadiem[i]);
            diaDiemNoiBatArrayList.add(diaDiemNoiBat);
        }
        return diaDiemNoiBatArrayList;
    }
}
