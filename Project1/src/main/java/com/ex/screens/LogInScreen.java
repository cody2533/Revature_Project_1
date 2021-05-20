package com.ex.screens;

import com.ex.controllers.EmployeeController;
import com.ex.controllers.ManagerController;
import io.javalin.Javalin;

import java.util.HashMap;

public class LogInScreen {
    private Javalin javalin;
    private HashMap<String, Object> data;

    public LogInScreen(Javalin javalinApp, HashMap<String,Object> database) {
        javalin = javalinApp;
        data = database;
    }

    public void init() {
        javalin.get("/Employee", ctx -> {
            ctx.render("/public/EmployeeLogIn.html"); });
        javalin.post("/Employee", ctx->{
            ((EmployeeController) data.get("EmployeeController")).logInEmployee(ctx); });

        javalin.get("/Manager",ctx->{
            ctx.render("/public/ManagerLogIn.html"); });
        javalin.post("/Manager",ctx->{
            ((ManagerController) data.get("ManagerController")).logInManager(ctx); });
    }
}
