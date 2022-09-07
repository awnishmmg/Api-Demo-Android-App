package com.pankaj.userresponce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.pankaj.userresponce.adaptor.TransactionAdapter;
import com.pankaj.userresponce.api.RetrofitClient;
import com.pankaj.userresponce.model.GetTransactionResponse;
import com.pankaj.userresponce.model.Transaction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllTransactionActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ArrayList<Transaction> transactions;
    public int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_transaction);
        recyclerView = (RecyclerView) findViewById(R.id.rv_transactions);
        getTransactionData();
    }

    public void getTransactionData() {
        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        user_id = Integer.parseInt(sp.getString("uid",""));

        //Animation Loader
        //data in loader show
        //data out loader dismiss
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Transaction Loading...");
        progressDialog.show();
        Call<GetTransactionResponse> call = RetrofitClient.getInstance().getAPI().getAllTransactiondata(user_id);
        call.enqueue(new Callback<GetTransactionResponse>() {
            @Override
            public void onResponse(Call<GetTransactionResponse> call, Response<GetTransactionResponse> response) {
                if(response.isSuccessful()){
                    GetTransactionResponse getTransactionResponse = response.body();
                    if(getTransactionResponse.isStatus()){
                        Toast.makeText(GetAllTransactionActivity.this, ""+getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        transactions = getTransactionResponse.getData();
                        TransactionAdapter adapter = new TransactionAdapter(getApplicationContext(),transactions);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setHasFixedSize(true);
                        progressDialog.dismiss();

                    }else{
                        Toast.makeText(GetAllTransactionActivity.this, ""+getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTransactionResponse> call, Throwable t) {
                Toast.makeText(GetAllTransactionActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}