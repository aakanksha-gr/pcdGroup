package com.androidjson.pcdGroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */
public class ImageUpload  extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton selection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageupload);

        //Assign Id'S
        radioGroup = (RadioGroup)findViewById(R.id.radiomain);

        // Click radio button.
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selection = (RadioButton)findViewById(checkedId);
                if(selection.getText().equals("Upload Image"))
                {
                    FragmentManager fm = ImageUpload.this.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction;
                    Fragment fragment = new UploadImageFragment();
                    fragmentTransaction =
                            fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentview, fragment);
                    fragmentTransaction.commit();

                }
                else if(selection.getText().equals("View Image"))
                {
                    FragmentManager fm = ImageUpload.this.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction;
                    Fragment fragment = new ViewImageFragment();

                    fragmentTransaction =
                            fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentview, fragment);
                    fragmentTransaction.commit();
                }
            }
        });
    }
}
