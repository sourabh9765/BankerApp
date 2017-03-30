package com.example.omkard.bankerapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by OmkarD on 3/26/2017.
 */

public class StudentModel implements Parcelable {

    int id;
    String name;
    String password;
    String cnfpassword;
    String email;
    String dob;


    public StudentModel(int anInt, String string, String cursorString, String s){}
    public StudentModel(){}

    public StudentModel(String name,String email,String dob, String password, String cnfpassword) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.cnfpassword = cnfpassword;
    }

    public StudentModel(int id, String name,  String email, String dob,String password, String cnfpassword) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.cnfpassword = cnfpassword;
        this.email = email;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnfpassword() {
        return cnfpassword;
    }

    public void setCnfpassword(String cnfpassword) {
        this.cnfpassword = cnfpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public static Creator<StudentModel> getCREATOR() {
        return CREATOR;
    }

    protected StudentModel(Parcel in) {
    }

    public static final Creator<StudentModel> CREATOR = new Creator<StudentModel>() {
        @Override
        public StudentModel createFromParcel(Parcel in) {
            return new StudentModel(in);
        }

        @Override
        public StudentModel[] newArray(int size) {
            return new StudentModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
