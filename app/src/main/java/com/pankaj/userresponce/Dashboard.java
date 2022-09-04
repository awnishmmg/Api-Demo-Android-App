package com.pankaj.userresponce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pankaj.userresponce.api.RetrofitClient;
import com.pankaj.userresponce.model.LogoutUserResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void AddCustomer(View view) {
Intent i=new Intent(getApplicationContext(),Addcostomers.class);
startActivity(i);
finish();
    }

    public void AddTransaction(View view) {

Intent i=new Intent(getApplicationContext(),AddTransaction.class);
startActivity(i);
finish();
    }

    public void gotoprofile(View view) {
    }


    public void Enquiry(View view) {
    }

    public void Updateprofile(View view) {

    }

    public void changepassword(View view) {

    }

    public void Logout(View view) {
        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
        String spToken=sp.getString("utoken","");
        if(!spToken.equals("")){
            Call<LogoutUserResponce> call= RetrofitClient.getInstance().getAPI().logout(spToken);
            call.enqueue(new Callback<LogoutUserResponce>() {
                @Override
                public void onResponse(Call<LogoutUserResponce> call, Response<LogoutUserResponce> response) {
                    if (response.isSuccessful()){
                        LogoutUserResponce logoutUserResponce=response.body();
                        Toast.makeText(Dashboard.this, ""+logoutUserResponce.getMessage(), Toast.LENGTH_SHORT).show();
                        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.clear();
                        editor.commit();

                        Intent i=new Intent(getApplicationContext(),Login.class);
                        startActivity(i);
                        finish();
                    }

                }

                @Override
                public void onFailure(Call<LogoutUserResponce> call, Throwable t) {
                    Toast.makeText(Dashboard.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

}