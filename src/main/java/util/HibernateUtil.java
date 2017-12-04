package util;

import entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory factory;

    public static SessionFactory getFactory(){
        if (factory == null){
        factory = new Configuration()
                .configure("hibernate/hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        }
        return factory;
    }
}
