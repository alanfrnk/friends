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
public class Post {
    private int id;
    private String message;
    private String sendDate;
    private int friendSender;
    private int friendReceiver;

    public Post() {
    
    }
    
    public Post(int id, String message, String sendDate, int friendReceiver, int friendSender) {
        super();
        this.id = id;
        this.message = message;
        this.sendDate = sendDate;
        this.friendSender = friendSender;
        this.friendReceiver = friendReceiver;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public int getFriendSender() {
        return friendSender;
    }

    public void setFriendSender(int friendSender) {
        this.friendSender = friendSender;
    }

    public int getFriendReceiver() {
        return friendReceiver;
    }

    public void setFriendReceiver(int friendReceiver) {
        this.friendReceiver = friendReceiver;
    }    
}
