package com.androidjson.pcdGroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class SelectProduct  extends AppCompatActivity {

    View rootView;

    Activity mActivity;
    ListView listView;
    ProductCustomListAdapter adapter;
    String HttpURL = "http://pcddata-001-site1.1tempurl.com/fimage.php";
    InputStream is = null;
    String line = null;
    String result = null;
    String[] data;
    ArrayList<String> picNames;
    String recordName;
    List<ProdactEntity> prodactEntities;
    private Activity activity;

    List<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_view);

        prodactEntities = new ArrayList<ProdactEntity>();
        recordName = new String("");
        picNames = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.lstv);

        //Adding ListView Item click Listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

//                Intent intent = new Intent(SelectProduct.this,CreateQuotation.class);
                Intent intent = new Intent();

                // Sending ListView clicked value using intent.
                ProdactEntity pcdata = prodactEntities.get((int) id);
                intent.putExtra("pname", pcdata.gettitle());
                intent.putExtra("phsn", pcdata.getHsncode());
                intent.putExtra("pgst", pcdata.getGst());
                intent.putExtra("pprice", pcdata.getPrice());
                setResult(RESULT_OK, intent);
//                startActivity(intent);

                finish();

            }
        });

        adapter = new ProductCustomListAdapter(this, prodactEntities);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //Allow network in main thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        //Retrieve
        getData();

        //Adepter
        adapter.notifyDataSetChanged();

    }

    private void getData(){

        try {
            URL url = new URL(HttpURL);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            is = new BufferedInputStream(con.getInputStream());

        }catch (Exception e){
            e.printStackTrace();
        }

        //Read in content into String
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null)
            {
                sb.append(line+"\n");
            }

            is.close();
            result = sb.toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        //Parse json data
        try {

            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;

            data = new String[ja.length()];

            for (int i=0; i<ja.length();i++){

                jo=ja.getJSONObject(i);
                String picname = jo.getString("name");
                String urlname = jo.getString("photo");
                Integer price = jo.getInt("price");
                Integer quantity = jo.getInt("quantity");
                Integer hsncode=jo.getInt("hsncode");
                Integer gst=jo.getInt("gst");
                String description=jo.getString("description");
                Integer stock=jo.getInt("stock");
                Integer reorderlevel=jo.getInt("reorderlevel");
                Integer id = jo.getInt("id");

                // Adding Student Id TO IdList Array.
                IdList.add(jo.getString("id").toString());

                picNames.add(picname);
                ProdactEntity e = new ProdactEntity(picname,urlname,price,quantity,hsncode,gst,description,stock,reorderlevel,id);
                prodactEntities.add(e);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
