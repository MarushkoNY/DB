package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCutil {

    private static final String HOST = "jdbc:mysql://localhost:3306/eshopbase";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    connection = DriverManager.getConnection(HOST, USER, PASS);
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            } catch (ClassNotFoundException ex){
                ex.printStackTrace();
            }

        }
        return connection;
    }

    public static void close(){
        try {
            connection.close();
        } catch (SQLException ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
}
