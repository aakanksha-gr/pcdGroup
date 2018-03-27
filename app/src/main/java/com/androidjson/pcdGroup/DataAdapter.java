package com.androidjson.pcdGroup;

/**
 * @author Grasp
 *  @version 1.0 on 09-03-2018.
 */

public class DataAdapter {

    String name;
    String type;
    String address;
    String addressline1;
    String addressline2;
    String mobileno;
    String state;
    String country;
    String company_name;
    String pin;
    String emailid;
    String designation;


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getAddressline1(){

        return  addressline1;
    }
    public  void setaddresline1 (String addressline1){

        this.addressline1= addressline1;
    }


    public String getAddressline2(){

        return  addressline2;
    }
    public  void setAddressline2 (String addressline2){

        this.addressline2= addressline2;
    }

    public String getMobileno(){

        return  mobileno;
    }
    public  void setMobileno (String mobileno){

        this.mobileno= mobileno;
    }

    public String getState(){

        return  state;
    }
    public  void setState (String state){

        this.state= state;
    }

    public String getcountry(){

        return  country;
    }
    public  void setCountry (String country){

        this.country= country;
    }

    public String getCompanyname() {

        return company_name;
    }

    public void setCompanyname(String companyname) {

        this.company_name = companyname;
    }

    public String getPin(){

        return  pin;
    }

    public  void  setPin(String pin){

        this.pin=pin;
    }

    public String getEmailid() {

        return emailid;
    }

    public void setEmailid(String emailid) {

        this.emailid = emailid;
    }

    public String getDesignation() {

        return designation;
    }

    public void setDesignation(String designation) {

        this.designation = designation;
    }

}
