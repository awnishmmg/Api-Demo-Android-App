package com.pankaj.userresponce;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pankaj.userresponce.api.RetrofitClient;
import com.pankaj.userresponce.model.CreateUserResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText et_name, et_email, et_mobile, et_password;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=(EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_password = (EditText) findViewById(R.id.et_password);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_name.getText().toString().trim();
                String email=et_email.getText().toString().trim();
                String mobile=et_mobile.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                if (name.isEmpty()&& email.isEmpty()&& mobile.isEmpty()){
                    et_email.setError("Required");
                    et_email.requestFocus();
                    et_mobile.setError("Required");
                    et_mobile.requestFocus();
                    et_name.setError("Required");
                    et_name.requestFocus();
                }

                Call<CreateUserResponce> call = RetrofitClient.getInstance().getAPI().createUser(name,email,mobile,password);
                call.enqueue(new Callback<CreateUserResponce>() {
                    @Override

                    public void onResponse(Call<CreateUserResponce> call, Response<CreateUserResponce> response) {
                        if (response.isSuccessful()){

                            CreateUserResponce users=response.body();
                            Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                            Toast.makeText(MainActivity.this, "Suucessfully"+users.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),Login.class);
                            startActivity(i);
                            finish();


                        }
                    }

                    @Override
                    public void onFailure(Call<CreateUserResponce> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                        Toast.makeText(MainActivity.this, "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}