package dao;

import dao.DAO;
import entity.User;

import java.util.Set;

public class DAOContext {

    private DAO dao;

    public DAOContext(DAO dao){
        this.dao = dao;
    }
    public DAOContext(){}

    public void addUser(String username, String password){
        dao.addUser(username, password);
    }

    public User getUserByName(String username){
        User user = dao.getUserByName(username);
        return user;
    }

    public User getUserById(int id){
        User user = dao.getUserById(id);
        return user;
    }

    public Set<User> getUsers(){
        Set<User> users = dao.getUsers();
        return users;
    }

    public void deleteUserById(int id){
        dao.deleteUserById(id);
    }
}
