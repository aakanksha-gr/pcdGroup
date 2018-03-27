package com.androidjson.pcdGroup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class DashboardActivity extends AppCompatActivity {

    Button LogOut, Client_Register, Client_Details, Image_upload, Quotation;
    TextView EmailShow;
    String EmailHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Assign Id'S
        LogOut = (Button)findViewById(R.id.button);
        Client_Register = (Button)findViewById(R.id.clientregister);
        Client_Details = (Button)findViewById(R.id.clientdetails);
        Image_upload = (Button)findViewById(R.id.imgupload);
        Quotation = (Button)findViewById(R.id.quotation);

        EmailShow = (TextView)findViewById(R.id.EmailShow);

        // intent
        Intent intent = getIntent();
        EmailHolder = intent.getStringExtra(LoginActivity.UserEmail);
        EmailShow.setText(EmailHolder);

        // Click logout button
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Users(DashboardActivity.this).removUser();

                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);

                startActivity(intent);

                finish();

                Toast.makeText(DashboardActivity.this, "Log Out Successfully", Toast.LENGTH_LONG).show();
            }
        });

        // Click client register button
        Client_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, ClientRegisterActivity.class);
                startActivity(intent);


            }
        });

        // Click Client_details button
        Client_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, ClientDetailsActivity.class);

                startActivity(intent);
            }
        });

        // Click image upload button
        Image_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, ImageUpload.class);

                startActivity(intent);
            }
        });

        // Click Quotation button
        Quotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, CreateQuotation.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
        builder.setMessage("Are You Sure Want To Exit ?");
        builder.setCancelable(true);
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
