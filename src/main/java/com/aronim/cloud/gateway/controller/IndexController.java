package com.aronim.cloud.gateway.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Kevin W. Sewell
 * Date: 2015-05-30
 * Time: 15h36
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            return "admin.html";
        } else {
            return "public.html";
        }
    }
}
