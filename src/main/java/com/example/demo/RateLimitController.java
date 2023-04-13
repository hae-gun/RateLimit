package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RateLimitController {
    //    private final RedisTemplate redisTemplate;
    private final RateLimit rateLimit;

    @GetMapping("/test")
    public ResponseEntity<String> test() throws InterruptedException {
        return ResponseEntity.ok(rateLimit.consume());
    }


}
