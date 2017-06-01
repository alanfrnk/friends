/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.dao;

import com.luizalabs.friendsserver.model.Friend;
import java.util.List;

/**
 *
 * @author alanfrank
 */
public interface FriendDao {
    public void save(Friend friend);
    public Friend getFriend(int id);
    public List<Friend> list();
    public void remove(Friend friend);
    public void remove(int id);
    public void update(Friend friend);
}
