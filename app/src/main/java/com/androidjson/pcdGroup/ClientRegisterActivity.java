package com.androidjson.pcdGroup;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class ClientRegisterActivity extends AppCompatActivity {

    EditText name,address,addressline1,addressline2,mobileno,country,company_name,pin,email_id,designation;
    Spinner type,state;
    Button submit;
    String Name_Holder, type_Holder, Address_Hoder,Addressline1_Holder,Addressline2_Holder,Mobileno_Holder,State_Holder,Country_Holder, CompanyName_Holder,Pin_Holder, Emailid_Holder, Designation_Holder;
    String finalResult ;
    String HttpURL = "http://pcddata-001-site1.1tempurl.com/ClientDetails.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientregister);

        //Assign Id'S
        name = (EditText) findViewById(R.id.et_name);
        type = (Spinner) findViewById(R.id.spinner);
        state = (Spinner) findViewById(R.id.spinner2);
        address = (EditText) findViewById(R.id.et_address);
        addressline1 = (EditText) findViewById(R.id.et_addressline1);
        addressline2 = (EditText) findViewById(R.id.et_addressline2);
        mobileno=(EditText) findViewById(R.id.et_Mobileno);
        country=(EditText) findViewById(R.id.et_Country);
        company_name = (EditText) findViewById(R.id.et_companyname);
        pin=(EditText) findViewById(R.id.et_Pin);
        email_id = (EditText) findViewById(R.id.et_email);
        designation = (EditText) findViewById(R.id.et_designation);
        submit = (Button) findViewById(R.id.btn_submit);





        //Adding Click Listener on button.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not.
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.
                    UserRegisterFunction(Name_Holder, type_Holder, Address_Hoder, Addressline1_Holder,Addressline2_Holder,Mobileno_Holder,State_Holder,Country_Holder,CompanyName_Holder,Pin_Holder, Emailid_Holder,Designation_Holder);
                }
                else {

                    // If EditText is empty then this block will execute.
                    Toast.makeText(ClientRegisterActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                   Intent intent = new Intent(ClientRegisterActivity.this, DashboardActivity.class);
                   startActivity(intent);
                }

            }
        });
    }

    public void CheckEditTextIsEmptyOrNot(){

        //Checking all EditText Empty or Not.
        Name_Holder = name.getText().toString();
        type_Holder = type.getSelectedItem().toString();
        Address_Hoder = address.getText().toString();
        Addressline1_Holder = addressline2.getText().toString();
        Addressline2_Holder = addressline1.getText().toString();
        Mobileno_Holder = mobileno.getText().toString();
        State_Holder=state.getSelectedItem().toString();
        Country_Holder=country.getText().toString();
        CompanyName_Holder = company_name.getText().toString();
        Emailid_Holder = email_id.getText().toString();
        Pin_Holder = pin.getText().toString();
        Designation_Holder = designation.getText().toString();

        if(TextUtils.isEmpty(Name_Holder) || TextUtils.isEmpty(type_Holder) || TextUtils.isEmpty(Address_Hoder) || TextUtils.isEmpty(CompanyName_Holder) || TextUtils.isEmpty(Emailid_Holder) || TextUtils.isEmpty(Designation_Holder))
        {
            CheckEditText = false;
        }
        else {
            CheckEditText = true ;
        }
    }

    //Register user in database details.
    public void UserRegisterFunction(final String name, final String type, final String address, final String addressline1, final String addressline2, final String mobileno, final String state, final String counntry, final String company_name, final String pin, final String email_id, final String designation){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(ClientRegisterActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(ClientRegisterActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            // Creating Packet database string to HashMap.
            @Override
            protected String doInBackground(String... params) {

                hashMap.put("name",params[0]);

                hashMap.put("type",params[1]);

                hashMap.put("address",params[2]);
                hashMap.put("addressline1",params[3]);
                hashMap.put("addressline2",params[4]);
                hashMap.put("mobileno",params[5]);
                hashMap.put("state",params[6]);
                hashMap.put("country",params[7]);
                hashMap.put("company_name",params[8]);
                hashMap.put("pin",params[9]);
                hashMap.put("email_id",params[10]);

                hashMap.put("designation",params[11]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }

            public void execute(String name, String type, String address, String addressline1, String addressline2, String mobileno, EditText state, String counntry, String company_name, String pin, String email_id, String designation) {
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(name,type,address,addressline1,addressline2,mobileno,state,counntry,company_name,pin,email_id,designation);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ClientRegisterActivity.this);
        builder.setMessage("Are You Sure Want To Exit Register ?");
        builder.setCancelable(true);
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(ClientRegisterActivity.this, DashboardActivity.class);
                startActivity(intent);

                finish();
            }
        });
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
