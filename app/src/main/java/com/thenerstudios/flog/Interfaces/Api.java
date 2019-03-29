package com.thenerstudios.flog.Interfaces;

import com.thenerstudios.flog.Modal.DefaultResponse;
import com.thenerstudios.flog.Modal.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("address") String address,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userlogin(
            @Field("phone") String phone
    );


}
