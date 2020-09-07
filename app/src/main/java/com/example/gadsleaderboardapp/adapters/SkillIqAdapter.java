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
import com.example.gadsleaderboardapp.models.Skill;

import java.util.List;

public class SkillIqAdapter extends RecyclerView.Adapter<SkillIqAdapter.SkillViewHolder> {

    private List<Skill> mSkills;

    public SkillIqAdapter(List<Skill> skills) {
        mSkills = skills;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.gads_item,parent,false);
        return new SkillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {

        Skill mySkill = mSkills.get(position);
        holder.bind(mySkill);
    }

    @Override
    public int getItemCount() {
        return mSkills.size();
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder{

        private ImageView iqImage;
        private TextView description;
        private TextView name;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text);
            iqImage = itemView.findViewById(R.id.badge_image);
            description = itemView.findViewById(R.id.text_description);
        }

        private void bind(Skill skill){

            String score = String.valueOf(skill.getScore());
            String country = skill.getCountry();

            String descriptionText = score +" skill IQ Score, "+ country;

            description.setText(descriptionText);
            name.setText(skill.getName());
            Glide.with(itemView.getContext())
                    .load(skill.getBadgeUrl())
                    .placeholder(R.drawable.skill_badge)
                    .into(iqImage);

        }

    }
}
