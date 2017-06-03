/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.dao;

import com.luizalabs.friendsserver.model.Post;
import java.util.List;

/**
 *
 * @author alanfrank
 */
public interface PostDao {
    public void save(Post post);
    public Post getPost(int id);
    public List<Post> list();
    public List<Post> listBySender(int id);
    public List<Post> listByReceiver(int id);
    public void remove(Post post);
    public void remove(int id);
    public void update(Post post);
}
