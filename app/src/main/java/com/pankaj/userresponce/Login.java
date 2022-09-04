package com.pankaj.userresponce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pankaj.userresponce.api.RetrofitClient;
import com.pankaj.userresponce.model.User;
import com.pankaj.userresponce.model.UserLoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         et_email=(EditText) findViewById(R.id.et_email);
         et_password=(EditText) findViewById(R.id.et_password);

         SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
         String spemail=sp.getString("uemail","");
         if(!spemail.equals("")){
             Intent i=new Intent(getApplicationContext(),Dashboard.class);
             startActivity(i);
             finish();
         }


    }
    public void UserLogin(View view) {
        String email=et_email.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        if (email.isEmpty() && password.isEmpty()){
            et_email.setError("Required");
            et_email.requestFocus();
            et_password.setError("Required");
            et_password.requestFocus();
            return;
        }
        Call<UserLoginResponse> call= RetrofitClient.getInstance().getAPI().login(email,password);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
              if (response.isSuccessful()){
                  UserLoginResponse userLoginResponse=response.body();
                  if (userLoginResponse.isStatus()){
                      Toast.makeText(Login.this, ""+userLoginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                      // recieve data from server and api

                      ArrayList<User> users= userLoginResponse.getData();
//                      String uname=user.get(0).getName();
//                      String uemail=user.get(0).getEmail();
//                      String upass=user.get(0).getPassword();
//                      String token=user.get(0).getToken();
//                      Toast.makeText(Login.this, "name"+uname, Toast.LENGTH_SHORT).show();

                      SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
                      SharedPreferences.Editor editor=sp.edit();

                      editor.putString("uid",users.get(0).getId());

                      editor.putString("uname",users.get(0).getName());

                      editor.putString("uemail",users.get(0).getEmail());

                      editor.putString("upassword",users.get(0).getPassword());

                      editor.putString("umobile",users.get(0).getMobile());

                      editor.putString("udate_time",users.get(0).date_time);
                      editor.putString("ustatus",users.get(0).getStatus());
                      editor.putString("utoken",users.get(0).getToken());
                      editor.commit();

                      Intent i=new Intent(getApplicationContext(),Dashboard.class);
                      startActivity(i);
                      finish();





                  }else{
                      Toast.makeText(Login.this, ""+userLoginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}