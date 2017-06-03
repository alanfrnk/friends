/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsclient.controller;

import com.luizalabs.friendsclient.client.FriendClient;
import com.luizalabs.friendsclient.client.PostClient;
import com.luizalabs.friendsclient.model.Friend;
import com.luizalabs.friendsclient.model.Post;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author alanfrank
 */
@ManagedBean
@SessionScoped
public class MainController {

    private int operation;
    private int friendSelected;
    private Friend friend;
    private Post post;
    private DataModel friends;
    private DataModel posts;
    private DataModel postsDialog;
    
    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void prepareFriend(int operation){
        if (operation == 1)
            friend = new Friend();
        else
            friend = (Friend) friends.getRowData();
        
        this.operation = operation;
    }
    
    public String returnPost(){
        Friend f = (Friend)(friends.getRowData());
        this.posts = new ListDataModel(new PostClient()
                .getAllPostsByFriend(String.valueOf(f.getId())));
        return "pm:postPage";
    }
    
    public String returnDialogPost(){
        return "";
    }
    
    public DataModel getFriends() {
        friends = new ListDataModel(new FriendClient().getAllFriends());
        return friends;
    }
    
    public DataModel getPostsDialog() {
        postsDialog = new ListDataModel(new PostClient().getAllPosts());
        return postsDialog;
    }
    
    public DataModel getPosts() {
        return posts;
    }
    
    public void saveFriend() {
        if (operation == 1)
            new FriendClient().postRequest(friend);
        else
            new FriendClient().putRequest(friend);
    }
    
    public void deleteFriend() {
        Friend f = (Friend)(friends.getRowData());
        new FriendClient().deleteFriend(String.valueOf(f.getId()));
    }
    
    public void deletePostDialog() {
        Post p = (Post)(postsDialog.getRowData());
        new PostClient().deletePost(String.valueOf(p.getId()));
    }
}
