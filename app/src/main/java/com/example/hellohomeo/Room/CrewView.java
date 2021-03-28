package com.example.hellohomeo.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hellohomeo.CrewMembersModalClass;
import com.example.hellohomeo.Repository.CrewRepository;

import java.util.List;

public class CrewView extends AndroidViewModel {

    private CrewRepository crewRepository;
    private LiveData<List<CrewMembersModalClass>> crewLiveData;

    public CrewView(@NonNull Application application) {
        super(application);
        crewRepository = new CrewRepository(application);
        crewLiveData = crewRepository.allCrewData();
    }

    public void insert(CrewEntity crewEntity){
        crewRepository.insertCrewData(crewEntity);
    }

    public LiveData<List<CrewMembersModalClass>> getAllCrew(){
        return crewLiveData;
    }

    public void DeleteCrewData ( ){
        crewRepository.deleteData();
    }

}
