/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author alanfrank
 * 
 */
@Entity(name = "Friend")
public class Friend implements Serializable {
    
    @Id
    private int id;    
    private String name;    
    private String email;
    private String profileLink;
    private String profileImage;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the profileLink
     */
    public String getProfileLink() {
        return profileLink;
    }

    /**
     * @param profileLink the profileLink to set
     */
    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    /**
     * @return the profileImage
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     * @param profileImage the profileImage to set
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id : ").append(this.id).append('\n');
        stringBuilder.append("name : ").append(this.name).append('\n');
        stringBuilder.append("email : ").append(this.email).append('\n');
        stringBuilder.append("profileImage : ").append(this.profileLink).append('\n');
        stringBuilder.append("profileLink : ").append(this.profileLink).append('\n');
        stringBuilder.append("birthDate : ")
                .append(new SimpleDateFormat("yyyy-MM-dd")
                        .format(this.birthDate));
        return stringBuilder.toString();
    }
}
