/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsclient.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luizalabs.friendsclient.model.Post;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alanfrank
 */
public class PostClient {
    public static final String baseUri = "http://localhost:8080/FriendsServer/api/v1/posts";
      
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        
        try {
            Client c = Client.create();
            WebResource wr = c.resource(baseUri);
            ClientResponse response = wr.accept("application/json").get(ClientResponse.class);
            String json = wr.get(String.class);
            Gson gson = new Gson();
            posts = gson.fromJson(json, new TypeToken<List<Post>>(){}.getType()); 
            
            if (response.getStatus() != 200) {
		throw new RuntimeException("Falha : Código do Erro: " + response.getStatus());
            }
        } catch (RuntimeException e) {
            String message = e.getMessage();
            System.out.println(e.getMessage());
        }
        
        return posts;
    }
    
    public List<Post> getAllPostsByFriend(String idReceiver) {
        List<Post> posts = new ArrayList<>();
        
        try {
            Client c = Client.create();
            WebResource wr = c.resource(baseUri).path("receiver").path(idReceiver);
            ClientResponse response = wr.accept("application/json").get(ClientResponse.class);
            String json = wr.get(String.class);
            Gson gson = new Gson();
            posts = gson.fromJson(json, new TypeToken<List<Post>>(){}.getType()); 
            
            if (response.getStatus() != 200) {
		throw new RuntimeException("Falha : Código do Erro: " + response.getStatus());
            }
        } catch (RuntimeException e) {
            String message = e.getMessage();
            System.out.println(e.getMessage());
        }
        
        return posts;
    }
      
    public void postRequest(Post post) {
        try {
            Client clientPost = Client.create();
            WebResource webResource = clientPost.resource(baseUri);

            String input = "{\"message\": \"" + post.getMessage()+ "\", "
                    + "\"friendSender\":\"" + post.getFriendSender()+ "\", "
                    + "\"friendReceiver\":\"" + post.getFriendReceiver()+ "\", "
                    + "\"sendDate\":\"" + post.getSendDate()+ "\"}";

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
      
    public void putRequest(Post post) {
        try {
            Client clientPost = Client.create();
            WebResource webResource = clientPost.resource(baseUri);

            String input = "{\"id\": \"" + post.getId()+ "\", "
                    + "\"message\": \"" + post.getMessage()+ "\", "
                    + "\"friendSender\":\"" + post.getFriendSender()+ "\", "
                    + "\"friendReceiver\":\"" + post.getFriendReceiver()+ "\", "
                    + "\"sendDate\":\"" + post.getSendDate()+ "\"}";

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
    
    public void deletePost(String id) {        
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
