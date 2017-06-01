/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.http;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alanfrank
 */
@XmlRootElement
public class PostHttp {
    
    private int id;
    private String message;
    private Date sendDate;
    private int friendSender;
    private int friendReceiver;

    public PostHttp() {
    
    }
    
    public PostHttp(int id, String message, Date sendDate, int friendSender, int friendReceiver) {
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

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
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
