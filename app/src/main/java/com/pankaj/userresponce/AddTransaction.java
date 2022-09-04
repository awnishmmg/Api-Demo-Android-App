package com.pankaj.userresponce;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.pankaj.userresponce.api.RetrofitClient;
import com.pankaj.userresponce.model.Customer;
import com.pankaj.userresponce.model.GetCustomerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private  RadioGroup rg_type;

    private Spinner sp_customer;
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        rg_type=(RadioGroup)findViewById(R.id.rg_type);
        sp_customer=(Spinner) findViewById(R.id.sp_customer);

        SharedPreferences sp = getSharedPreferences("user_id",MODE_PRIVATE);
        String user_id = sp.getString("uid","");

        //get data from the server
        Call<GetCustomerResponse> call = RetrofitClient.getInstance().getAPI().getCustomerList(user_id);
        call.enqueue(new Callback<GetCustomerResponse>() {
            @Override
            public void onResponse(Call<GetCustomerResponse> call, Response<GetCustomerResponse> response) {

    if(response.isSuccessful()){
        GetCustomerResponse getCustomerResponse = response.body();
        if(getCustomerResponse.isStatus()){
            ArrayList<Customer> customers = getCustomerResponse.getData();

            ArrayList<String> customer_list = new ArrayList<>();

            for(Customer customer : customers){
                customer_list.add(customer.getId(), customer.email);
            }

             ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, customers);
            //set Dropdown ViewResource
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //attach Spinner to adapter
            sp_customer.setAdapter(ad);
            sp_customer.setOnItemSelectedListener(AddTransaction.this);

        }else{
            Toast.makeText(AddTransaction.this, ""+getCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    }

            @Override
            public void onFailure(Call<GetCustomerResponse> call, Throwable t) {
                Toast.makeText(AddTransaction.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void checkType(View view) {
      int radioId = rg_type.getCheckedRadioButtonId();
        RadioButton rd = (RadioButton)findViewById(radioId);
        String type = rd.getText().toString();
        Toast.makeText(this, ""+type, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String customer_name = ad.getItem(position).getName();
        String customer_id = ad.getItem(position).getId();
        Toast.makeText(this, ""+customer_id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}