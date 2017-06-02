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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author alanfrank
 */
@Entity(name = "Post")
public class Post implements Serializable{
    
    @Id
    private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friendSender")
    private Friend friendSender;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friendReceiver")
    private Friend friendReceiver;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date sendDate;
    
    private String message;

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
     * @return the friendSender
     */
    public Friend getFriendSender() {
        return friendSender;
    }

    /**
     * @param friendSender the friendSender to set
     */
    public void setFriendSender(Friend friendSender) {
        this.friendSender = friendSender;
    }

    /**
     * @return the friendReceiver
     */
    public Friend getFriendReceiver() {
        return friendReceiver;
    }

    /**
     * @param friendReceiver the friendReceiver to set
     */
    public void setFriendReceiver(Friend friendReceiver) {
        this.friendReceiver = friendReceiver;
    }

    /**
     * @return the sendDate
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * @param sendDate the sendDate to set
     */
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id : ").append(this.id).append('\n');
        stringBuilder.append("message : ").append(this.message).append('\n');
        stringBuilder.append("sendDate : ")
                .append(new SimpleDateFormat("yyyy-MM-dd")
                        .format(this.sendDate)).append('\n');
        stringBuilder.append("friendSender : ")
                .append(this.friendSender).append('\n');
        stringBuilder.append("friendReceiver : ")
                .append(this.friendReceiver).append('\n');
        return stringBuilder.toString();
    }
}
