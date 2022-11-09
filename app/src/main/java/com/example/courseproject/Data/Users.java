package com.example.courseproject.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Users implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fullName")
    private String fullName;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "mobNum")
    private String mobNum;

    @ColumnInfo(name = "lastDon")
    private String lastDon;

    @ColumnInfo(name = "nextDon")
    private String nextDon;

    @ColumnInfo(name = "bloodType")
    private String bloodType;

    @ColumnInfo(name = "healthIssues")
    private String healthIssues;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getLastDon() {
        return lastDon;
    }

    public void setLastDon(String lastDon) {
        this.lastDon = lastDon;
    }

    public String getNextDon() {
        return nextDon;
    }

    public void setNextDon(String nextDon) {
        this.nextDon = nextDon;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getHealthIssues() {
        return healthIssues;
    }

    public void setHealthIssues(String healthIssues) {
        this.healthIssues = healthIssues;
    }
}