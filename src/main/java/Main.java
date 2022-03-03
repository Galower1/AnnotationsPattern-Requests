import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException {
        List<Object> output = Auth.verifyMethods(Controllers.class);
        for (Object obj : output) {
            System.out.println((String) obj);
        }
    }
}
