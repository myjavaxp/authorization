package com.yibo.web.domain;

import java.io.Serializable;

public class Role implements Serializable {
    private static final long serialVersionUID = -4629374311955301054L;
    private Long id;

    private String name;

    private Boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}