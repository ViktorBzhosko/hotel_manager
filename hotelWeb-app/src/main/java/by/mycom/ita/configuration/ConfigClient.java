package by.mycom.ita.configuration;

import com.netflix.discovery.EurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClient {

    private final EurekaClient eurekaClient;

    public ConfigClient(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    public String serviceInfo(){
        return eurekaClient.getNextServerFromEureka("hotel-app", false).getHomePageUrl();
    }

}
