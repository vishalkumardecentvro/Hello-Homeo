package com.example.hellohomeo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hellohomeo.Room.CrewDao;
import com.example.hellohomeo.Room.CrewEntity;

@Database(entities = {CrewEntity.class},version = 1)
public abstract class CrewDatabase extends RoomDatabase {

    private static CrewDatabase crewInstance;
    public abstract CrewDao crewDao();

    public static synchronized CrewDatabase getInstance(Context context){
        if(crewInstance == null){
            crewInstance = Room.databaseBuilder(context.getApplicationContext(),CrewDatabase.class,"crew table")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallBack)
                    .build();
        }

        return crewInstance;
    }

    public static RoomDatabase.Callback roomcallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
