package com.example.gadsleaderboardapp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gadsleaderboardapp.adapters.SkillIqAdapter;
import com.example.gadsleaderboardapp.data.GadsViewModel;
import com.example.gadsleaderboardapp.models.Skill;
import com.example.gadsleaderboardapp.services.Skillservice;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SkillIqFragment extends Fragment {

    private GadsViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private LiveData<ArrayList<Skill>> mSkills;
    public Skillservice mSkillservice;

    public static SkillIqFragment newInstance() {
        return new SkillIqFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.skill_iq_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_iq);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mViewModel = ViewModelProviders.of(this).get(GadsViewModel.class);
        mViewModel.getMySkills().observe((LifecycleOwner) getContext(), new Observer<List<Skill>>() {
            @Override
            public void onChanged(List<Skill> skills) {
                Log.d(TAG, "onChanged: getting skills");
                SkillIqAdapter adapter = new SkillIqAdapter(skills);
                Log.d(TAG, "onChanged: "+skills.size());
                mRecyclerView.setAdapter(adapter);
            }
        });

    }

}