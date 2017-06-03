/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.dao;

import com.luizalabs.friendsserver.model.Friend;
import com.luizalabs.friendsserver.model.Post;
import com.luizalabs.friendsserver.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alanfrank
 */
public class FriendDaoImpl implements FriendDao {

    @Override
    public void save(Friend friend) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(friend);
        t.commit();
        session.close();
    }

    @Override
    public Friend getFriend(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Friend friend = (Friend) session.get(Friend.class, id);
        session.close();
        return friend;
    }

    @Override
    public List<Friend> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List list = session.createQuery("from Friend").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void remove(Friend friend) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(friend);
        t.commit();
        session.close();
    }
    
    @Override
    public void remove(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Friend friend = (Friend) session.get(Friend.class, id);
        List<Post> listPost = session.createQuery("from Post where friendSender = " + id 
                + " or friendReceiver = " + id).list();
        
        for (Post p : listPost) {
            session.delete(p);
        }
        
        session.delete(friend);
        t.commit();
        session.close();
    }

    @Override
    public void update(Friend friend) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(friend);
        t.commit();
        session.close();
    }   
}
