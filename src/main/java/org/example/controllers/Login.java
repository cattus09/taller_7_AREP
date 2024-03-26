package org.example.controllers;

import org.example.model.User;
import spark.Session;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.example.services.HashGenerator.hashGenerator;
import static org.example.services.URLReader.trust;
import static spark.Spark.*;

public class Login {

    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>(Map.of(
            "admin", new User("admin", hashGenerator("admin"))
    ));

 
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        secure(getKey(), getKeyPwd(), null, null);
        post("/login", (request, response) -> {
            String username = request.queryParams("username");
            String password = request.queryParams("password");

            if (users.containsKey(username) && users.get(username).getPassword().equals(hashGenerator(password))) {
                Session session = request.session(true);
                session.attribute("username", username);
                trust(getTrust(), getKeyPwd());
                response.redirect("https://ec2-54-224-82-150.compute-1.amazonaws.com:5001/mainpage");
                return null;
            }

            return "ERROR";
        });
    }

    public static Integer getPort() {
        return 5000;
    }

    public static String getKey() {
        return "certificates/ecikeystore.p12";
    }

    public static String getTrust() {
        return  "certificates/myTrustStore";
    }

    public static String getKeyPwd() {
        return  "123456";
    }






}
