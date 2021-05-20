package com.ex.screens;

import com.ex.controllers.ScreenController;
import io.javalin.Javalin;

import java.util.HashMap;

public class HomeScreen {

    Javalin javalin;
    HashMap<String,Object> data;

    public HomeScreen(Javalin javalinApp, HashMap<String,Object> database) {
        javalin = javalinApp;
        data = database;
    }

    public void init() {
        javalin.get("/", ctx->{
            ((ScreenController) (data.get("ScreenController"))).displayHomeScreen(ctx); });

        javalin.get("/EmployeeHome", ctx -> {
            ((ScreenController) (data.get("ScreenController"))).displayEmployeeHome(ctx); });

        javalin.get("/ManagerHome",ctx->{
            ((ScreenController) (data.get("ScreenController"))).displayManagerHome(ctx); });
    }
}
