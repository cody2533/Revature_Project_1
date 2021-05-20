package pojos;

import com.ex.pojos.Request;
import org.junit.jupiter.api.Test;

public class RequestTests {

    @Test
    public void EmployeeToString() {
        Request request = new Request(100,"other", "name");
        request.toString();
    }
}
