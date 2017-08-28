package com.aronim.cloud.gateway.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kevin W. Sewell
 * Date: 2015-05-30
 * Time: 19h27
 */
@Service
public class UserIntegrationService
{
    private static final String BASE_URL = "http://aronim-cloud-user/internal/users";

    private final RestTemplate restTemplate;

    @Autowired
    public UserIntegrationService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public User findByEmailAddress(String emailAddress)
    {
        String url = BASE_URL + "/findByEmailAddress?emailAddress={emailAddress}";
        try
        {
            return restTemplate.getForObject(url, User.class, emailAddress);
        }
        catch (HttpStatusCodeException e)
        {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
            {
                return null;
            }

            throw e;
        }
    }
}
