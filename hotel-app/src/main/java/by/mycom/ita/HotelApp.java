package by.mycom.ita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelApp {

    public static void main(String[] args) {
        SpringApplication.run(HotelApp.class, args);
    }
}
