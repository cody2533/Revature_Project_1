package com.ex.dao;

import com.ex.pojos.Employee;
import com.ex.pojos.Manager;
import com.ex.pojos.Request;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;


public class MongoDao implements Dao {

    private MongoCollection<Employee> employees;
    private MongoCollection<Manager> managers;
    private MongoCollection<Request> requests;

    public MongoDao(){
        try{
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/myReimbursementApp");
            CodecProvider codecProvider = PojoCodecProvider.builder().register("com.ex.pojos").build();
            CodecRegistry registry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(codecProvider));
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .retryWrites(true)
                    .codecRegistry(registry)
                    .build();
            MongoClient client = MongoClients.create(settings);
            MongoDatabase database = client.getDatabase("myReimbursementApp");

            this.employees = database.getCollection("employees", Employee.class);
            this.managers = database.getCollection("managers", Manager.class);
            this.requests = database.getCollection("requests", Request.class);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public MongoCollection<Employee> getEmployees() {
        return employees;
    }

    public void createEmployee(Employee employee){
        this.employees.insertOne(employee);
    }

    public Employee getEmployee(String userName){
        return this.employees.find(eq("userName", userName)).first();
    }

    public void updateEmployee(String field, String match, String column, String value) {
        employees.updateOne(
                eq(field, match),
                combine(set(column, value)));
    }

        public MongoCollection<Manager> getManagers() {
        return managers;
    }

    public void createManager(Manager manager){
        this.managers.insertOne(manager);
    }

    public Manager getManager(String userName){
        return this.managers.find(eq("userName", userName)).first();
    }

    public MongoCollection<Request> getRequests() {
        return requests;
    }

    public FindIterable<Request> getRequestsByEmployee(String employeeName) {
        return requests.find(eq("employeeName", employeeName));
    }

    public void createRequest(Request request){ this.requests.insertOne(request); }

    public Request getRequest(double amount, String reason, String employeeName){
        return this.requests.find(and(eq("amount", amount), eq("reason", reason), eq("employeeName", employeeName))).first();
    }

    public void deleteRequest(Request request){ this.requests.findOneAndDelete(and(eq("amount", request.getAmount()),
            eq("reason", request.getReason()),
            eq("employeeName", request.getEmployeeName())));}

    public void updateRequest(Request request){
        this.deleteRequest(request);
        this.createRequest(request);
    }

    @Override
    public List getAllData() {
        return Arrays.asList("From Mongo Dao");

    }
}