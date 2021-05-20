package com.ex.services;

import com.ex.dao.MongoDao;
import com.ex.pojos.Employee;
import com.mongodb.client.MongoCollection;

public class EmployeeService implements Service{
    private MongoDao dao;


    public EmployeeService(MongoDao dao){
        this.dao = dao;
    }


    public boolean employeeLogIn(String userName, String password){
        for (Employee employee : dao.getEmployees().find()) {
            if (userName.equals(employee.getUserName()) && password.equals(employee.getPassword())) {
                return true;
            }
        }
        return false;
    }


    public MongoDao getDao() {
        return dao;
    }


    public MongoCollection<Employee> getEmployees() {
        return dao.getEmployees();
    }
}
