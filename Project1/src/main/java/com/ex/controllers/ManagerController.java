package com.ex.controllers;

import com.ex.pojos.Manager;
import com.ex.services.ManagerService;
import io.javalin.http.Context;

public class ManagerController {
    private ManagerService managerService;
    private String currentUser = null;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    public void logInManager(Context ctx) {
        String userName = ctx.formParam("userName");
        String password = ctx.formParam("password");
        if (managerService.logIn(userName, password)) {
            currentUser = userName;
            ctx.render("/public/ManagerHome.html");
        } else {
            ctx.render("/public/ManagerLogIn.html");
        }
    }

    public boolean isLoggedIn() {
        if (currentUser == null) {
            return false;
        }
        return true;
    }

    public void getManagerInformation(Context ctx) {
        Manager currentManager = managerService.getDao().getManager(currentUser);
        ctx.json(currentManager);
    }

    public String getCurrentManager() {
        return currentUser;
    }


    public void logOut() {
        currentUser = null;
    }
}
