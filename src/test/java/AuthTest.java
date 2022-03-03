import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

class AuthTest {
    @Test
    void checkAuth() throws IOException, InvocationTargetException, IllegalAccessException {
        List<Object> output = Auth.verifyMethods(Controllers.class);
        Assertions.assertEquals(output.size(), 1);
    }

    @Test
    void checkRequestResponse() throws IOException, InvocationTargetException, IllegalAccessException {
        List<Object> output = Auth.verifyMethods(Controllers.class);
        Assertions.assertEquals(output.get(0), "{\"username\":\"Adrian\",\"password\":\"admin\"}");
    }

    @Test
    void obtainRequestStatus() throws IOException {
        List<String[]> headers = new ArrayList<>();
        headers.add(new String[]{"authorization", "admin"});
        int status = Auth.checkStatus(headers);
        Assertions.assertEquals(status, 200);
    }
}