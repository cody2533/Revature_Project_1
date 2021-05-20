package com.ex.controllers;

import io.javalin.http.Context;

public class ScreenController {
    private ManagerController managerController;
    private EmployeeController employeeController;

    public ScreenController(EmployeeController employeeController, ManagerController managerController){
        this.employeeController = employeeController;
        this.managerController = managerController;
    }

    public void displayEmployeeHome(Context ctx){
        if (employeeController.isLoggedIn()){
            ctx.render("/public/EmployeeHome.html");
        }
        else {
            ctx.redirect("/Employee");
        }
    }

    public void displaySubmitRequest(Context ctx) {
        if (employeeController.isLoggedIn()){
            ctx.render("/public/SubmitRequest.html");
        }
        else {
            displayHomeScreen(ctx);
        }
    }

    public void displayViewRequests(Context ctx) {
        if (employeeController.isLoggedIn()){
            ctx.render("/public/ViewRequests.html");
        }
        else {
            displayHomeScreen(ctx);
        }
    }

    public void displayManagerHome(Context ctx) {
        if (managerController.isLoggedIn()){
            ctx.render("/public/ManagerHome.html");
        }
        else if(employeeController.isLoggedIn()){
            ctx.render("/public/EmployeeHome.html");
        }
        else {
            ctx.redirect("/Manager");
        }
    }

    public void displayManagerRequests(Context ctx) {
        if (managerController.isLoggedIn()){
            ctx.render("/public/ManagerRequests.html");
        }
        else if(employeeController.isLoggedIn()){
            ctx.render("/public/EmployeeHome.html");
        }
        else {
            displayHomeScreen(ctx);
        }
    }

    public void displayHomeScreen(Context ctx) {
        managerController.logOut();
        employeeController.logOut();
        ctx.render("public/index.html");
    }

    public void displayManagerView(Context ctx) {
        if (managerController.isLoggedIn()){
            ctx.render("/public/ManagerView.html");
        }
        else if(employeeController.isLoggedIn()){
            ctx.render("/public/EmployeeHome.html");
        }
        else {
            displayHomeScreen(ctx);
        }
    }
}
