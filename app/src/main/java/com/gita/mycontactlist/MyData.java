package com.gita.mycontactlist;

/**
 * Created by adv on 4/10/2017.
 */

public class MyData {
    String name ,number,address;
    public MyData(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }
}
