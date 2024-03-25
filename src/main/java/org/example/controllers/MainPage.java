package org.example.controllers;



import static org.example.services.URLReader.trust;
import static spark.Spark.*;

public class MainPage {

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        secure(getKey(), getKeyPwd(), null, null);
        get("/mainpage", (request, response) -> {
            trust(getTrust(),getKeyPwd());
            response.type("text/html");
            return "<html><head><title>poca logueado</title></head><body><h1>Hola, poca!</h1></body></html>";

        });
    }

    public static Integer getPort() {
        return 5001;
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