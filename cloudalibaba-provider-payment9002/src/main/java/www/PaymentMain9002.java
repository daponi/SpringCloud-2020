package www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 演示alibaba Nacos discovery的生产者Demo
 */
@SpringBootApplication
@EnableDiscoveryClient//该注解用于向使用consul或者zookeeper、Nacos作为注册中心时注册服务
public class PaymentMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9002.class, args);
    }
}
