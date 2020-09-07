package com.example.gadsleaderboardapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gadsleaderboardapp.services.RetrofitInstance;
import com.example.gadsleaderboardapp.services.Skillservice;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    private static final String TAG = "SubmitActivity";
    private  EditText mFirstNameTxt;
    private EditText mLastNameTxt;
    private  EditText mEmailTxt;
    private  EditText mProjectTxt;

    public static final String BUNDLE_KEY = "send_form";

    private static final String SUBMIT_LINK = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse"
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);


        TextView submitTxt = findViewById(R.id.main_submit);
        Drawable drawable = submitTxt.getBackground();
        if(drawable instanceof GradientDrawable){
            Log.d(TAG, "onCreate: drawable is shapedrawable");
            ((GradientDrawable) drawable).setColor(ContextCompat.getColor(this,R.color.darkOrange));

        }



        ImageView toolbarImage = findViewById(R.id.toolbar_image);
        Glide.with(this).load(R.drawable.gads_header).into(toolbarImage);

        ImageView backArrowImage = findViewById(R.id.back_arrow);
        backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitActivity.super.onBackPressed();
            }
        });

        mFirstNameTxt = findViewById(R.id.first_name);
        mLastNameTxt = findViewById(R.id.last_name);
        mEmailTxt = findViewById(R.id.email);
        mProjectTxt = findViewById(R.id.github_link);

        submitTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });



    }



    public  void submitForm(){
        String email = mEmailTxt.getText().toString().trim();
        String firstName = mFirstNameTxt.getText().toString().trim();
        String lastName = mLastNameTxt.getText().toString().trim();
        String projectLink = mProjectTxt.getText().toString().trim();


        if(!(email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || projectLink.isEmpty())){
            ArrayList<String> formArray = new ArrayList<>();
            formArray.add(email);
            formArray.add(firstName);
            formArray.add(lastName);
            formArray.add(projectLink);

            Bundle bundle = new Bundle();
            bundle.putStringArrayList(BUNDLE_KEY,formArray);
            Log.i(TAG, "submitForm: "+formArray.size());
            ConfirmationFragment fragment = new ConfirmationFragment();
            FragmentManager manager = getSupportFragmentManager();

            fragment.setArguments(bundle);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container,fragment);
            transaction.addToBackStack("Confirm");
            transaction.commit();
        }
        else{
          Toast.makeText(this,"fill all fields",Toast.LENGTH_SHORT).show();
        }
    }
}