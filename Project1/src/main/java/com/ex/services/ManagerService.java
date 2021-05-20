package com.ex.services;

import com.ex.dao.MongoDao;
import com.ex.pojos.Manager;

public class ManagerService implements Service{
    private MongoDao dao;

    public ManagerService(MongoDao dao){
        this.dao = dao;
    }


    public boolean logIn(String userName, String password) {
        for (Manager manager : dao.getManagers().find()) {
            if (userName.equals(manager.getUserName()) && password.equals(manager.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public MongoDao getDao() {
        return dao;
    }

}
