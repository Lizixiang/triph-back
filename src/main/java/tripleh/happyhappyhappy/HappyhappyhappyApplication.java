package tripleh.happyhappyhappy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("tripleh.happyhappyhappy.com.tripleh.happy.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class HappyhappyhappyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyhappyhappyApplication.class, args);
    }

}
