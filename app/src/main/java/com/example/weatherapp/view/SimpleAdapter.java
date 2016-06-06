package com.example.weatherapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.pojo.weather.SimpleWeather;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

    private ArrayList<SimpleWeather> mData;

    public SimpleAdapter(ArrayList<SimpleWeather> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.day_textView_itemFiveDays)
        TextView day;
        @Bind(R.id.temp_textView_itemFiveDays)
        TextView temp;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.five_days_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.day.setText(mData.get(position).getDay());
        holder.temp.setText(mData.get(position).getTemp());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}