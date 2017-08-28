package com.aronim.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * User: Kevin W. Sewellq
 * Date: 2015-05-29
 * Time: 19h37
 */
@ComponentScan
@EnableZuulProxy
@EnableWebSecurity
@EnableEurekaClient
@SpringBootApplication
@EnableRedisHttpSession
@EnableAutoConfiguration
public class GatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GatewayApplication.class);
    }
}
