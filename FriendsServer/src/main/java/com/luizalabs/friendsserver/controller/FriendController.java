package com.luizalabs.friendsserver.controller;

import com.luizalabs.friendsserver.dao.FriendDao;
import com.luizalabs.friendsserver.dao.FriendDaoImpl;
import com.luizalabs.friendsserver.http.FriendHttp;
import com.luizalabs.friendsserver.model.Friend;
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

    FriendDao dao = new FriendDaoImpl();

    @POST	
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public String Post(FriendHttp friendHttp) {
        Friend friend = new Friend();

        try { 
            friend.setName(friendHttp.getName());
            friend.setEmail(friendHttp.getEmail());
            friend.setCity(friendHttp.getCity());
            friend.setBirthDate(friendHttp.getBirthDate()); 
            dao.save(friend); 
            return "Registro cadastrado com sucesso!"; 
        } catch (Exception e) { 
            return "Erro ao cadastrar um registro " + e.getMessage();
        }
    }
    
    @PUT
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")	
    public String Put(FriendHttp friendHttp) {
        Friend friend = new Friend();

        try {
            friend.setId(friendHttp.getId());
            friend.setName(friendHttp.getName());
            friend.setEmail(friendHttp.getEmail());
            friend.setCity(friendHttp.getCity());
            friend.setBirthDate(friendHttp.getBirthDate());           
            dao.update(friend);
            return "Registro alterado com sucesso!";
        } catch (Exception e) {
            return "Erro ao alterar o registro " + e.getMessage();
        }
    }
   
    @GET
    @Produces("application/json; charset=UTF-8")
    public List<FriendHttp> ListAll() {
        List<FriendHttp> friendHttpList =  new ArrayList<>();
        List<Friend> friendList = dao.list();

        for (Friend f : friendList) {
            friendHttpList.add(new FriendHttp(f.getId(), f.getName(), f.getEmail(), f.getCity(), f.getBirthDate()));
        }

        return friendHttpList;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    public FriendHttp GetFriend(@PathParam("id") Integer id) {
        Friend friend = dao.getFriend(id);

        if(friend != null)
            return new FriendHttp(friend.getId(), friend.getName(), friend.getEmail(), friend.getCity(), friend.getBirthDate());

        return null;
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")	
    public String Delete(@PathParam("id") Integer id) {
        try {
            dao.remove(id);
            return "Registro excluido com sucesso!";
        } catch (Exception e) {
            return "Erro ao excluir o registro! " + e.getMessage();
        }
    }
}
