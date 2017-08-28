package com.aronim.cloud.gateway.integration.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * User: Kevin W. Sewell
 * Date: 2016-08-21
 * Time: 14h08
 */
@Service
public class MenuIntegrationService
{
    private static final String BASE_URL = "http://aronim-cloud-menu/internal/menus/main/items";

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public List<Map<String, ?>> findMenuItems(String menuId)
    {
        String url = BASE_URL + "/{menuId}/items";
        try
        {
            @SuppressWarnings("unchecked")
            List<Map<String, ?>> result = (List<Map<String, ?>>) restTemplate.getForEntity(url, List.class, menuId);

            return result;
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
