/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.http;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alanfrank
 */
@XmlRootElement
public class FriendHttp {
    
    private int id;
    private String name;
    private String email;
    private String profileLink;
    private String profileImage;
    private String birthDate;

    public FriendHttp(){
    
    }
    
    public FriendHttp(int id, String name, String email, String profileLink, String profileImage, String birthDate){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileLink = profileLink;
        this.profileImage = profileImage;
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

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }    

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
