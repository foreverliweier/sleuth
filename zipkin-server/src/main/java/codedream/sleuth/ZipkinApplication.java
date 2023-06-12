package codedream.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

/**
 * @author alice
 * @time 2023-06-09 11:04
 **/
//@EnableZipkinServer
@EnableZipkinStreamServer
@SpringBootApplication
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }

}
