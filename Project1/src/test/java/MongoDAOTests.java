import com.ex.dao.MongoDao;
import com.ex.pojos.Employee;
import com.ex.pojos.Manager;
import com.ex.pojos.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MongoDAOTests {

    MongoDao mongoDao = new MongoDao();



    @Test
    public void getEmployees(){
        mongoDao.getEmployees();
    }

    @Test
    public void updateEmployeePassword(){
        Employee employee = new Employee("John", "password");
        if(mongoDao.getEmployee(employee.getUserName()) == null){
            mongoDao.createEmployee(employee);
        }
        String updatedPassword = "pass";
        mongoDao.updateEmployee("userName", "John", "password", updatedPassword);
        Assertions.assertEquals(updatedPassword, mongoDao.getEmployee(employee.getUserName()).getPassword());
    }

    @Test
    public void createAndGetManager(){
        Manager manager = new Manager("Cody", "pass");
        if(mongoDao.getManager(manager.getUserName()) == null){
            mongoDao.createManager(manager);
        }
        Assertions.assertEquals(manager.getUserName(), mongoDao.getManager(manager.getUserName()).getUserName());
    }

    @Test
    public void createAndUpdateRequest(){
        Request request = new Request(100, "test", "John");
        if(mongoDao.getRequest(request.getAmount(), request.getReason(), request.getEmployeeName()) == null){
            mongoDao.createRequest(request);
        }
        Request updatedRequest = new Request(request, "Cody", "Approved");
        mongoDao.updateRequest(updatedRequest);
        Assertions.assertEquals(updatedRequest.getManagerName(), mongoDao.getRequest(updatedRequest.getAmount(), updatedRequest.getReason(), updatedRequest.getEmployeeName()).getManagerName());
    }


    @Test
    public void getManagers(){
        mongoDao.getManagers();
    }

    @Test
    public void getRequests(){
        mongoDao.getRequests();
    }

}
