package com.pankaj.userresponce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pankaj.userresponce.api.RetrofitClient;
import com.pankaj.userresponce.model.AddCustomerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Addcostomers extends AppCompatActivity {
private EditText et_customer_name, et_customer_email, et_customer_mobile,et_customer_address;
private  String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcostomers);
        et_customer_name=(EditText) findViewById(R.id.et_customer_name);
        et_customer_email=(EditText) findViewById(R.id.et_customer_email);
        et_customer_mobile =(EditText) findViewById(R.id.et_customer_mobile);
        et_customer_address=(EditText) findViewById(R.id.et_customer_address);
        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
       user_id =sp.getString("uid","");

    }

    public void CreateCustomer(View view) {

        String c_name=et_customer_name.getText().toString().trim();
        String c_email=et_customer_email.getText().toString().trim();
        String c_mobile=et_customer_mobile.getText().toString().trim();
        String c_address=et_customer_address.getText().toString().trim();

      if (c_name.isEmpty()&& c_email.isEmpty()&&c_mobile.isEmpty()){
          et_customer_email.setError("Requried");
          et_customer_email.requestFocus();
          et_customer_name.setError("required");
          et_customer_name.requestFocus();
          et_customer_mobile.setError("required");
          et_customer_mobile.requestFocus();
          return;
      }

        Call<AddCustomerResponse> call= RetrofitClient.getInstance().getAPI().addCustomer(c_name,c_email,c_mobile,c_address,user_id);

       call.enqueue(new Callback<AddCustomerResponse>() {
           @Override
           public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
               if(response.isSuccessful()){
                   AddCustomerResponse addCustomerResponse=response.body();
                   if (addCustomerResponse.isStatus()){
                       Toast.makeText(Addcostomers.this, ""+addCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                   }else{
                       Toast.makeText(Addcostomers.this, ""+addCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                   }

               }
           }

           @Override
           public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
               Toast.makeText(Addcostomers.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }
}