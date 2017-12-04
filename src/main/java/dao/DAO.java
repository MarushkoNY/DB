package dao;

import entity.User;

import java.util.Set;

public interface DAO {

    public User getUserById(int id);

    public User getUserByName(String name);

    public Set<User> getUsers();

    public void addUser(String name, String password);

    public void deleteUserById(int id);



}
