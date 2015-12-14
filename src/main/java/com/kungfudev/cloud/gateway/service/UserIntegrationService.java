package com.kungfudev.cloud.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserIntegrationService {

    public static final String BASE_URL = "http://kdc-user/internal/users";

    @Autowired
    private RestTemplate restTemplate;

    public User findByEmailAddress(String emailAddress) {

        String url = BASE_URL + "/findByEmailAddress?emailAddress={emailAddress}";
        try {

            return restTemplate.getForObject(url, User.class, emailAddress);

        } catch (HttpStatusCodeException e) {

            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }

            throw e;
        }
    }
}
