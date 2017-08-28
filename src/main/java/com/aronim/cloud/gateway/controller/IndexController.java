package com.aronim.cloud.gateway.controller;

import com.aronim.cloud.gateway.integration.menu.MenuIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kevin W. Sewell
 * Date: 2015-05-30
 * Time: 15h36
 */
@Controller
public class IndexController
{
    private final MenuIntegrationService menuIntegrationService;

    @Autowired
    public IndexController(MenuIntegrationService menuIntegrationService)
    {
        this.menuIntegrationService = menuIntegrationService;
    }

    @RequestMapping("/")
    public String index(Authentication authentication)
    {
        if (authentication != null && authentication.isAuthenticated())
        {
            return "redirect:/admin";
        }
        else
        {
            return "public";
        }
    }

    @RequestMapping({"/admin", "/admin/**"})
    public String admin(HttpServletRequest request, Model model)
    {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        List<Map<String, ?>> menuItems = menuIntegrationService.findMenuItems("main");

        model.addAttribute("menuItems", menuItems);
        model.addAttribute("selectedItem", getSelectedItem(path, menuItems));

        return "admin";
    }

    private Map<String, ?> getSelectedItem(String path, List<Map<String, ?>> menuItems)
    {
        for (Map<String, ?> menuItem : menuItems)
        {
            if (menuItem.get("path").equals(path))
            {
                return menuItem;
            }
        }

        return menuItems.get(0);
    }
}
