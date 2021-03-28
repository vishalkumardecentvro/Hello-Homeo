package com.example.hellohomeo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitConnection {

    private static String url = "https://api.spacexdata.com/v4/";

    public static SpaceXInterface spaceXInterface = null;

    public static SpaceXInterface getSpaceXInterface  (){
        if(spaceXInterface == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            spaceXInterface = retrofit.create(SpaceXInterface.class);
        }

        return spaceXInterface;
    }

    public interface SpaceXInterface{

        @GET("crew")
        Call<List<CrewMembersModalClass>> getCrewData ();

    }
}
