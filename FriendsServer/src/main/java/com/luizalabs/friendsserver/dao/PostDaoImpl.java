/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.dao;

import com.luizalabs.friendsserver.model.Post;
import com.luizalabs.friendsserver.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alanfrank
 */
public class PostDaoImpl implements PostDao {

    @Override
    public void save(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(post);
        t.commit();
        session.close();
    }

    @Override
    public Post getPost(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Post post = (Post) session.load(Post.class, id);
        session.close();
        return post;
    }

    @Override
    public List<Post> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List list = session.createQuery("from Post").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void remove(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.delete(post);
        t.commit();
        session.close();
    }
    
    @Override
    public void remove(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Post post = (Post) session.load(Post.class, id);
        session.delete(post);
        t.commit();
        session.close();
    }

    @Override
    public void update(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.update(post);
        t.commit();
        session.close();
    }
    
}
