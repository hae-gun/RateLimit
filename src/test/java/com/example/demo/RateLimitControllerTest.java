package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class RateLimitControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("test")
    void request_rateLimit(){
        List<Runnable> threads = new ArrayList<>();
        AtomicInteger cnt= new AtomicInteger();
        for(int i=0; i<1000; i++){
            threads.add(
                    () -> {
                        String result = webTestClient.get()
                                .uri("/test")
                                .accept(MediaType.APPLICATION_JSON)
                                .exchange()
                                .expectBody(String.class)
                                .returnResult().getResponseBody();
                        if(!"IS NOT WORKING".equals(result)){
                            cnt.getAndIncrement();
                        }
                    }
            );
        }
        threads.stream().parallel().forEach(Runnable::run);
        System.out.println(String.format("result is %d", cnt.get()));
    }

}