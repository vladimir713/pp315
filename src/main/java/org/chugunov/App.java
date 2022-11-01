package org.chugunov;

import org.chugunov.configuration.MyConfig;
import org.chugunov.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
//    Communication communication;
    public static void main( String[] args )
    {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<User> allUsers = communication.getAll();
        System.out.println(allUsers);

        String cookie = communication.getCookie().getFirst(communication.getCookie().SET_COOKIE);
        String sessionId = cookie.substring(cookie.indexOf("JSESSIONID="),
                cookie.indexOf(";"));
        System.out.println(cookie);
        System.out.println(sessionId);

        User u = new User(3L, "James", "Brown", (byte) 51);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
//        headers.setContentType(MediaType.APPLICATION_JSON);
        communication.saveUser(u, headers);

        System.out.println(headers);

        allUsers = communication.getAll();
        System.out.println(allUsers);
        User uu = new User(3L, "Thomas", "Shelby", (byte) 51);
        communication.updateUser(uu, headers);
        allUsers = communication.getAll();
        System.out.println(allUsers);
        communication.deleteUser(3L, headers);
    }
}
