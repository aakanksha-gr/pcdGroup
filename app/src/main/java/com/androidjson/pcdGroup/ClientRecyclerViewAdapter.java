package com.androidjson.pcdGroup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 19-03-2018.
 */

public class ClientRecyclerViewAdapter extends RecyclerView.Adapter<ClientRecyclerViewAdapter.ViewHolder> {

    Context context;

    List<ClientDataAdapter> clientDataAdapters;

    public ClientRecyclerViewAdapter(List<ClientDataAdapter> getClientDataAdapter, Context context){

        super();

        this.clientDataAdapters = getClientDataAdapter;
        this.context = context;
    }

    @Override
    public ClientRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clientcardview, parent, false);

        ClientRecyclerViewAdapter.ViewHolder viewHolder = new ClientRecyclerViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClientRecyclerViewAdapter.ViewHolder viewHolder, int position) {

        ClientDataAdapter clientDataAdapter =  clientDataAdapters.get(position);

        viewHolder.TextViewName.setText(clientDataAdapter.getName());

        viewHolder.TextViewAddress.setText(clientDataAdapter.getAddress());

        viewHolder.TextViewCompanyName.setText(clientDataAdapter.getCompanyname());

    }

    @Override
    public int getItemCount() {

        return clientDataAdapters.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView TextViewName;
        public TextView TextViewAddress;
        public TextView TextViewCompanyName;

        public ViewHolder(View itemView) {

            super(itemView);

            TextViewName = (TextView) itemView.findViewById(R.id.tvname) ;
            TextViewAddress = (TextView) itemView.findViewById(R.id.tvaddress) ;
            TextViewCompanyName = (TextView) itemView.findViewById(R.id.tvcompanyname) ;

        }
    }
}