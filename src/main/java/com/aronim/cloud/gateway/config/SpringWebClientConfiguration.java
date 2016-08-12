package com.aronim.cloud.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kevin W. Sewell
 * Date: 2016-08-06
 * Time: 15h53
 */
@Configuration
public class SpringWebClientConfiguration
{
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
