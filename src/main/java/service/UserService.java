package service;

import dao.DAOContext;
import entity.User;
import util.SpringUtil;

import java.util.Set;

public class UserService {

    private DAOContext service = (DAOContext) SpringUtil.getContext().getBean("DAOContext");

    public void addUser(String username, String password){
        service.addUser(username,password);
    }

    public User getUserByName(String name){
        User user = service.getUserByName(name);
        return user;
    }
    public User getUserById(int id){
        User user = service.getUserById(id);
        return user;
    }

    public Set<User> getUsers(){
        Set<User> users = service.getUsers();
        return users;
    }

    public void deleteUserById(int id){
        service.deleteUserById(id);
    }
}
