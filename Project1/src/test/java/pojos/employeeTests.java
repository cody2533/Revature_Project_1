package pojos;

import com.ex.pojos.Employee;
import org.junit.jupiter.api.Test;

public class employeeTests {

    @Test
    public void EmployeeToString() {
        Employee employee = new Employee("username","password");
        employee.toString();
    }

}
