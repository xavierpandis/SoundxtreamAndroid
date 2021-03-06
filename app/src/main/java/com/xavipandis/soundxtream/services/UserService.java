package com.xavipandis.soundxtream.services;

import com.xavipandis.soundxtream.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Xavi on 08/02/2017.
 */

public interface UserService {
    @GET("/api/users/{login}")
    Call<User> getUser(
            /**
             * "Bearer [space ]token"
             */
            @Path("login") String login
            //@Header("Authorization") String Authorization
    );

    @GET("/api/account")
    Call<User> getCurrentUser();

    @POST("/api/register/app")
    Call<ResponseBody> registerAccount(@Body User user);

}
