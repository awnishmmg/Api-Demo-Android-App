package com.pankaj.userresponce.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

        // 1. Retrofit class
        // 2. static instance
        // 3. static final base_url

        private final  String BASE_URL ="https://myhisab.seeksolution.in/api/";
        private static RetrofitClient instance ;
        private Retrofit retrofit;


        // private constructor initialize retrofit
        private RetrofitClient() {
            // 1. set base-url
            // 2. add converter
            Gson gson=new GsonBuilder().setLenient().create();

            retrofit =new Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        }
        public static  synchronized RetrofitClient getInstance(){
            if (instance==null){
                instance =new RetrofitClient();
            }
            return  instance;
        }
        public api getAPI(){
            return   retrofit.create(api.class);
        }


    }
