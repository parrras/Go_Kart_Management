package com.gokart.model;

public class GoKartModel {
    private int kartID;
    private String Name;
    private String Kart_Model;
    private String Kart_Type;
    private String Max_Speed;
    private String Current_Status;
    
    // Default constructor
    public GoKartModel() {
    }
    
    // Constructor with fields
    public GoKartModel(String Name, String Kart_Model, String Kart_Type, String Max_Speed, String Current_Status) {
        this.Name = Name;
        this.Kart_Model = Kart_Model;
        this.Kart_Type = Kart_Type;
        this.Max_Speed = Max_Speed;
        this.Current_Status = Current_Status;
    }
    
    // Getters and Setters
    public int getkartID() {
        return kartID;
    }
    
    public void setkartID(int kartID) {
        this.kartID = kartID;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }
    
    public String getKart_Model() {
        return Kart_Model;
    }
    
    public void setKart_Model(String Kart_Model) {
        this.Kart_Model = Kart_Model;
    }
    
    public String getKart_Type() {
        return Kart_Type;
    }
    
    public void setKart_Type(String Kart_Type) {
        this.Kart_Type = Kart_Type;
    }
    
    public String getMax_Speed() {
        return Max_Speed;
    }
    
    public void setMax_Speed(String Max_Speed) {
        this.Max_Speed = Max_Speed;
    }
    
    public String getCurrent_Status() {
        return Current_Status;
    }
    
    public void setCurrent_Status(String Current_Status) {
        this.Current_Status = Current_Status;
    }
    
    
    @Override
    public String toString() {
        return "User [kartID=" + kartID + ", Name=" + Name + ", Kart_Model=" + Kart_Model + ", Kart_Type=" + Kart_Type
                + ", Max_Speed=" + Max_Speed + ", Current_Status=" + Current_Status + "]";
    }
}