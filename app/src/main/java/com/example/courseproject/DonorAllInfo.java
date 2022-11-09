package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.courseproject.Data.Users;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DonorAllInfo extends AppCompatActivity {

    ImageView bgDonor;
    TextView nameInfoTv, mobInfoTv,addressInfoTv,lastDonInfoTV,nextDonInfoTV,healthInfoTV,bloodTypeInfoTV;
    Button callButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_donor_all_info);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nameInfoTv = findViewById(R.id.nameInfoTV);
        mobInfoTv = findViewById(R.id.mobInfoTV);
        addressInfoTv = findViewById(R.id.addressInfoTV);
        lastDonInfoTV = findViewById(R.id.lastDonInfoTV);
        nextDonInfoTV = findViewById(R.id.nextDonInfoTV);
        bloodTypeInfoTV = findViewById(R.id.bloodTypeTV);

        healthInfoTV = findViewById(R.id.healthIssuesInfo);
        callButton = findViewById(R.id.callDonorButton);
        bgDonor = findViewById(R.id.bgDonor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            bgDonor.setRenderEffect(RenderEffect.createBlurEffect(10,10, Shader.TileMode.MIRROR));
        }

        Users users = (Users) getIntent().getSerializableExtra("donorsRV");


        LocalDate intervalDate = LocalDate.parse(users.getNextDon());
        LocalDate thisDay = LocalDate.now();
        long range = ChronoUnit.DAYS.between(thisDay, intervalDate);




        nameInfoTv.setText(users.getFullName());
        mobInfoTv.setText(users.getMobNum());
        addressInfoTv.setText(users.getAddress());
        lastDonInfoTV.setText(users.getLastDon());
        nextDonInfoTV.setText(Long.toString(range));
        healthInfoTV.setText(users.getHealthIssues());
        bloodTypeInfoTV.setText(users.getBloodType());


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            callToDonor(mobInfoTv.getText().toString());
            }
        });


    }

    private void callToDonor(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}