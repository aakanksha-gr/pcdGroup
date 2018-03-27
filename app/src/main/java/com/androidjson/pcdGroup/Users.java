package com.androidjson.pcdGroup;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class Users {

    Context context;

    public void removUser(){
        sharedPreferences.edit().clear().commit();
    }

    public String getEmail() {
        sharedPreferences.getString("userdata","");
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
        sharedPreferences.edit().putString("userdata",email).commit();
    }

    private String Email;

    private String Password;

    SharedPreferences sharedPreferences;

    public Users(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userinfo",context.MODE_PRIVATE);

    }

    public void setSharedTrue(){
        sharedPreferences.edit().putBoolean("logged",true).apply();
    }

    public void getPassword(){
        sharedPreferences.edit().putBoolean("logged",true).apply();
    }

    public boolean getSharedTrue()
    {
        if(sharedPreferences.getBoolean("logged",false)){
            return true;
        }
        return false;
    }
}
