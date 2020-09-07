package com.example.gadsleaderboardapp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;

public class HoursFragment extends Fragment {

    private GadsViewModel mViewModel;
    private RecyclerView mRecyclerView;

    private static final String TAG = "HoursFragment";

    public static HoursFragment newInstance() {
        return new HoursFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hours_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_hours);

        if(!MainActivity.isConnected){
            View parentLayout = view.findViewById(R.id.frame);
            Snackbar.make(parentLayout,"Gads is trying to connect to internet", LENGTH_LONG)
                    .show();
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mViewModel = ViewModelProviders.of(this).get(GadsViewModel.class);
        mViewModel.getDataHours().observe((LifecycleOwner) getContext(), hours -> {
            Log.d(TAG, "onChanged: getting skills");
            HoursAdapter adapter = new HoursAdapter(hours);
            Log.d(TAG, "onChanged: "+hours.size());
            mRecyclerView.setAdapter(adapter);
        });

    }

}