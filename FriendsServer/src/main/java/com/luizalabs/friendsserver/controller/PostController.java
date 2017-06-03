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
import javax.ws.rs.core.Response;

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
    public Response newPost(PostHttp postHttp) {
        Post post = new Post();
        
        if(postHttp.getMessage() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo message").build();
        }
        
        if(postHttp.getSendDate() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo sendDate").build();
        }
        
        if(postHttp.getFriendSender() < 1) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo friendSender").build();
        }
        
        if(postHttp.getFriendReceiver()< 1) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo friendReceiver").build();
        }
        
        try {            
            post.setMessage(postHttp.getMessage());
            post.setSendDate(new Util().toDate(postHttp.getSendDate(), "yyyy-MM-dd"));
            Friend friendReceiver = daoFriend.getFriend(postHttp.getFriendReceiver());            
            post.setFriendReceiver(friendReceiver);
            Friend friendSender = daoFriend.getFriend(postHttp.getFriendSender());            
            post.setFriendSender(friendSender); 
            
            dao.save(post); 
            
            String data = "Mensagem salva com sucesso: " + post.toString();
            return Response.status(201).entity(data).build();
        } catch (ParseException e) { 
            return Response.status(500).entity(e.getMessage()).build();
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
    public Response editPost(PostHttp postHttp) {
        Post post = dao.getPost(postHttp.getId());

        if(post == null) {
            return Response.status(404).entity("A mensagem com id: " 
                    + postHttp.getId() + " não existe").build();
        }
        
        if(postHttp.getId() < 1) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo id").build();
        }
        
        if(postHttp.getMessage() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo message").build();
        }
        
        if(postHttp.getSendDate() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo sendDate").build();
        }
        
        if(postHttp.getFriendSender() < 1) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo friendSender").build();
        }
        
        if(postHttp.getFriendReceiver()< 1) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo friendReceiver").build();
        }
        
        try {            
            post.setId(postHttp.getId());
            post.setMessage(postHttp.getMessage());
            post.setSendDate(new Util().toDate(postHttp.getSendDate(), "yyyy-MM-dd"));
            Friend friendReceiver = daoFriend.getFriend(postHttp.getFriendReceiver());
            post.setFriendReceiver(friendReceiver);
            Friend friendSender = daoFriend.getFriend(postHttp.getFriendSender());
            post.setFriendSender(friendSender);                        
            
            dao.update(post);
            
            String data = "Mensagem editada com sucesso: " + post.toString();
            return Response.status(200).entity(data).build();
        } catch (ParseException e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
   
    /**
     *
     * @return
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    public List<PostHttp> listAll() {
        List<PostHttp> postHttpList =  new ArrayList<>();
        List<Post> postList = dao.list();
        
        for (Post p : postList) {
            postHttpList.add(new PostHttp(p.getId(), p.getMessage(), 
                    p.getSendDate().toString(), p.getFriendReceiver().getId(), 
                    p.getFriendSender().getId()));
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
    public Response getPost(@PathParam("id") Integer id) {        
        Post post = dao.getPost(id);
        PostHttp postHttp;
        
        if (post != null) {
            postHttp = new PostHttp(post.getId(), post.getMessage(), 
                    post.getSendDate().toString(), post.getFriendReceiver().getId(), 
                    post.getFriendSender().getId());
            return Response.status(200).entity(postHttp).build();
        } else {
            return Response.status(404).entity("A mensagem com id: " + id + 
                    " não foi encontrada").build();
        }
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
            postHttpList.add(new PostHttp(p.getId(), p.getMessage(), 
                    p.getSendDate().toString(), p.getFriendReceiver().getId(), 
                    p.getFriendSender().getId()));
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
    @Path("/receiver/{id}")
    public List<PostHttp> getPostByReceiver(@PathParam("id") Integer id) {
        List<PostHttp> postHttpList =  new ArrayList<>();
        List<Post> postList = dao.listByReceiver(id);

        for (Post p : postList) {
            postHttpList.add(new PostHttp(p.getId(), p.getMessage(), 
                    p.getSendDate().toString(), p.getFriendReceiver().getId(), 
                    p.getFriendSender().getId()));
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
    public Response deletePost(@PathParam("id") Integer id) {
        if(dao.getPost(id) == null) {
            return Response.status(404).entity("A mensagem com id: " + id + 
                    " não foi encontrada").build();
        }
                    
        try {
            dao.remove(id);
            return Response.status(200).entity("A mensagem com id " + id + 
                    " foi exlcuída com sucesso").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
