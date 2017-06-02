/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsclient.model;

/**
 *
 * @author alanfrank
 */
public class Friend {
    private int id;
    private String name;
    private String email;
    private String city;
    private String birthDate;

    public Friend(){
    
    }
    
    public Friend(int id, String name, String email, String city, String birthDate){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.birthDate = birthDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }    
    
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id : ").append(this.id).append('\n');
        stringBuilder.append("Name : ").append(this.name).append('\n');
        stringBuilder.append("Email : ").append(this.email).append('\n');
        stringBuilder.append("City : ").append(this.city).append('\n');
        stringBuilder.append("Birth Date : ").append(this.birthDate).append('\n');

        return stringBuilder.toString();
    }
}
