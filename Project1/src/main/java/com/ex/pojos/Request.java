package com.ex.pojos;

public class Request {
    private double amount;
    private String reason;
    private String employee;
    private String manager;
    private String status;

    public Request(){};

    public Request(double amount, String reason, String employeeName) {
        this.amount = amount;
        this.reason = reason;
        this.employee = employeeName;
        this.manager = "TBD";
        this.status = "Pending";
    }

    public Request(Request request, String managerName, String status) {
        this.amount = request.getAmount();
        this.reason = request.getReason();
        this.employee = request.getEmployeeName();
        this.manager = managerName;
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEmployeeName() {
        return employee;
    }

    public void setEmployeeName(String employeeName) {
        this.employee = employeeName;
    }

    public String getManagerName() {
        return manager;
    }

    public void setManagerName(String managerName) {
        this.manager = managerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "amount=" + amount +
                ", reason='" + reason + '\'' +
                ", employeeName='" + employee + '\'' +
                ", managerName='" + manager + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
