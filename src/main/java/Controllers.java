import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

public class Controllers {

    @Verify(header = {"authorization", "admin"})
    @Verify(header = {"content-type", "application/json"})
    public static String getData(List<String[]> headers) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://localhost:7000/data");

            for (String[] header : headers) {
                request.addHeader(header[0], header[1]);
            }

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return new BasicResponseHandler().handleResponse(response);
            }
        }
    }

    @Verify(header = {"authorization", "application/json"})
    public static String getDataUnauthorized(List<String[]> headers) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("http://localhost:7000/data");

            for (String[] header : headers) {
                request.addHeader(header[0], header[1]);
            }

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return new BasicResponseHandler().handleResponse(response);
            }
        }
    }
}
