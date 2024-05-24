package kirdin.lab.ownerMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("kirdin.lab.models")
@SpringBootApplication
public class OwnerMsApp {
    public static void main(String[] args) {
        SpringApplication.run(OwnerMsApp.class, args);
    }
}
