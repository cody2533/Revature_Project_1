package com.ex;

import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        Logger javalinLogger = Logger.getLogger("io.javalin.Javalin");

        mongoLogger.setLevel(org.apache.log4j.Level.FATAL);
        javalinLogger.setLevel(org.apache.log4j.Level.FATAL);



        MyApplication app = new MyApplication();
        app.run();

    }
}
