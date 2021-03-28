package com.example.hellohomeo.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hellohomeo.CrewDatabase;
import com.example.hellohomeo.CrewMembersModalClass;
import com.example.hellohomeo.Room.CrewDao;
import com.example.hellohomeo.Room.CrewEntity;

import java.util.List;

public class CrewRepository {

    private CrewDao crewDao;
    private LiveData<List<CrewMembersModalClass>> crewList;

    public CrewRepository(Application application) {
        CrewDatabase crewDatabase = CrewDatabase.getInstance(application);
        this.crewDao = crewDatabase.crewDao();
        this.crewList = crewDao.getAllCrewData();
    }

    public void insertCrewData (CrewEntity crewEntity){
        new insertAsyncTask(crewDao).execute(crewEntity);
    }

    public void deleteData (){
        new deleteAsyncTask(crewDao).execute();
    }

    public LiveData<List<CrewMembersModalClass>> allCrewData(){
        return crewList;
    }

    private static class insertAsyncTask extends AsyncTask<CrewEntity,Void,Void> {
        private CrewDao crewDao;

        public insertAsyncTask(CrewDao crewDao) {
            this.crewDao = crewDao;
        }

        @Override
        protected Void doInBackground(CrewEntity... crewEntities) {
            crewDao.insertCrewData(crewEntities[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<CrewEntity,Void,Void> {
        private CrewDao crewDao;

        public deleteAsyncTask(CrewDao crewDao) {
            this.crewDao = crewDao;
        }

        @Override
        protected Void doInBackground(CrewEntity... crewEntities) {
            crewDao.deleteAllData();
            return null;
        }
    }
}
