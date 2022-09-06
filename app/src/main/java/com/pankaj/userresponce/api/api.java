package com.pankaj.userresponce.api;

import com.pankaj.userresponce.model.AddCustomerResponse;
import com.pankaj.userresponce.model.AddTransactionResponse;
import com.pankaj.userresponce.model.CreateUserResponce;
import com.pankaj.userresponce.model.GetCustomerResponse;
import com.pankaj.userresponce.model.LogoutUserResponce;
import com.pankaj.userresponce.model.User;
import com.pankaj.userresponce.model.UserLoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    @FormUrlEncoded
    @POST("logoutuser.php")
    Call<LogoutUserResponce> logout(
            @Field("token") String token
    );
    @FormUrlEncoded
    @POST("addcustomer.php")
    Call<AddCustomerResponse> addCustomer(
            @Field("name") String name,
            @Field("email")String email,
            @Field("mobile") String mobile,
            @Field("address") String address,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("getcustomer.php")
    Call<GetCustomerResponse> getCustomerList(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("addtransaction.php")
    Call<AddTransactionResponse> addTransaction(
          @Field("user_id") int user_id,
          @Field("customer_id") int customer_id,
          @Field("amount") String amount,
          @Field("type") String amount_type,
          @Field("title") String title,
          @Field("description") String description

);



}
