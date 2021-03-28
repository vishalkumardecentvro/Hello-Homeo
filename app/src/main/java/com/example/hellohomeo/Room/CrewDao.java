package com.example.hellohomeo.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hellohomeo.CrewMembersModalClass;

import java.util.List;

@Dao
public interface CrewDao {

    @Insert
    void insertCrewData (CrewEntity crewEntity);

    @Query("delete from `crew table`")
    void deleteAllData();

    @Query("select * from `crew table`")
    LiveData<List<CrewMembersModalClass>> getAllCrewData();
}
