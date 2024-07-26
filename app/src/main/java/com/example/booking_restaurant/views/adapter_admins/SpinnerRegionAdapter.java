package com.example.booking_restaurant.views.adapter_admins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Region;

import java.util.List;

public class SpinnerRegionAdapter extends BaseAdapter {
    Context context;
    List<Region> _reigon;
    LayoutInflater inflter;

    private TextView names;
    private SpinnerResAdapter.ClickSpinnerListener clickSpinnerListener;
    private int dropdownResource;
    public void setClickSpinnerListener(SpinnerResAdapter.ClickSpinnerListener clickSpinnerListener) {
        this.clickSpinnerListener = clickSpinnerListener;
    }

    public void setDropDownResource(int dropdownResource) {
        this.dropdownResource = dropdownResource;
    }

    public SpinnerRegionAdapter(Context applicationContext, List<Region>  region) {
        this.context = applicationContext;
        this._reigon = region;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return _reigon.isEmpty() ? _reigon.size() : 0;
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
        if (_reigon.isEmpty()) {
            // Nếu danh sách rỗng, hiển thị một dòng chữ mặc định
            view = inflter.inflate(R.layout.simple_spinner_item, null);
            names = (TextView) view.findViewById(R.id.textName);
            names.setText("No regions available"); // Thiết lập dòng chữ mặc định
        } else {
            // Nếu danh sách không rỗng, hiển thị các dòng chữ từ danh sách
            view = inflter.inflate(R.layout.simple_spinner_item, null);
            names = (TextView) view.findViewById(R.id.textName);
            names.setText(_reigon.get(i).getName().toString());
            names.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickSpinnerListener.onClickSpinner(_reigon.get(i).getUuid(), _reigon.get(i).getName());
                }
            });
        }
        view.setPivotX(14);
        return view;
    }


    public interface ClickSpinnerListener {
        void onClickSpinner(String uuid, String name);
    }
}
