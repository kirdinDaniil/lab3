package kirdin.lab.catMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("kirdin.lab.models")
@SpringBootApplication
public class CatMsApp {
    public static void main(String[] args) {
        SpringApplication.run(CatMsApp.class, args);
    }
}
