package com.example.booking_restaurant.views.adapter_users;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.HomeHorModel;
import com.example.booking_restaurant.data.models.HomeVerModel;


import java.util.ArrayList;
import java.util.List;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {
    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if (check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));
            homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_1", "10:00 - 22:00", "4.1", "200K - 100K / người"));
            homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_1", "10:00 - 22:00", "4.2", "200K - 100K / người"));
            homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_1", "10:00 - 22:00", "4.3", "200K - 100K / người"));
            homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_1", "10:00 - 22:00", "4.3", "200K - 100K / người"));

            updateVerticalRec.callBack(position, homeVerModels);
            check = false;
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if (position == 0) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_2", "10:00 - 22:00", "4.1", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_3", "10:00 - 22:00", "4.2", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_4", "10:00 - 22:00", "4.3", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.bbq, "BBQ_4", "10:00 - 22:00", "4.3", "200K - 100K / người"));

                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (position == 1) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                    homeVerModels.add(new HomeVerModel(R.drawable.hotpot, "Lẫu_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.hotpot, "Lẫu_2", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.hotpot, "Lẫu_3", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.hotpot, "Lẫu_3", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.hotpot, "Lẫu_3", "10:00 - 22:00", "4.0", "200K - 100K / người"));

                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (position == 2) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.monviet, "Việt_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.monviet, "Việt_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));

                    homeVerModels.add(new HomeVerModel(R.drawable.monviet, "Việt_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.monviet, "Việt_2", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.monviet, "Việt_3", "10:00 - 22:00", "4.0", "200K - 100K / người"));

                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (position == 3) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.vegetable, "Chay_2", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.vegetable, "Chay_2", "10:00 - 22:00", "4.0", "200K - 100K / người"));

                    homeVerModels.add(new HomeVerModel(R.drawable.vegetable, "Chay_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.vegetable, "Chay_2", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.vegetable, "Chay_3", "10:00 - 22:00", "4.0", "200K - 100K / người"));

                    updateVerticalRec.callBack(position, homeVerModels);
                }  else if (position == 4) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.sushi, "Nhật_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sushi, "Nhật_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));

                    homeVerModels.add(new HomeVerModel(R.drawable.sushi, "Nhật_1", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sushi, "Nhật_2", "10:00 - 22:00", "4.0", "200K - 100K / người"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sushi, "Nhật_3", "10:00 - 22:00", "4.0", "200K - 100K / người"));

                    updateVerticalRec.callBack(position, homeVerModels);
                }
            }
        });
        if (select) {
            if (position == 0) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
                select = false;
            }
        } else {
            if (row_index == position) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
            } else {
                holder.cardView.setBackgroundResource(R.drawable.default_bg);
            }
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hor_image);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


}
