package com.ex.controllers;

import com.ex.pojos.Employee;
import com.ex.services.EmployeeService;
import com.mongodb.client.MongoCollection;
import io.javalin.http.Context;
import org.apache.log4j.Logger;
import org.json.JSONArray;

public class EmployeeController {
    EmployeeService service;
    private String currentUser = null;
    Logger javalinLogger = Logger.getLogger("io.javalin.Javalin");

    public EmployeeController(EmployeeService logInService)
    {
        service = logInService;
    }

    public void logInEmployee(Context ctx){
        String userName = ctx.formParam("userName");
        String password = ctx.formParam("password");
        if (service.employeeLogIn(userName, password)) {
            currentUser = userName;
            javalinLogger.info("Logged in as " + userName);
            ctx.render("/public/EmployeeHome.html");
        }
        else {
            javalinLogger.info("Failed log in attempt using " + userName);
            ctx.render("/public/EmployeeLogIn.html");
        }
    }

    /**
     * Sends the current employee
     */
    public void getEmployeeInformation(Context ctx) {
        Employee currentEmployee = service.getDao().getEmployee(currentUser);
        ctx.json(currentEmployee);
    }

    /**
     * Returns the name of the current user
     * @return userName
     */
    public String getCurrentUser(){
        return currentUser;
    }

    /**
     * Check to see if an employee is logged in
     */
    public boolean isLoggedIn(){
        if (currentUser == null){
            return false;
        }
        return true;
    }

    /**
     * Logs out the current employee
     */
    public void logOut() {
        currentUser = null;
    }

    /**
     * Sends a list of all employees
     */
    public void getEmployees(Context ctx) {
        MongoCollection<Employee> employees = service.getEmployees();
        JSONArray response = new JSONArray();
        for (Employee employee : employees.find()) {
            response.put(employee);
        }
        ctx.json(response);
    }

}
