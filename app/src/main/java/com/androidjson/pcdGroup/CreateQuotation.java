package com.androidjson.pcdGroup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class CreateQuotation extends AppCompatActivity {

    private EditText quantity;
    private Button add_client,add_product,preview,date,validdate;
    public TextView client,textdate,textvaliddate;
    public ListView product;

    GlobalVariable globalVariable;
    ProductListAdapter itemsAdapter;
//    ProductInfoAdapter tempAdapter;

    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);

        globalVariable = GlobalVariable.getInstance();

        quantity = (EditText) findViewById(R.id.quantity_et);

        client = (TextView) findViewById(R.id.tv_client);
        product = (ListView) findViewById(R.id.tv_product);
        textdate = (TextView) findViewById(R.id.tv_date);
        textvaliddate = (TextView) findViewById(R.id.tv_uptodate);

        add_client = (Button) findViewById(R.id.btn_client);
        add_product = (Button) findViewById(R.id.btn_product);
        preview = (Button) findViewById(R.id.btn_preview);
        date = (Button) findViewById(R.id.btn_date);
        validdate = (Button) findViewById(R.id.btn_validupto);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);

        textdate.setText(new StringBuilder()
                .append(day).append("-")
                .append(month + 1).append("-")
                .append(year).append(" "));

        itemsAdapter = new ProductListAdapter(this,  globalVariable.items);
        product.setAdapter(itemsAdapter);

        // Client add in database
        add_client.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            Intent intent = new Intent(CreateQuotation.this, SelectClient.class);

            startActivityForResult(intent, 1);
          }
        });

        // Product add in database
        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreateQuotation.this, SelectProduct.class);

                startActivityForResult(intent, 2);

            }
        });

        // Date add
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDialog(DATE_PICKER_ID);
            }
        });

        // Valid up to Date
        validdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });


        // Preview add in database
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(CreateQuotation.this, Invoice.class);
            //customer
            intent.putExtra("ClientInfo", globalVariable.globalClient);

            //product
            int itemsCount = 0;
              for (ProductInfoAdapter pradap: globalVariable.items){

                String[] glstr = globalVariable.PrdList.get(itemsCount++);
                if(glstr != null)
                  glstr[4] =  pradap.getAmount();
            }
            intent.putExtra("ProductInfo", globalVariable.PrdList);
//                intent.putExtra("proqunt", quantity.getText());
            intent.putExtra("date", textdate.getText());
            intent.putExtra("validdate", textvaliddate.getText());

            startActivity(intent);

            }
        });
      quantity.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {

          String str = editable.toString();
          int listSize = globalVariable.items.size() ;
          if(listSize != 0) {
            listSize -= 1;
            ProductInfoAdapter prinfo = globalVariable.items.get(listSize);
            prinfo.setAmount(str);
            itemsAdapter.notifyDataSetChanged();
          }
        }
      });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      if (requestCode == 1) {
        if (resultCode == RESULT_OK) {

          Bundle extras = data.getExtras();
          if (extras != null) {
            // Client Details
            if (extras.containsKey("address")) {
              globalVariable.globalClient[0] = extras.getString("address");
              globalVariable.globalClient[1] = extras.getString("ad1");
              globalVariable.globalClient[2] = extras.getString("ad2");
              globalVariable.globalClient[3] = extras.getString("pin");
              globalVariable.globalClient[4] = extras.getString("state");
              globalVariable.globalClient[5] = extras.getString("country");
              globalVariable.globalClient[6] = extras.getString("company");
              globalVariable.globalClient[7] = extras.getString("name");
              globalVariable.globalClient[7] = extras.getString("name");
            }
            client.setText(globalVariable.globalClient[7]);
          }
        }
      } else {
        if (requestCode == 2) {
          if(resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {

              //Product Details
              if (extras.containsKey("pname")) {
                globalVariable.globalProduct[0] = extras.getString("pname");
                Integer temp = new Integer(extras.getInt("phsn"));
                globalVariable.globalProduct[1] = temp.toString();
                temp = extras.getInt("pgst");
                globalVariable.globalProduct[2] = temp.toString();
                temp = extras.getInt("pprice");
                globalVariable.globalProduct[3] = temp.toString();


                ProductInfoAdapter tempAdapter = new ProductInfoAdapter();
                tempAdapter.setName(globalVariable.globalProduct[0]);
                globalVariable.items.add(tempAdapter);
                itemsAdapter.notifyDataSetChanged();

                String[] strpr = globalVariable.globalProduct.clone();
                globalVariable.PrdList.add(strpr);
              }
            }
          }
        }
      }
    }
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            textvaliddate.setText(new StringBuilder()
                    .append(day).append("-")
                    .append(month + 1).append("-")
                    .append(year).append(" "));
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
