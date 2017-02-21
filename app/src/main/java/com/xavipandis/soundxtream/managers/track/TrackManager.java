package com.xavipandis.soundxtream.managers.track;

import android.util.Log;

import com.xavipandis.soundxtream.managers.BaseManager;
import com.xavipandis.soundxtream.managers.TokenStoreManager;
import com.xavipandis.soundxtream.model.Track;
import com.xavipandis.soundxtream.services.TrackService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xavi on 18/02/2017.
 */

public class TrackManager extends BaseManager {
    private static TrackManager ourInstance;
    private List<Track> trackList;
    private Track track;
    private String username;
    private TrackService trackService;

    private TrackManager() {
        trackService = retrofit.create(TrackService.class);
    }

    public static TrackManager getInstance() {
        if(ourInstance == null){
            ourInstance = new TrackManager();
        }

        return ourInstance;
    }

    public Track getTrack(String id) {
        for (Track track : trackList) {
            if (track.getId().toString().equals(id)) {
                return track;
            }
        }

        return null;
    }

    public synchronized void getTracksUser(String username, final TrackCallback trackCallback){
        Call<List<Track>> call = trackService.getTracksUser(username);
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                trackList = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    trackCallback.onSuccess(trackList);
                } else {
                    trackCallback.onFailure(new Throwable("ERROR " + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Log.e("UserLoginManager ", " performtaks->call.enqueue->onResponse err: " + t.toString());
                trackCallback.onFailure(t);
            }
        });
    }

}
