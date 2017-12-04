package dao;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Set;

public class HibernateImpl implements DAO {

    public User getUserById(int id) {
        SessionFactory factory = HibernateUtil.getFactory();
        Session session = factory.getCurrentSession();
        User user = null;
        try {
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception ex){
            session.getTransaction().rollback();
        } finally {
            factory.close();
        }
        return user;
    }

    public Set<User> getUsers() {
        SessionFactory factory = HibernateUtil.getFactory();
        Session session = factory.getCurrentSession();
        List<User> usersList = null;
        Set<User> userSet = null;
        try {
            session.beginTransaction();
            usersList = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception ex){
            session.getTransaction().rollback();
        } finally {
            factory.close();
        }
        for (User u : usersList){
            userSet.add(u);
        }
        return userSet;
    }

    public void deleteUserById(int id) {
        SessionFactory factory = HibernateUtil.getFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception ex){
            session.getTransaction().rollback();
        } finally {
            factory.close();
        }
    }

    public User getUserByName(String name) {
        SessionFactory factory = HibernateUtil.getFactory();
        Session session = factory.getCurrentSession();
        User user = null;
        List<User> users= null;
        try {
            session.beginTransaction();
             users = session.createQuery("from User name where user.username='"+name+"'").list();
             for (User u : users){
                 user = u;
             }
             session.getTransaction().commit();
        } catch (Exception ex){
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    public void addUser(String name, String password) {
        SessionFactory factory = HibernateUtil.getFactory();
        Session session = factory.getCurrentSession();
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setRating((int)(Math.random()*100));
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception ex){
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }
}
