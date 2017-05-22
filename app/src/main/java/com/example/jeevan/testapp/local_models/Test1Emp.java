package com.example.jeevan.testapp.local_models;

/**
 * Created by jeevan on 5/20/17.
 */

public class Test1Emp {
    String name;
    String designation;
    String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return "Name: " + name + "\nDesignation: " + designation + "\nPhone: " + phone + "\n";
    }

}
