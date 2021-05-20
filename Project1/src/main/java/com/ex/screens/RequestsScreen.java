package com.ex.screens;

import com.ex.controllers.RequestController;
import com.ex.controllers.ScreenController;
import io.javalin.Javalin;

import java.util.HashMap;

public class RequestsScreen {

    private Javalin javalin;
    private HashMap<String,Object> data;

    public RequestsScreen(Javalin javalinApp, HashMap<String,Object> database) {
        javalin = javalinApp;
        data = database;
    }

    public void init() {
        javalin.get("/Employee/ViewRequests", ctx -> {
            ((ScreenController) data.get("ScreenController")).displayViewRequests(ctx); });

        javalin.get("/Manager/View", ctx -> {
            ((ScreenController) data.get("ScreenController")).displayManagerView(ctx); });

        javalin.post("/Manager/View", ctx -> {
            ctx.redirect("/Manager/View"); });

        javalin.post("/Manager/View/Employee", ctx -> {
            ((RequestController) data.get("RequestController")).getEmployeeRequests(ctx); });

        javalin.get("/Employee/SubmitRequest",ctx-> {
            ((ScreenController) data.get("ScreenController")).displaySubmitRequest(ctx); });

        javalin.post("/Employee/SubmitRequest",ctx->{
            ((RequestController) data.get("RequestController")).createRequest(ctx); });

        javalin.get("/Manager/Requests",ctx->{
            ((ScreenController) data.get("ScreenController")).displayManagerRequests(ctx); });

        javalin.post("/Manager/Requests",ctx->{
            ((RequestController) data.get("RequestController")).updateRequest(ctx); });
    }
}
