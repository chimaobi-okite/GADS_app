package com.example.gadsleaderboardapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gadsleaderboardapp.R;
import com.example.gadsleaderboardapp.models.Hours;

import java.util.List;

public class HoursAdapter  extends RecyclerView.Adapter<HoursAdapter.HoursViewHolder> {

    private List<Hours> mHours;

    public HoursAdapter(List<Hours> hours) {
       mHours = hours;
    }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.gads_item,parent,false);
        return new HoursViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {

        Hours myHours = mHours.get(position);
        holder.bind(myHours);
    }

    @Override
    public int getItemCount() {
        return mHours.size();
    }

    public class HoursViewHolder extends RecyclerView.ViewHolder{

        private ImageView iqImage;
        private TextView description;
        private TextView name;

        public HoursViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text);
            iqImage = itemView.findViewById(R.id.badge_image);
            description = itemView.findViewById(R.id.text_description);
        }

        private void bind(Hours hours){

            String learningHours = String.valueOf(hours.getHours());
            String country = hours.getCountry();

            String descriptionText = learningHours  +" learning hours, "+ country;

            description.setText(descriptionText);
            name.setText(hours.getName());
            Glide.with(itemView.getContext())
                    .load(hours.getBadgeUrl())
                    .placeholder(R.drawable.top_learner)
                    .into(iqImage);

        }

    }
}
