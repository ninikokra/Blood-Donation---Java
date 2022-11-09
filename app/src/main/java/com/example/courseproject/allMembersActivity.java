package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.courseproject.Data.DBClient;
import com.example.courseproject.Data.Users;

import java.util.List;

public class allMembersActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    Spinner spinnerMembers;
    String spinnerClickedItem;
    ImageView bgMembersIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_members);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        bgMembersIMG = findViewById(R.id.bgMembersIMG);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            bgMembersIMG.setRenderEffect(RenderEffect.createBlurEffect(10,10, Shader.TileMode.MIRROR));
        }

        recyclerView = findViewById(R.id.recView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        spinnerMembers = findViewById(R.id.spinnerMembersBloodType);
        String[] spinnerArray = getResources().getStringArray(R.array.spinner_array);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMembers.setAdapter(spinnerAdapter);

        spinnerMembers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerClickedItem = spinnerMembers.getSelectedItem().toString();
                if (spinnerClickedItem.equals("Select Blood Type")){
                    GetAllBlood getAllBlood = new GetAllBlood();
                    getAllBlood.execute();
                }else {
                    GetDataFromBloodType getDataFromBloodType = new GetDataFromBloodType();
                    getDataFromBloodType.execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    class GetDataFromBloodType extends AsyncTask<Void,Void,List<Users>>{

        @Override
        protected List<Users> doInBackground(Void... voids) {
           List<Users> list =
            DBClient.getInstance(getApplicationContext())
                    .getAppDataBase()
                    .usersDAO()
                    .getB(spinnerClickedItem);

            return list;
        }

        @Override
        protected void onPostExecute(List<Users> users) {
            super.onPostExecute(users);
            RVAdapter recAdapter = new RVAdapter(allMembersActivity.this,users);
            recyclerView.setAdapter(recAdapter);

        }

    }
    class GetAllBlood extends AsyncTask<Void,Void,List<Users>>{

        @Override
        protected List<Users> doInBackground(Void... voids) {
            List<Users> list =
                    DBClient.getInstance(getApplicationContext())
                            .getAppDataBase()
                            .usersDAO()
                            .getAll();

            return list;
        }

        @Override
        protected void onPostExecute(List<Users> users) {
            super.onPostExecute(users);
            RVAdapter recAdapter = new RVAdapter(allMembersActivity.this,users);
            recyclerView.setAdapter(recAdapter);

        }

    }

}