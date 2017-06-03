/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsclient.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luizalabs.friendsclient.model.Friend;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alanfrank
 */
public class FriendClient {
    public static final String baseUri = "http://localhost:8080/FriendsServer/api/v1/friends";
      
    public List<Friend> getAllFriends() {
        List<Friend> friends = new ArrayList<>();
        
        try {
            Client c = Client.create();
            WebResource wr = c.resource(baseUri);
            ClientResponse response = wr.accept("application/json").get(ClientResponse.class);
            String json = wr.get(String.class);
            Gson gson = new Gson();
            friends = gson.fromJson(json, new TypeToken<List<Friend>>(){}.getType()); 
            
            if (response.getStatus() != 200) {
		throw new RuntimeException("Falha : Código do Erro: " + response.getStatus());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        
        return friends;
    }
      
    public void postRequest(Friend friend) {
        try {
            Client clientFriend = Client.create();
            WebResource webResource = clientFriend.resource(baseUri);

            String input = "{\"name\": \"" + friend.getName() + "\", "
                    + "\"email\":\"" + friend.getEmail() + "\", "
                    + "\"city\":\"" + friend.getCity() + "\", "
                    + "\"birthDate\":\"" + friend.getBirthDate() + "\"}";

            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").post(ClientResponse.class, input);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Falha : HTTP código de erro : "
                        + response.getStatus());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
      
    public void putRequest(Friend friend) {
        try {
            Client clientFriend = Client.create();
            WebResource webResource = clientFriend.resource(baseUri);

            String input = "{\"id\": \"" + friend.getId()+ "\", "
                    + "\"name\": \"" + friend.getName() + "\", "
                    + "\"email\":\"" + friend.getEmail() + "\", "
                    + "\"city\":\"" + friend.getCity() + "\", "
                    + "\"birthDate\":\"" + friend.getBirthDate() + "\"}";

            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").put(ClientResponse.class, input);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Falha : HTTP código de erro : "
                        + response.getStatus());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteFriend(String id) {        
        try {
            Client c = Client.create();
            WebResource wr = c.resource(baseUri).path(id);
            ClientResponse response = wr.accept("application/json").delete(ClientResponse.class);
            
            if (response.getStatus() != 200) {
		throw new RuntimeException("Falha : Código do Erro: " + response.getStatus());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }        
    }
}
