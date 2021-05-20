package com.ex;

import com.ex.controllers.EmployeeController;
import com.ex.controllers.ManagerController;
import com.ex.controllers.RequestController;
import com.ex.controllers.ScreenController;
import com.ex.dao.MongoDao;
import com.ex.screens.HomeScreen;
import com.ex.screens.LogInScreen;
import com.ex.screens.RequestsScreen;
import com.ex.screens.UserScreen;
import com.ex.services.EmployeeService;
import com.ex.services.ManagerService;
import com.ex.services.RequestService;
import io.javalin.Javalin;

import java.util.HashMap;

public class MyApplication extends AbstractApplication {

    public MyApplication(){

    }

    @Override
    public void run() {
        init();

    }

    private void init() {

        Javalin javalinApp = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        }).start(7777);

        context = new HashMap<String, Object>();
        MongoDao dao = new MongoDao();

        EmployeeService employeeService = new EmployeeService(dao);
        ManagerService managerService = new ManagerService(dao);
        RequestService requestService = new RequestService(dao);

        EmployeeController employeeController = new EmployeeController(employeeService);
        ManagerController managerController = new ManagerController(managerService);
        RequestController requestController = new RequestController(requestService, employeeController, managerController);
        ScreenController screenController = new ScreenController(employeeController, managerController);

        context.put("Dao", dao);
        context.put("EmployeeService", employeeService);
        context.put("ManagerService", managerService);
        context.put("RequestService", requestService);

        context.put("EmployeeController", employeeController);
        context.put("ManagerController", managerController);
        context.put("RequestController", requestController);
        context.put("ScreenController", screenController);

        context.put("javalin", javalinApp);

        HomeScreen homeScreen = new HomeScreen(javalinApp, context);
        LogInScreen logInScreen = new LogInScreen(javalinApp, context);
        RequestsScreen requestScreen = new RequestsScreen(javalinApp, context);
        UserScreen userScreen = new UserScreen(javalinApp, context);

        homeScreen.init();
        logInScreen.init();
        requestScreen.init();
        userScreen.init();

    }

}
