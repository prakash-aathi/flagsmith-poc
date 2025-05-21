package com.test.flagsmith.config;

import com.flagsmith.FlagsmithClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlagsmithConfig {

    @Value("${flagsmith.api.key}")
    private String apiKey;

    @Value("${flagsmith.api.url}")
    private String apiUrl;

    @Bean
    public FlagsmithClient flagsmithClient() {
        return FlagsmithClient.newBuilder()
                .setApiKey(apiKey)
                .withConfiguration(com.flagsmith.config.FlagsmithConfig.newBuilder()
                        .baseUri(apiUrl)
                        .build())
                .enableLogging()
                .build();
    }
    

}
