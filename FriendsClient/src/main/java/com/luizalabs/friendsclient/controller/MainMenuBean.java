/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsclient.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luizalabs.friendsclient.model.Friend;
import com.luizalabs.friendsclient.model.Post;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author alanfrank
 */
@ManagedBean
@SessionScoped
public class MainMenuBean implements Serializable {

    private Friend friend;
    private Post post;
    private List<Friend> friends;
    
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

    public String preparePost(){
        return "pm:postPage";
    }
    
    public List<Friend> getFriends() {
        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/FriendsServer/api/v1/friends");
        String json = wr.get(String.class);
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<Friend>>(){}.getType());
    }
    
    public List<Post> getPosts() {
        Client c = Client.create();
        WebResource wr = c.resource("http://localhost:8080/FriendsServer/api/v1/posts/sender/11");
        String json = wr.get(String.class);
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<Post>>(){}.getType());
    }
}
