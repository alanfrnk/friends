/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.controller;

import com.luizalabs.friendsserver.dao.FriendDao;
import com.luizalabs.friendsserver.dao.FriendDaoImpl;
import com.luizalabs.friendsserver.dao.PostDao;
import com.luizalabs.friendsserver.dao.PostDaoImpl;
import com.luizalabs.friendsserver.http.PostHttp;
import com.luizalabs.friendsserver.model.Friend;
import com.luizalabs.friendsserver.model.Post;
import com.luizalabs.friendsserver.util.Util;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author alanfrank
 */
@Path("/posts")
public class PostController {
    
    private final PostDao dao = new PostDaoImpl();
    private final FriendDao daoFriend = new FriendDaoImpl();

    /**
     *
     * @param postHttp
     * @return
     */
    @POST	
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public String newPost(PostHttp postHttp) {
        Post post = new Post();

        try {            
            post.setMessage(postHttp.getMessage());
            post.setSendDate(new Util().toDate(postHttp.getSendDate(), "yyyy-MM-dd"));
            Friend friendReceiver = daoFriend.getFriend(postHttp.getFriendReceiver());            
            post.setFriendReceiver(friendReceiver);
            Friend friendSender = daoFriend.getFriend(postHttp.getFriendSender());            
            post.setFriendSender(friendSender); 
            
            dao.save(post); 
            
            return "Registro cadastrado com sucesso!"; 
        } catch (ParseException e) { 
            return "Erro ao cadastrar um registro " + e.getMessage();
        }
    }
    
    /**
     *
     * @param postHttp
     * @return
     */
    @PUT
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")	
    public String editPost(PostHttp postHttp) {
        Post post = new Post();

        try {            
            post.setId(postHttp.getId());
            post.setMessage(postHttp.getMessage());
            post.setSendDate(new Util().toDate(postHttp.getSendDate(), "yyyy-MM-dd"));
            Friend friendReceiver = daoFriend.getFriend(postHttp.getFriendReceiver());
            post.setFriendReceiver(friendReceiver);
            Friend friendSender = daoFriend.getFriend(postHttp.getFriendSender());
            post.setFriendSender(friendSender);                        
            
            dao.update(post);
            
            return "Registro alterado com sucesso!";
        } catch (ParseException e) {
            return "Erro ao alterar o registro " + e.getMessage();
        }
    }
   
    /**
     *
     * @return
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    public List<PostHttp> ListAll() {
        List<PostHttp> postHttpList =  new ArrayList<>();
        List<Post> postList = dao.list();

        for (Post p : postList) {
            postHttpList.add(new PostHttp(p.getId(), p.getMessage(), p.getSendDate().toString(), p.getFriendReceiver().getId(), p.getFriendSender().getId()));
        }

        return postHttpList;
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    public PostHttp getPost(@PathParam("id") Integer id) {
        Post post = dao.getPost(id);

        if(post != null)
            return new PostHttp(post.getId(), post.getMessage(), post.getSendDate().toString(), post.getFriendReceiver().getId(), post.getFriendSender().getId());

        return null;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/sender/{id}")
    public List<PostHttp> getPostBySender(@PathParam("id") Integer id) {
        List<PostHttp> postHttpList =  new ArrayList<>();
        List<Post> postList = dao.listBySender(id);

        for (Post p : postList) {
            postHttpList.add(new PostHttp(p.getId(), p.getMessage(), p.getSendDate().toString(), p.getFriendReceiver().getId(), p.getFriendSender().getId()));
        }

        return postHttpList;
    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")	
    public String deletePost(@PathParam("id") Integer id) {
        try {
            dao.remove(id);
            return "Registro excluido com sucesso!";
        } catch (Exception e) {
            return "Erro ao excluir o registro! " + e.getMessage();
        }
    }
}
