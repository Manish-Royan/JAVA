package com.learning.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student implements Serializable {

  
	@Value("101")
    private int id;
	@Value("ChuChu")
    private String name;
	@Value("4.0f")
    private double gpa;

    // No-argument constructor
    public Student() {
    }

    // Getter and Setter methods
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