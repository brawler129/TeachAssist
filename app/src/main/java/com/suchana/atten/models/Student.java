package com.suchana.atten.models;

import com.google.gson.annotations.SerializedName;

public class Student {
   @SerializedName("name")
   public String name;
   @SerializedName("rollno")
   public Integer roll;
   @SerializedName("regdno")
   public Integer regdno;
   @SerializedName("subject1_at")
   public Integer subject1_at;
   @SerializedName("subject2_at")
   public Integer subject2_at;
   @SerializedName("subject3_at")
   public Integer subject3_at;
   @SerializedName("subject4_at")
   public Integer subject4_at;
   @SerializedName("subject5_at")
   public Integer subject5_at;
   @SerializedName("subject6_at")
   public Integer subject6_at;


    public Student(String name, Integer roll,Integer regdno,Integer subject1_at) {
        this.name = name;
        this.roll = roll;
        this.regdno = regdno;
        this.subject1_at = subject1_at;
    }

    public Student(){

    }
    public Integer getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public Integer getRegdno() {
        return regdno;
    }

    public Integer getSubject1_at() {
        return subject1_at;
    }

    public Integer getSubject2_at() {
        return subject2_at;
    }

    public Integer getSubject3_at() {
        return subject3_at;
    }

    public Integer getSubject4_at() {
        return subject4_at;
    }

    public Integer getSubject5_at() {
        return subject5_at;
    }

    public Integer getSubject6_at() {
        return subject6_at;
    }
}
