package com.androidjson.pcdGroup;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class UpdateActivity extends AppCompatActivity {

    String HttpURL = "http://pcddata-001-site1.1tempurl.com/UpdateClientDetails.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText ClientName, ClientAddress, ClientEmail, ClientCompany, ClientDesignation;
    Button UpdateStudent;
    String ClientNameHolder, ClientAddressHolder, ClientEmailHolder, ClientComapnyHolder, ClientDesignationHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ClientName = (EditText)findViewById(R.id.editName);
        ClientAddress = (EditText)findViewById(R.id.editAddress);
        ClientEmail = (EditText)findViewById(R.id.editEmail);
        ClientCompany = (EditText)findViewById(R.id.editCompany);
        ClientDesignation = (EditText)findViewById(R.id.editDesignation);

        UpdateStudent = (Button)findViewById(R.id.UpdateButton);

        // Receive Student ID, Name , Address , Email, etc.. Send by previous ShowSingleRecordActivity.
        ClientNameHolder = getIntent().getStringExtra("Name");
        ClientAddressHolder = getIntent().getStringExtra("Address");
        ClientEmailHolder = getIntent().getStringExtra("Email");
        ClientComapnyHolder = getIntent().getStringExtra("Company");
        ClientDesignationHolder = getIntent().getStringExtra("Designation");

        // Setting Received Student Name, Phone Number, Class into EditText.
        ClientName.setText(ClientNameHolder);
        ClientAddress.setText(ClientAddressHolder);
        ClientEmail.setText(ClientEmailHolder);
        ClientCompany.setText(ClientComapnyHolder);
        ClientDesignation.setText(ClientDesignationHolder);

        // Adding click listener to update button .
        UpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();

                // Sending Student Name, Phone Number, Class to method to update on server.
                StudentRecordUpdate(ClientNameHolder,ClientAddressHolder, ClientEmailHolder, ClientComapnyHolder, ClientDesignationHolder);

            }
        });

    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){

        ClientNameHolder = ClientName.getText().toString();
        ClientAddressHolder = ClientAddress.getText().toString();
        ClientEmailHolder = ClientEmail.getText().toString();
        ClientComapnyHolder = ClientCompany.getText().toString();
        ClientDesignationHolder = ClientDesignation.getText().toString();

    }

    // Method to Update Student Record.
    public void StudentRecordUpdate(String clientNameHolder, final String ID, final String S_Name, final String S_Phone, final String S_Class){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(UpdateActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(UpdateActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {


                hashMap.put("ClientName",params[0]);

                hashMap.put("ClientAddress",params[1]);

                hashMap.put("ClientEmail",params[2]);

                hashMap.put("ClientCompany",params[3]);

                hashMap.put("ClientDesignation",params[4]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(S_Name,S_Phone,S_Class);
    }
}
