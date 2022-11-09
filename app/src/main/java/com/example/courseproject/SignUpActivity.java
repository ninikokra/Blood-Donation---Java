package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.courseproject.Data.DBClient;
import com.example.courseproject.Data.Users;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;


public class SignUpActivity extends AppCompatActivity {

    ImageView bgSignUpIMG;

    EditText nameSUET,mobSUET,addressSUET, lastDonationSUET, nextDonationSUET,healthSUET;
    CheckBox hIssuesSignUpCheckBox;
    Spinner spinnerSU;
    Button submitButton;
    ImageButton dateButton,intervalDateButton;
    String spinnerItem;
    Dialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bgSignUpIMG = findViewById(R.id.bgSignUpIMG);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            bgSignUpIMG.setRenderEffect(RenderEffect.createBlurEffect(10,10, Shader.TileMode.MIRROR));
        }

        nameSUET = findViewById(R.id.nameSignUpET);
        mobSUET = findViewById(R.id.mobSignUpET);
        addressSUET = findViewById(R.id.addressSignUpET);
        lastDonationSUET = findViewById(R.id.lastDonationSUET);
        nextDonationSUET = findViewById(R.id.nextTimeDonationET);
        healthSUET = findViewById(R.id.healthIssuesSUET);
        spinnerSU = findViewById(R.id.spinnerSignUp);
        submitButton = findViewById(R.id.submitSignUpButton);
        dateButton = findViewById(R.id.dateButton);
        intervalDateButton = findViewById(R.id.intervalDateButton);
        hIssuesSignUpCheckBox = findViewById(R.id.hIssuesCheckBox);
        dialog = new Dialog(this);

        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        int day = Integer.parseInt(currentDate.substring(0,2));
        int month = Integer.parseInt(currentDate.substring(3,5));
        int year = Integer.parseInt(currentDate.substring(6,10));

        hIssuesSignUpCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (hIssuesSignUpCheckBox.isChecked()) {
                    healthSUET.setVisibility(View.VISIBLE);
                }
                else {
                    healthSUET.setVisibility(View.INVISIBLE);
                }
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, (android.R.style.Theme_Holo_Dialog),
                        ((view, year, month, dayOfMonth) ->
                            lastDonationSUET.setText(year + "-" + (month+1) + "-" + dayOfMonth)), 2022, 0, 1);
                datePickerDialog.show();
            }
        });

        intervalDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dataPickerDialog= new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (month<10){
                            nextDonationSUET.setText(year + "-0" + (month+1) + "-" + dayOfMonth);
                        }
                        else if (dayOfMonth<10){
                            nextDonationSUET.setText(year + "-" + (month+1) + "-0" + dayOfMonth);
                        }else{
                            nextDonationSUET.setText(year + "-" + (month+1) + "-" + dayOfMonth);

                        }
                    }
                }, year, month-1, day);

                dataPickerDialog.show();

            }
        });


        String[] spinnerArray = getResources().getStringArray(R.array.spinner_array);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSU.setAdapter(arrayAdapter);

        spinnerSU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItem = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameSUET.getText().toString().isEmpty() || mobSUET.getText().toString().isEmpty() ||
                        addressSUET.getText().toString().isEmpty() ||
                        lastDonationSUET.getText().toString().isEmpty() || nextDonationSUET.getText().toString().isEmpty())
                {

                    Toast.makeText(SignUpActivity.this, "Please Fill all Fields!! ", Toast.LENGTH_LONG).show();

                }else if (spinnerItem.equals("Select Blood Type"))
                {
                    Toast.makeText(SignUpActivity.this, "Please Select Your Blood TYPE!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    SaveDonor saveDonor = new SaveDonor();
                    saveDonor.execute();
                    openDialog();

                }
            }
        });


    }

    public void openDialog() {
        dialog.setContentView(R.layout.dialog_item);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button backToMenuButton = dialog.findViewById(R.id.backToMenuButton);
        dialog.show();

        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });



    }

    class SaveDonor extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Users users = new Users();
            users.setFullName(nameSUET.getText().toString());
            users.setAddress(addressSUET.getText().toString());
            users.setMobNum(mobSUET.getText().toString());
            users.setLastDon(lastDonationSUET.getText().toString());
            users.setNextDon(nextDonationSUET.getText().toString());
            users.setBloodType(spinnerItem);
            if (healthSUET.getText().toString().isEmpty()){
                users.setHealthIssues("Nothing");
            }else {
                users.setHealthIssues(healthSUET.getText().toString());
            }


            DBClient.getInstance(getApplicationContext()).getAppDataBase().usersDAO().insert(users);

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(getApplicationContext(), "Donor Successfully Saved! ", Toast.LENGTH_LONG).show();

        }
    }


}