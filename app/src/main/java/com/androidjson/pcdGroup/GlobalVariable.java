package com.androidjson.pcdGroup;

import java.util.ArrayList;

/**
 * Created by HP on 21-03-2018.
 */

public class GlobalVariable {

    //Declare the class singleton class
    private static GlobalVariable myObj;
    public static GlobalVariable getInstance(){
        if(myObj == null){
            myObj = new GlobalVariable();
        }
        return myObj;
    }

    public  String[] globalClient = new String[8];
    public String[] globalProduct = new String[5];

    ArrayList<String[]> PrdList = new ArrayList<String[]>();

    ArrayList<ProductInfoAdapter> items = new ArrayList<ProductInfoAdapter>();;

}
