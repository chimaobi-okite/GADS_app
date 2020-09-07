package com.example.gadsleaderboardapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gadsleaderboardapp.services.RetrofitInstance;
import com.example.gadsleaderboardapp.services.Skillservice;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gadsleaderboardapp.SubmitActivity.BUNDLE_KEY;

public class ConfirmationFragment extends Fragment {

    private static final String TAG = "ConfirmationFragment";

    private static final String SUBMIT_LINK = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    private String mEmail;
    private String mFirstName;
    private String mLastName;
    private String mProjectLink;
    private FragmentManager mManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle =  this.getArguments();
        if(bundle != null){

            ArrayList<String> form = bundle.getStringArrayList(BUNDLE_KEY);
            Log.i(TAG, "onCreate: "+ form.size());
            mEmail = form.get(0);
            mFirstName = form.get(1);
            mLastName = form.get(2);
            mProjectLink = form.get(3);
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirm_dialog,container,false);


        ImageView imageView = view.findViewById(R.id.cancel_button);
        TextView confirmTxt = view.findViewById(R.id.yes_btn);
        TextView question = view.findViewById(R.id.question_mark);
        question.setText("?");

        confirmTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });



        return view;
    }

    public  void submitForm(){
        Log.d(TAG, "submitForm: "+ mEmail + mFirstName +mLastName);
            RetrofitInstance.buildService(Skillservice.class).submitData(SUBMIT_LINK,
                    mEmail,mFirstName,mLastName,mProjectLink).enqueue(new Callback<Void>() {
                private FragmentManager mManager;


                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG, "onResponse: submitted successfully");
                        Log.d(TAG, "onResponse: "+response.code());
                        SuccessAlert successAlert = new SuccessAlert();
                        mManager = getParentFragmentManager();
                        FragmentTransaction transaction = mManager.beginTransaction();
                        transaction.replace(R.id.container,successAlert);
                        transaction.commit();
                    }
                    else{
                        FailureAlert failureAlert = new FailureAlert();
                        mManager = getParentFragmentManager();
                        FragmentTransaction transaction = mManager.beginTransaction();
                        transaction.replace(R.id.container,failureAlert);
                        transaction.commit();
                    }

                }


                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                 FailureAlert failureAlert = new FailureAlert();
                    mManager = getParentFragmentManager();
                    FragmentTransaction transaction = mManager.beginTransaction();
                    transaction.replace(R.id.container,failureAlert);
                    transaction.commit();

                }
            });

    }
}
