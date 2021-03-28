package com.example.hellohomeo;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellohomeo.Room.CrewEntity;
import com.example.hellohomeo.Room.CrewView;
import com.wessam.library.NetworkChecker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView crewRecycler;
    LinearLayoutManager layoutManager;
    CrewAdapter crewAdapter;
    List<CrewMembersModalClass> crewList = new ArrayList<>();
    CrewView crewView;
    private Dialog deleteDataDialog;

    private Button deleteButton, refreshBUtton, loadOfflineDataButton;
    public Button delteButtonDialog;
    private ImageView noInternetImage, emptyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crewRecycler = findViewById(R.id.crewMembersRecyclerView);
        deleteButton = findViewById(R.id.deleteDataButton);
        refreshBUtton = findViewById(R.id.refreshButton);
        loadOfflineDataButton = findViewById(R.id.loadButton);
        noInternetImage = findViewById(R.id.offlineImage);
        emptyImage = findViewById(R.id.emptyImage);

        crewView = ViewModelProviders.of(this).get(CrewView.class);

        if (checkInternet())
            fetchCrewData();
        else {
            Toast.makeText(this, "You are offline", Toast.LENGTH_SHORT).show();
            noInternetImage.setVisibility(View.VISIBLE);
            loadOfflineDataButton.setVisibility(View.VISIBLE);
            refreshBUtton.setVisibility(View.VISIBLE);
        }

        refreshBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInternet()) {
                    noInternetImage.setVisibility(View.GONE);
                    loadOfflineDataButton.setVisibility(View.GONE);
                    refreshBUtton.setVisibility(View.GONE);
                    emptyImage.setVisibility(View.GONE);
                    deleteButton.setVisibility(View.GONE);
                    fetchCrewData();
                } else {
                    Toast.makeText(MainActivity.this, "You are Still offline", Toast.LENGTH_SHORT).show();
                }

            }
        });

        loadOfflineDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // logic
                noInternetImage.setVisibility(View.GONE);
                loadOfflineDataButton.setVisibility(View.GONE);
                refreshBUtton.setVisibility(View.GONE);
                fetchOfflineCrewData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDataDialog = new Dialog(MainActivity.this);
                deleteDataDialog.setContentView(R.layout.alert_dialog);
                delteButtonDialog = deleteDataDialog.findViewById(R.id.okayButton);


                delteButtonDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        crewView.DeleteCrewData();
                        deleteButton.setVisibility(View.GONE);
                        emptyImage.setVisibility(View.VISIBLE);
                        refreshBUtton.setVisibility(View.VISIBLE);
                        deleteDataDialog.dismiss();
                    }
                });
                deleteDataDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deleteDataDialog.show();

            }
        });
    }

    private void fetchOfflineCrewData() {
        crewView.getAllCrew().observe(MainActivity.this, new Observer<List<CrewMembersModalClass>>() {
            @Override
            public void onChanged(List<CrewMembersModalClass> crewMembersModalClasses) {
                if (!crewMembersModalClasses.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Showing data stored in room database", Toast.LENGTH_SHORT).show();
                    showDataInRecyclerView(crewMembersModalClasses);
                    deleteButton.setVisibility(View.VISIBLE);
                    emptyImage.setVisibility(View.GONE);
                } else {
                    showDataInRecyclerView(crewMembersModalClasses);
                    emptyImage.setVisibility(View.VISIBLE);
                    refreshBUtton.setVisibility(View.VISIBLE);

                }

            }
        });
    }

    private boolean checkInternet() {

        return NetworkChecker.isNetworkConnected(this);
    }


    private void fetchCrewData() {

        Call<List<CrewMembersModalClass>> crewDataCall = RetrofitConnection.getSpaceXInterface().getCrewData();
        crewDataCall.enqueue(new Callback<List<CrewMembersModalClass>>() {
            @Override
            public void onResponse(Call<List<CrewMembersModalClass>> call, Response<List<CrewMembersModalClass>> response) {
                for (CrewMembersModalClass crew : response.body()) {

                    crewList.add(new CrewMembersModalClass(crew.getName(), crew.getAgency(), crew.getImage(), crew.getWikipedia(), crew.getStatus()));

                    CrewEntity crewEntity = new CrewEntity(crew.getName(), crew.getAgency(), crew.getImage(), crew.getWikipedia(), crew.getStatus());
                    crewView.insert(crewEntity);

                }
                Toast.makeText(MainActivity.this, "Showing data directly from internet", Toast.LENGTH_SHORT).show();

                showDataInRecyclerView(crewList);
            }

            @Override
            public void onFailure(Call<List<CrewMembersModalClass>> call, Throwable t) {
                Log.i("error", t.toString());

            }
        });
    }

    private void showDataInRecyclerView(List<CrewMembersModalClass> crewData) {
        crewAdapter = new CrewAdapter(crewData);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        crewRecycler.setLayoutManager(layoutManager);
        crewRecycler.setAdapter(crewAdapter);
    }
}