package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

    private static ApplicationContext ctx;

    public static ApplicationContext getContext(){
        if (ctx == null){
            ctx = new ClassPathXmlApplicationContext("spring/daoBeans.xml");
        }

        return ctx;
    }
}
