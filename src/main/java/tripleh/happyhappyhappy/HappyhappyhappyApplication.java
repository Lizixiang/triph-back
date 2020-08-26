package tripleh.happyhappyhappy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("tripleh.happyhappyhappy.com.tripleh.happy.mapper")
public class HappyhappyhappyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyhappyhappyApplication.class, args);
    }

}
