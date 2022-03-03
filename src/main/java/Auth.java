import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Auth {
    public static int checkStatus(List<String[]> headers) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://localhost:7000/verify");

            for (String[] header : headers) {
                request.addHeader(header[0], header[1]);
            }

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return response.getStatusLine().getStatusCode();
            }

        }
    }

    public static List<Object> verifyMethods(final Class<?> type)
            throws IOException,
            InvocationTargetException,
            IllegalAccessException {

        Class<?> klass = type;
        List<String[]> headers = new ArrayList<>();
        List<Object> functionReturns = new ArrayList<>();

        while (klass != Object.class) {
            for (final Method method : klass.getDeclaredMethods()) {
                Verify[] annotInstances = method.getAnnotationsByType(Verify.class);
                for (final Verify annotInstance : annotInstances) {
                    headers.add(annotInstance.header());
                }
                if (checkStatus(headers) == 200) {
                    Object returned = method.invoke(null, headers);
                    functionReturns.add(returned);
                }
                headers = new ArrayList<>();
            }
            klass = klass.getSuperclass();
        }
        return functionReturns;
    }
}
