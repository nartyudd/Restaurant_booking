package com.example.booking_restaurant.views.adapter_admins;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Restaurant;

import java.util.List;

public class SpinnerResAdapter extends BaseAdapter {
    Context context;
    List<Restaurant>  res;
    LayoutInflater inflter;

    private  TextView names;
    private ClickSpinnerListener clickSpinnerListener;
    private int dropdownResource;
    public void setClickSpinnerListener(ClickSpinnerListener clickSpinnerListener) {
        this.clickSpinnerListener = clickSpinnerListener;
    }

    public void setDropDownResource(int dropdownResource) {
        this.dropdownResource = dropdownResource;
    }

    public SpinnerResAdapter(Context applicationContext, List<Restaurant>  res) {
        this.context = applicationContext;
        this.res = res;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return res.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.simple_spinner_item, null);


        names = (TextView) view.findViewById(R.id.textName);
        names.setText(res.get(i).getName().toString());

        names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSpinnerListener.onClickSpinner(res.get(i).getUuid(), res.get(i).getName());
            }
        });

        view.setPivotX(14);
        return view;
    }


    public interface ClickSpinnerListener {
        void onClickSpinner(String uuid, String name);
    }
}
