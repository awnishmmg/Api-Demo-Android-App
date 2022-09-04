package com.pankaj.userresponce;

import androidx.appcompat.app.AppCompatActivity;

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
                  Toast.makeText(Login.this, ""+userLoginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                  // how to take data

                 ArrayList<User> user= userLoginResponse.getData();
                 String uname=user.get(0).getName();
                 String uemail=user.get(0).getEmail();
                 String upass=user.get(0).getPassword();
                 String token=user.get(0).getToken();

                  Toast.makeText(Login.this, "name"+uname, Toast.LENGTH_SHORT).show();

              }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}