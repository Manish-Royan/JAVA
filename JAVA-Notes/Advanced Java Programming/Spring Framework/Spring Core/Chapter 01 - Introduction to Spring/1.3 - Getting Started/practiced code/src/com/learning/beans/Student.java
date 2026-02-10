package com.learning.beans;

import java.io.Serializable;

public class Student implements Serializable {

    // 1. Private fields
    private int id;
    private String name;
    private double gpa;

    // 2. No-argument constructor
    public Student() {
    }

    // 3. Getter and Setter methods
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public void display()
    {
    		System.out.println("ID: "+ id);
    		System.out.println("Name: "+ name);
    		System.out.print("GPA: "+ gpa);
    }
}