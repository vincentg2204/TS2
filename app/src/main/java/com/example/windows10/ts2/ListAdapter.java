package com.example.windows10.ts2;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private Route[] route;
    private MainActivity ui;
    private Fragment1 fragment1;

    public ListAdapter(Route[] route, MainActivity ui, Fragment1 fragment1) {
        this.route = route;
        this.ui = ui;
        this.fragment1 = fragment1;
    }

    @Override
    public int getCount() {
        return route.length;
    }

    @Override
    public Route getItem(int position) {
        return route[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ui).inflate(R.layout.list_item, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.update(getItem(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment1.changePage(2, position);
            }
        });

        return convertView;
    }

    public Route[] getRoute() {
        return route;
    }

    public void setRoute(Route[] route) {
        this.route = route;
    }

    private class ViewHolder{
        private TextView tvRute;

        public ViewHolder(View v){
            tvRute = v.findViewById(R.id.text_route);
        }
        public void update(Route route){
            tvRute.setText(route.getDisplay_name());
            Log.d("ROUTECOLOR",route.getFgColor()+" "+route.getBgColor());
            if(route.getFgColor() != null) {
                tvRute.setTextColor(Color.parseColor(route.getFgColor()));
            }
            if(route.getBgColor() != null) {
                tvRute.setBackgroundColor(Color.parseColor(route.getBgColor()));
            }
        }
    }
}
