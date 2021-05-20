package com.ex.services;

import com.ex.dao.MongoDao;
import com.ex.pojos.Request;

public class RequestService implements Service{

    private MongoDao dao;

    public RequestService(MongoDao dao) {
        this.dao = dao;
    }

    public MongoDao getDao() {
        return dao;
    }

    public void CreateRequest(Request request){
        dao.createRequest(request);
    }

    public void updateRequest(Request request, String manager, String status) {
        Request updatedRequest = new Request(request, manager, status);
        dao.updateRequest(updatedRequest);
    }
}
