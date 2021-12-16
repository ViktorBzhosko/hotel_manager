package by.mycom.ita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HotelApp {

    public static void main(String[] args) {
        SpringApplication.run(HotelApp.class,args);
    }
}
