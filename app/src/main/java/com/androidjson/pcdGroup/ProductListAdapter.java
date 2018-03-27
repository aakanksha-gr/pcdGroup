package com.androidjson.pcdGroup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 21-03-2018.
 */

public class ProductListAdapter extends BaseAdapter {
  private Activity activity;
  private LayoutInflater inflater;
  private List<ProductInfoAdapter> entityItems;


  public ProductListAdapter(Activity activity, List<ProductInfoAdapter> entityItems) {
    this.activity = activity;
    this.entityItems = entityItems;

  }

  @Override
  public int getCount() { return entityItems.size(); }

  @Override
  public Object getItem(int location) {
    return entityItems.get(location);
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
      convertView = inflater.inflate(R.layout.productselect, null);

    //Assign Id'S
    TextView title = (TextView) convertView.findViewById(R.id.productname);
    TextView price = (TextView) convertView.findViewById(R.id.amount);

    // getting movie data for the row
    ProductInfoAdapter m = entityItems.get(position);

    // title
    title.setText(m.getName());

    // price & Quantity
    price.setText(m.getAmount());

    return convertView;
  }
}
