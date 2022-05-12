package com.example.config;

import com.backbase.buildingblocks.communication.http.HttpCommunicationConfiguration;
import com.backbase.greeting.api.service.ApiClient;
import com.backbase.greeting.api.service.v1.GreetingApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GreetingClientConfiguration {
    @Value("${backbase.example.greeting-base-url}")
    private String greetingBasePath;

    @Bean
    public ApiClient greetingApiClient() {
        ApiClient apiClient = new ApiClient(new RestTemplate());
        apiClient.setBasePath(this.greetingBasePath);
        apiClient.addDefaultHeader(HttpCommunicationConfiguration.INTERCEPTORS_ENABLED_HEADER, Boolean.TRUE.toString());
        apiClient.setDebugging(true);
        return apiClient;
    }

    @Bean
    public GreetingApi createConfirmationApi(@Qualifier("greetingApiClient") ApiClient greetingApiClient) {
        return new GreetingApi(greetingApiClient);
    }
}
