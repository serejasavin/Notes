package com.example.notes;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SimpleAdapter extends BaseAdapter {

    private List<Item> _Items;

    public SimpleAdapter(List<Item> items) {
        _Items = items;
    }

    @Override
    public int getCount() {
        return _Items.size();
    }

    @Override
    public Item getItem(int position) {
        return _Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        }
        ((TextView) convertView.findViewById(R.id.Title)).setText(getItem(position).getName());
        ((TextView) convertView.findViewById(R.id.PriceT)).setText(getItem(position).getPrice() + " руб.");
        return convertView;
    }
}
