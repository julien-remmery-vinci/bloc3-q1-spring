package be.vinci.ipl.amazingproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AmazingProductServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(AmazingProductServiceApplication.class, args);
  }

}
