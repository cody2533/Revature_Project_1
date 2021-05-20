package pojos;

import com.ex.pojos.Manager;
import org.junit.jupiter.api.Test;

public class ManagerTests {

    @Test
    public void EmployeeToString() {
        Manager manager = new Manager("username","password");
        manager.toString();
    }
}
