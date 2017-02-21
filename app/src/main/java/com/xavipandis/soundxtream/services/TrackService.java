package com.xavipandis.soundxtream.services;

import com.xavipandis.soundxtream.model.Track;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Xavi on 08/02/2017.
 */

public interface TrackService {
    @GET("/api/songs/user/{login}")
    Call<List<Track>> getTracksUser(
            @Path("login") String login
    );

}
