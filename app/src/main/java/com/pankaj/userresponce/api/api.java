package com.pankaj.userresponce.api;

import com.pankaj.userresponce.model.CreateUserResponce;
import com.pankaj.userresponce.model.User;
import com.pankaj.userresponce.model.UserLoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface api {

    @FormUrlEncoded
    @POST("createuser.php")
        // String data=>get
    Call<CreateUserResponce> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password
    );



    @FormUrlEncoded
    @POST("userlogin.php")
    Call<UserLoginResponse> login(
            @Field("email") String email,
            @Field ("password") String password

    );
}
