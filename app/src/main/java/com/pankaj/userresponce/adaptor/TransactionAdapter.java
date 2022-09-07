package com.pankaj.userresponce.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.userresponce.R;
import com.pankaj.userresponce.model.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    public Context context;
    public ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context, ArrayList<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_transaction_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holder.tv_cust_name.setText(transactions.get(position).getCustomer_name());
          holder.tv_cust_mobile.setText(transactions.get(position).getCustomer_mobile());
          holder.tv_cust_amount.setText(transactions.get(position).getAmount());
          holder.tv_cust_type.setText(transactions.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_cust_name,tv_cust_mobile,tv_cust_amount,tv_cust_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_cust_name = (TextView) itemView.findViewById(R.id.tv_cust_name);
            tv_cust_mobile = (TextView) itemView.findViewById(R.id.tv_cust_mobile);
            tv_cust_amount = (TextView) itemView.findViewById(R.id.tv_cust_amount);
            tv_cust_type = (TextView) itemView.findViewById(R.id.tv_cust_type);


        }
    }
}