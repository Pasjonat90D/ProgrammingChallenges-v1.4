package com.namegrnerator.model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Andrzej on 2017-03-12.
 */
public class Person {

    private String name;
    private String surname;
    private int numberPhone;
    private LocalDate birthday;
    private String address;

    public Person(String name,String surname,int numberPhone,LocalDate birthday, String address){
        this.name = name;
        this.surname = surname;
        this.numberPhone = numberPhone;
        this.birthday = birthday;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getNumberPhone() {
        return numberPhone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }
}
