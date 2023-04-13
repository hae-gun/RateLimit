package com.example.demo;

import io.github.bucket4j.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
public class RateLimit {
    private final Bucket tokenBucket;
    private static final String NOT_WORKING = "IS NOT WORKING";

    public RateLimit() {
        this.tokenBucket = tpsBucket("200");
    }

    public String consume() throws InterruptedException {

        VerboseResult<ConsumptionProbe> verboseResult = tokenBucket.asVerbose().tryConsumeAndReturnRemaining(1);
        ConsumptionProbe probe = verboseResult.getValue();
        if (!probe.isConsumed()) {
            return NOT_WORKING;
        }
        log.info("is Running {}", probe.getRemainingTokens());
        return String.format("Remain tokens: %d", probe.getRemainingTokens());
    }

    private Bucket tpsBucket(String tps) {
        Bandwidth bandwidth = Bandwidth.classic(Integer.parseInt(tps),
                Refill.intervally(Integer.parseInt(tps), Duration.ofMillis(100L)));
        return bucket(bandwidth);
    }

    private Bucket bucket(Bandwidth limit) {
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
}
