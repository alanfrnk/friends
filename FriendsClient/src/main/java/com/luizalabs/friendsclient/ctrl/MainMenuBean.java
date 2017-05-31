/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsclient.ctrl;

import com.luizalabs.friendsclient.model.Friend;
import com.luizalabs.friendsclient.model.Post;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author alanfrank
 */
@ManagedBean(name = "mainMenu")
@SessionScoped
public class MainMenuBean implements Serializable {

    private Friend friend;
    private Post post;

    public MainMenuBean() {

    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public void prepareFriend(ActionEvent actionEvent){
        friend = new Friend();
    }
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void preparePost(ActionEvent actionEvent){
        post = new Post();
    }
}
