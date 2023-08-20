package com.github.mhewedy.condition.module;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

    private final MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(myService.getProductDetails("1"));
        System.out.println(myService.getClass());
    }

    public interface MyService {
        Map<String, Object> getProductDetails(String productId);
    }

    @Service
    @ConditionalOnModule("okhttp")
    public static class OKHttpClientImpl implements MyService {

        @Override
        public Map<String, Object> getProductDetails(String productId) {
            // TODO
            return null;
        }
    }

    @Service
    @ConditionalOnMissingModule("okhttp")
    public static class JavaHttpClientImpl implements MyService {

        @Override
        public Map<String, Object> getProductDetails(String productId) {
            // TODO
            return null;
        }
    }
}
