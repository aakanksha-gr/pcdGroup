package com.androidjson.pcdGroup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class UploadImageFragment extends Fragment {

    EditText name,price,quantity,hsncode,gst,description,stock,reorderlevel;
    ImageView imageView;
    Button pickImage, upload;
    Activity mActivity;
    View rootView;

    private Bitmap bitmap;
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    private String KEY_Price = "price";
    private String KEY_Quantity = "quantity";
    private String KEY_HSNCode = "HSNCode";
    private String KEY_GST = "GST";
    private String KEY_Description = "Description";
    private String KEY_Stock = "Stock";
    private String KEY_Reorderlevel = "Reorderlevel";
    private String UPLOAD_URL ="http://pcddata-001-site1.1tempurl.com/upload.php";

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        rootView = inflater.inflate(R.layout.upload_image_layout, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Assign Id'S
        name= (EditText)rootView.findViewById(R.id.name);
        price= (EditText)rootView.findViewById(R.id.price);
        quantity= (EditText)rootView.findViewById(R.id.quantity);
        hsncode= (EditText)rootView.findViewById(R.id.HSNCode);
        gst= (EditText)rootView.findViewById(R.id.GST);
        description= (EditText)rootView.findViewById(R.id.Descriprion);
        stock= (EditText)rootView.findViewById(R.id.Stock);
        reorderlevel= (EditText)rootView.findViewById(R.id.Reorderlevel);
        pickImage= (Button)rootView.findViewById(R.id.pickImgaeButton);
        upload = (Button)rootView.findViewById(R.id.upload);

        imageView = (ImageView)rootView.findViewById(R.id.previewImage);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().length() <= 0)
                {
                    name.setError("Please Enter Name !");
                }
                else if (price.getText().toString().length() <= 0)
                {
                    price.setError("Please Enter Price !");
                }
                else if (quantity.getText().toString().length() <= 0)
                {
                    quantity.setError("Please Enter Quantity !");
                }
                else if (bitmap==null)
                {
                    Toast.makeText(mActivity,"Please Upload Image", Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadImage();

                }
            }
        });
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        return rootView;
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == mActivity.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                imageView.setVisibility(View.VISIBLE);
                bitmap = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(mActivity,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(mActivity,s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(mActivity, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String name1 = name.getText().toString().trim();
                String name2 = price.getText().toString().trim();
                String name3 = quantity.getText().toString().trim();
                String name4 = hsncode.getText().toString().trim();
                String name5= gst.getText().toString().trim();
                String name6= description.getText().toString().trim();
                String name7= stock.getText().toString().trim();
                String name8= reorderlevel.getText().toString().trim();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name1);
                params.put(KEY_Price, name2);
                params.put(KEY_Quantity, name3);
                params.put(KEY_HSNCode,name4);
                params.put(KEY_GST,name5);
                params.put(KEY_Description,name6);
                params.put(KEY_Stock,name7);
                params.put(KEY_Reorderlevel,name8);
                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(mActivity);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

}
