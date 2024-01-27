package az.edu.ada.msquestions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsQuestionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsQuestionsApplication.class, args);
    }

}
