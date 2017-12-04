package dao;

import entity.User;
import util.JDBCutil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JDBCImpl implements DAO {

    public User getUserByName(String name) {
        String query = "SELECT * FROM userbase WHERE user=?;";
        User user = new User();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCutil.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("user"));
                user.setPassword(rs.getString("pass"));
                user.setRating(rs.getInt("rating"));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
                JDBCutil.getConnection().close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return user;
    }

    public User getUserById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();
        String query = "SELECT * FROM USERBASE WHERE user_id = ?;";
        try {
            connection = JDBCutil.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                user.setId(id);
                user.setUsername(rs.getString("user"));
                user.setPassword(rs.getString("pass"));
                user.setRating(rs.getInt("rating"));
            }
        } catch (SQLException ex){
            System.out.println();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
                JDBCutil.close();
            } catch (SQLException ex){
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return user;
    }

    public Set<User> getUsers() {
        Connection connection = null;
        Statement statement  = null;
        ResultSet rs = null;
        String query = "SELECT * FROM userbase;";
        Set<User> users = new HashSet<User>();
        try {
            connection = JDBCutil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                User user = new User(rs.getString("user"),
                        rs.getString("pass"), rs.getInt("rating"));
                user.setId(rs.getInt("user_id"));
                users.add(user);
            }
        } catch (SQLException ex){
            System.out.println(ex);
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
                JDBCutil.close();
            } catch (SQLException ex){
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return users;
    }

    public void deleteUserById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        String query = "DELETE FROM userbase WHERE user_id=?;";
        try {
            connection = JDBCutil.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex);
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
                JDBCutil.close();
            } catch (SQLException ex){
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
    }

    public void addUser(String name, String password) {
        String query = "INSERT INTO userbase (user, pass, rating) VALUES (?,?,?);";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCutil.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setInt(3, (int)(Math.random()*100));
            ps.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
                JDBCutil.getConnection().close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
