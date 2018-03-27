package com.androidjson.pcdGroup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class CustomListAdapter extends BaseAdapter {
  private Activity activity;
  private LayoutInflater inflater;
  private List<Entity> entityItems;
  private ImageLoader imageLoader;

  public CustomListAdapter(Activity activity, List<Entity> entityItems) {
    this.activity = activity;
    this.entityItems = entityItems;
    imageLoader = CustomVolleyRequest.getInstance(activity.getApplicationContext())
            .getImageLoader();
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
      convertView = inflater.inflate(R.layout.list_row, null);

    if (imageLoader == null)
      imageLoader = CustomVolleyRequest.getInstance(activity.getApplicationContext())
              .getImageLoader();

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
    Entity m = entityItems.get(position);

    // thumbnail image
    thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

    // title
    title.setText(m.getTitle());

    // price & Quantity
    price.setText("Price: \u20B9 " + String.valueOf(m.getPrice()));
    qunt.setText("Quantity: " + String.valueOf(m.getQuantity()));
    hsncode.setText("Hsncode:" + String.valueOf(m.getHsncode()));
    gst.setText("GST:" + String.valueOf(m.getGst()));
    description.setText("Description:" + String.valueOf(m.getGst()));
    stock.setText("Stock:" + String.valueOf(m.getGst()));
    reorderlevel.setText("Reorderlevel:" + String.valueOf(m.getReorderlevel()));
    return convertView;
  }

}
