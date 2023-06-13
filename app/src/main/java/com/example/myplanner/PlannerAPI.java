package com.example.myplanner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.FormUrlEncoded;

public interface PlannerAPI {
    @POST("api/token/")
    @FormUrlEncoded
    Call<AuthData> login(@Field("username") String username, @Field("password") String password);

    @POST("api/token/refresh")
    @FormUrlEncoded
    Call<AuthData> refresh(@Field("refresh") String refresh);

    @POST("api/register/")
    @FormUrlEncoded
    Call<RegisterData> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password2") String password2);

    @GET("api/last-week/")
    Call<WeekNoData> lastWeek(@Header("Authorization") String authHeader);

    @GET("api/first-week/")
    Call<WeekNoData> firstWeek(@Header("Authorization") String authHeader);

    @GET("api/week/{week_no}/")
    Call<List<ItemData>> week(@Path("week_no") int week_no, @Header("Authorization") String authHeader);

    @POST("api/item/")
    @FormUrlEncoded
    Call<ItemData> postItem(
            @Field("week_no") int week_no,
            @Field("day_no") int day_no,
            @Field("slot_no") int slot_no,
            @Field("title") String title,
            @Field("text") String text,
            @Field("color") String color,
            @Header("Authorization") String authHeader);

    @PATCH("api/item/{week_no}/{day_no}/{slot_no}/")
    @FormUrlEncoded
    Call<ItemData> patchItem(
            @Path("week_no") int week_no,
            @Path("day_no") int day_no,
            @Path("slot_no") int slot_no,
            @Field("title") String title,
            @Field("text") String text,
            @Field("color") String color,
            @Header("Authorization") String authHeader);

    @DELETE("api/item/{week_no}/{day_no}/{slot_no}/")
    Call<Void> deleteItem(
            @Path("week_no") int week_no,
            @Path("day_no") int day_no,
            @Path("slot_no") int slot_no,
            @Header("Authorization") String authHeader);
}
