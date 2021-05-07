package com.example.plantes_test_2.adaptateur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.plantes_test_2.Plante;
import com.example.plantes_test_2.R;

import java.util.ArrayList;

public class MonAdaptateur extends BaseAdapter {

    Context context;
    ArrayList<Plante> arrayList;

    public MonAdaptateur(Context context, ArrayList<Plante> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.malistview, null);
        TextView nom_txt = (TextView) convertView.findViewById(R.id.nom_txt);
        TextView date_txt = (TextView) convertView.findViewById(R.id.date_txt);

        Plante plante = arrayList.get(position);
        nom_txt.setText(plante.get_nom());
        date_txt.setText(plante.get_dateNextArrosage());

        return convertView;
    }
}
