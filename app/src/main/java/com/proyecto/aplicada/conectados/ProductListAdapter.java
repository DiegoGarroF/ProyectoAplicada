package com.proyecto.aplicada.conectados;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Garro on 21/11/2016.
 */

public class ProductListAdapter extends BaseAdapter {
    private Context context;
    private List<Product> mProductList;

    public ProductListAdapter(Context context, List<Product> mProductList) {
        this.context = context;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.mensajes, null);
        TextView tvName = (TextView) v.findViewById(R.id.txtRemitente);
        TextView tvPrice = (TextView) v.findViewById(R.id.txtfecha);
        TextView tvDescripcion = (TextView) v.findViewById(R.id.tv_descripcion);
        tvName.setText(mProductList.get(position).getName());
        tvPrice.setText(""+mProductList.get(position).getPrice());
        tvDescripcion.setText(mProductList.get(position).getDescripcion());

        v.setTag(mProductList.get(position).getId());
        return v;
    }
}
