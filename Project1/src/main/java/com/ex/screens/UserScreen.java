package com.ex.screens;

import com.ex.controllers.EmployeeController;
import com.ex.controllers.ManagerController;
import com.ex.controllers.RequestController;
import io.javalin.Javalin;

import java.util.HashMap;

public class UserScreen {
    private Javalin javalin;
    private HashMap<String, Object> data;

    public UserScreen(Javalin javalinApp, HashMap<String, Object> database) {
        javalin = javalinApp;
        data = database;
    }

    public void init() {
        javalin.post("/UserData", ctx -> {
            ((EmployeeController) data.get("EmployeeController")).getEmployeeInformation(ctx);
        });

        javalin.post("/ManagerData", ctx -> {
            ((ManagerController) data.get("ManagerController")).getManagerInformation(ctx);
        });

        javalin.post("/RequestEmployeeData",ctx->{
            ((RequestController) data.get("RequestController")).getEmployeeRequest(ctx); });

        javalin.post("/RequestManagerData", ctx -> {
            ((RequestController) data.get("RequestController")).getAllRequests(ctx);
        });

        javalin.post("/EmployeeData", ctx -> {
            ((EmployeeController) data.get("EmployeeController")).getEmployees(ctx);
        });

    }
}
