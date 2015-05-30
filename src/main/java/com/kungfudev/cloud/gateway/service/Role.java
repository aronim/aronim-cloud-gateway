package com.kungfudev.cloud.gateway.service;

import java.io.Serializable;

/**
 * User: Kevin W. Sewell
 * Date: 2015-05-30
 * Time: 19h39
 */
public class Role implements Serializable {

    private String name;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
