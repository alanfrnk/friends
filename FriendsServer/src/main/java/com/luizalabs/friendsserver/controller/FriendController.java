package com.luizalabs.friendsserver.controller;

import com.luizalabs.friendsserver.dao.FriendDao;
import com.luizalabs.friendsserver.dao.FriendDaoImpl;
import com.luizalabs.friendsserver.http.FriendHttp;
import com.luizalabs.friendsserver.model.Friend;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alanfrank
 */
@Path("/friends")
public class FriendController {

    private final FriendDao dao;

    public FriendController() {
        this.dao = new FriendDaoImpl();
    }

    /**
     *
     * @param friendHttp
     * @return
     */
    @POST	
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response newFriend(FriendHttp friendHttp) {
        Friend friend = new Friend();
        
        if (friendHttp.getName() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo name").build();
        }
        
        if (friendHttp.getBirthDate() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo birthDate").build();
        }
        
        if (friendHttp.getCity() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo city").build();
        }
        
        if (friendHttp.getEmail() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo email").build();
        }

        try { 
            friend.setName(friendHttp.getName());
            friend.setEmail(friendHttp.getEmail());
            friend.setCity(friendHttp.getCity());
            friend.setBirthDate(new Util().toDate(friendHttp.getBirthDate(), "yyyy-MM-dd"));
            
            dao.save(friend); 
            
            String data = "Amigo salvo com sucesso: " + friend.toString();
            return Response.status(201).entity(data).build();
        } catch (ParseException e) { 
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    
    /**
     *
     * @param friendHttp
     * @return
     */
    @PUT
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")	
    public Response editFriend(FriendHttp friendHttp) {
        Friend friend = dao.getFriend(friendHttp.getId());

        if (friend == null) {
            return Response.status(404).entity("O amigo com id: " 
                    + friendHttp.getId() + " não existe").build();
        }
        
        if(friendHttp.getId() < 1) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo id").build();
        }
        
        if (friendHttp.getName() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo name").build();
        }
        
        if (friendHttp.getBirthDate() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo birthDate").build();
        }
        
        if (friendHttp.getCity() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo city").build();
        }
        
        if (friendHttp.getEmail() == null) {
            return Response.status(400)
                    .entity("Por favor, forneça o campo email").build();
        }
        
        try {
            friend.setId(friendHttp.getId());
            friend.setName(friendHttp.getName());
            friend.setEmail(friendHttp.getEmail());
            friend.setCity(friendHttp.getCity());
            friend.setBirthDate(new Util().toDate(friendHttp.getBirthDate(), "yyyy-MM-dd"));           
            
            dao.update(friend);
            
            String data = "Amigo editado com sucesso: " + friend.toString();
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
    public List<FriendHttp> listAll() {
        List<FriendHttp> friendHttpList =  new ArrayList<>();
        List<Friend> friendList = dao.list();

        for (Friend f : friendList) {
            friendHttpList.add(new FriendHttp(f.getId(), f.getName(), 
                    f.getEmail(), f.getCity(), f.getBirthDate().toString()));
        }

        return friendHttpList;
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    public Response getFriend(@PathParam("id") Integer id) {
        Friend friend = dao.getFriend(id);
        FriendHttp friendHttp;
        
        if (friend != null) {
            friendHttp = new FriendHttp(friend.getId(), friend.getName(),
                    friend.getEmail(), friend.getCity(), friend.getBirthDate().toString());
            return Response.status(200).entity(friendHttp).build();
        } else {
            return Response.status(404).entity("O amigo com id: " + id + 
                    " não foi encontrado").build();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")	
    public Response delete(@PathParam("id") Integer id) {
        if (dao.getFriend(id) == null) {
            return Response.status(404).entity("O amigo com id: " + id + 
                    " não foi encontrado").build();
        }
        
        try {
            dao.remove(id);
            return Response.status(200).entity("O amigo com id " + id + 
                    " foi exlcuído com sucesso").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
