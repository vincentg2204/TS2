package com.example.windows10.ts2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class ListStopAdapter extends BaseAdapter{
    private Stop[] stops;
    private MainActivity ui;

    public ListStopAdapter(Stop[] stops, MainActivity ui) {
        this.stops = stops;
        this.ui = ui;
    }

    @Override
    public int getCount() {
        return stops.length;
    }

    @Override
    public Stop getItem(int position) {
        return stops[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ui).inflate(R.layout.list_stop, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.update(getItem(position));
        return convertView;
    }

    private class ViewHolder{
        private TextView tvJudul,tvWaktu;

        public ViewHolder(View v){
            tvJudul = v.findViewById(R.id.tv_judul);
            tvWaktu = v.findViewById(R.id.tv_waktu);
        }

        public void update(Stop stop){
            tvJudul.setText(stop.getDisplay_name());
        }
    }
}