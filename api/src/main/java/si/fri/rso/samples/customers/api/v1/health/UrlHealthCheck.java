package si.fri.rso.samples.customers.api.v1.health;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.Optional;

@Health
@ApplicationScoped
public class UrlHealthCheck implements HealthCheck {

    @Inject
    @DiscoverService(value = "uberapp-rides")
    private Optional<String> url;

    @Override
    public HealthCheckResponse call() {

        try {
            String fullUrl = url.get() + "/v1/rides";
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(fullUrl);
            HttpResponse response = httpClient.execute(request);

            int status = response.getStatusLine().getStatusCode();
            System.out.println("response code: " + status);
            if (status >= 200 && status < 300) {
                return HealthCheckResponse.named(UrlHealthCheck.class.getSimpleName()).up().build();
            }
        } catch (Exception e) {
            String msg = e.getClass().getName() + " occured: " + e.getMessage();
            // todo logging
            System.out.println(msg);
//                throw new InternalServerErrorException(msg);
        }
        return HealthCheckResponse.named(UrlHealthCheck.class.getSimpleName()).down().build();



    }
}
