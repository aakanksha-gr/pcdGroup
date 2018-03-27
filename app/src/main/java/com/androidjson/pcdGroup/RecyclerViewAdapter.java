package com.androidjson.pcdGroup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<DataAdapter> dataAdapters;

    public RecyclerViewAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();

        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        DataAdapter dataAdapter =  dataAdapters.get(position);

        viewHolder.TextViewName.setText(dataAdapter.getName());

        viewHolder.TextViewType.setText(dataAdapter.getType());
        viewHolder.TextViewAddress.setText(dataAdapter.getAddress());
        viewHolder.TextviewAddressline1.setText(dataAdapter.getAddressline1());
        viewHolder.TextviewAddressline2.setText(dataAdapter.getAddressline2());
        viewHolder.TextviewMobileno.setText(dataAdapter.getMobileno());
        viewHolder.TextviewState.setText(dataAdapter.getState());
        viewHolder.TextviewCountry.setText(dataAdapter.getcountry());
        viewHolder.TextViewCompanyName.setText(dataAdapter.getCompanyname());
        viewHolder.TextviewPin.setText(dataAdapter.getPin());
        viewHolder.TextViewEmailID.setText(dataAdapter.getEmailid());
        viewHolder.TextViewDesignation.setText(dataAdapter.getDesignation());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView TextViewName;
        public TextView TextViewType;
        public TextView TextViewAddress;
        public TextView TextviewAddressline1;
        public TextView TextviewAddressline2;
        public TextView TextviewMobileno;
        public TextView TextviewState;
        public TextView TextviewCountry;
        public TextView TextViewCompanyName;
        public TextView TextviewPin;
        public TextView TextViewEmailID;
        public TextView TextViewDesignation;


        public ViewHolder(View itemView) {

            super(itemView);

            TextViewName = (TextView) itemView.findViewById(R.id.tvname) ;
            TextViewType = (TextView) itemView.findViewById(R.id.tvtype) ;
            TextViewAddress = (TextView) itemView.findViewById(R.id.tvaddress) ;
            TextviewAddressline1 = (TextView) itemView.findViewById(R.id.tvaddressline1) ;
            TextviewAddressline2 = (TextView) itemView.findViewById(R.id.tvaddressline2) ;
            TextviewMobileno = (TextView) itemView.findViewById(R.id.tv_Mobileno) ;
            TextviewState = (TextView) itemView.findViewById(R.id.tv_state) ;
            TextviewCountry = (TextView) itemView.findViewById(R.id.tvCountry) ;
            TextViewCompanyName = (TextView) itemView.findViewById(R.id.tv_companyname) ;
            TextviewPin = (TextView) itemView.findViewById(R.id.tvpin) ;
            TextViewEmailID = (TextView) itemView.findViewById(R.id.tvemailid) ;
            TextViewDesignation = (TextView) itemView.findViewById(R.id.tvdesignation) ;

        }
    }
}