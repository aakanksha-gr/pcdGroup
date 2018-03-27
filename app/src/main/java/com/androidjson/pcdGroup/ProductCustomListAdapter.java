package com.androidjson.pcdGroup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by HP on 19-03-2018.
 */

public class ProductCustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ProdactEntity> prodactEntities;

    public ProductCustomListAdapter(Activity activity, List<ProdactEntity> entityItems) {
        this.activity = activity;
        this.prodactEntities = entityItems;

    }

    @Override
    public int getCount() { return prodactEntities.size(); }

    @Override
    public Object getItem(int location) {
        return prodactEntities.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.product_listrow, null);


        //Assign Id'S
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView qunt = (TextView) convertView.findViewById(R.id.qunt);
        TextView hsncode = (TextView) convertView.findViewById(R.id.HSNCode);
        TextView gst = (TextView) convertView.findViewById(R.id.GST);
        TextView description = (TextView) convertView.findViewById(R.id.Descriprion);
        TextView stock = (TextView) convertView.findViewById(R.id.Stock);
        TextView reorderlevel = (TextView) convertView.findViewById(R.id.Reorderlevel);


        // getting movie data for the row
        ProdactEntity m = prodactEntities.get(position);

        // title
        title.setText(m.getTitle());
        // price & Quantity
        price.setText("Price: \u20B9" + String.valueOf(m.getPrice()));
        qunt.setText("Quantity: " + String.valueOf(m.getQuantity()));
        hsncode.setText("Hsncode:" + String.valueOf(m.getHsncode()));
        gst.setText("GST:" + String.valueOf(m.getGst()));
        description.setText("Description:" + String.valueOf(m.getGst()));
        stock.setText("Stock:" + String.valueOf(m.getGst()));
        reorderlevel.setText("Reorderlevel:" + String.valueOf(m.getReorderlevel()));
        return convertView;
    }

}
