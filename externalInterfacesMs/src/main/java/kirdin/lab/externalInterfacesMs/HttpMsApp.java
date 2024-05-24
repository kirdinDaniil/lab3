package kirdin.lab.externalInterfacesMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("kirdin.lab.models")
@SpringBootApplication
public class HttpMsApp {
    public static void main(String[] args) {
        SpringApplication.run(HttpMsApp.class, args);
    }
}
