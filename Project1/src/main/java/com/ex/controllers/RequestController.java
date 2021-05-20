package com.ex.controllers;

import com.ex.pojos.Request;
import com.ex.services.RequestService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import io.javalin.http.Context;
import org.apache.log4j.Logger;
import org.json.JSONArray;

public class RequestController  {

    private EmployeeController employeeController;
    private ManagerController managerController;
    private RequestService requestService;
    Logger javalinLogger = Logger.getLogger("io.javalin.Javalin");

    public RequestController(RequestService requestService, EmployeeController employeeController, ManagerController managerController) {
        this.requestService = requestService;
        this.employeeController = employeeController;
        this.managerController = managerController;
    }

    /**
     *Creates a request
     */
    public void createRequest(Context ctx) {
        double amount = Double.valueOf(ctx.formParam("amount"));
        String reason = ctx.formParam("reason");
        String userName = employeeController.getCurrentUser();
        Request req = new Request(amount, reason, userName);
        requestService.CreateRequest(req);
        ctx.redirect("/EmployeeHome");
        javalinLogger.info(userName + " created a request for $" + amount);
    }

    /**
     *Gets a specific request
     */
    public void getEmployeeRequest(Context ctx) {
        String currentUser = employeeController.getCurrentUser();
        getRequest(currentUser,ctx);
    }

    /**
     *Posts all requests to the screen
     */
    public void getAllRequests(Context ctx) {
        MongoCollection<Request> requests = requestService.getDao().getRequests();

        JSONArray response = new JSONArray();
        for (Request request : requests.find()) {
            response.put(request);
        }
        ctx.json(response);
    }

    /**
     *Posts all requests by an employee to the screen
     */
    private void getRequest(String employee, Context ctx){
        FindIterable<Request> requests = requestService.getDao().getRequestsByEmployee(employee);

        JSONArray response = new JSONArray();
        for (Request request : requests) {
            response.put(request);
        }
        ctx.json(response);
    }

    /**
     *Approves or denies a request
     */
    public void updateRequest(Context ctx) {
        String userName = ctx.formParam("userName");
        double amount = Double.valueOf(ctx.formParam("amount"));
        String reason = ctx.formParam("reason");
        Request request = new Request(amount, reason, userName);
        String status = ctx.formParam("status");
        String manager = managerController.getCurrentManager();

        if (status.equals("Approve")){
            requestService.updateRequest(request, manager,"Approved");
        }
        else if (status.equals("Deny")){
            requestService.updateRequest(request, manager, "Denied");
        }
        javalinLogger.info(userName + "'s request was " + status + " by " + manager);
        ctx.redirect("/Manager/Requests");
    }

    /**
     *Displays the requests for an employee
     */
    public void getEmployeeRequests(Context ctx) {
        String employeeName = ctx.formParam("employeeName");
        FindIterable<Request> requests = requestService.getDao().getRequestsByEmployee(employeeName);
        String html = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<link rel=\"stylesheet\" href=\"styles.css\">" +
                "<meta_charset=\"UTF-8\">" +
                "<title>View Employee</title>" +
                "</head>" +
                "<body>" +
                "<h2>Requests for " + requests.first().getEmployeeName() + "</h2>" +
                "<a href=\"/ManagerHome\">Home</a><br>" +
                "<table style=\"width:50%\">" +
                "<tr>" +
                "<th>Reason</th>" +
                "<th>Amount</th>" +
                "<th>Status</th>" +
                "<th>Manager</th>" +
                "</tr>";


        // create an array and iterate through it, then return it to the screen to display
        for (Request request : requests) {
            html += "<tr>" +
                    "<td>"+request.getReason()+"</td>" +
                    "<td>"+request.getAmount()+"</td>" +
                    "<td>"+request.getStatus()+"</td>" +
                    "<td>"+request.getManagerName()+"</td>" +
                    "</tr>";
        }

        html += "</table>" +
                "</body>" +
                "</html>";
        ctx.html(html);
    }

}
