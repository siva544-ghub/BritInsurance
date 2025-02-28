package com.automation.task.pojo;

import java.util.Map;

/**
 * Pojo class for product object
 */
public class ProductPojo {
    private String id;
    private String name;
    private Map<String, Object> data;
    private String updatedAt;

    public ProductPojo() {
    }

    public ProductPojo(String name) {
        this.name = name;
    }

    public ProductPojo(String id, String name, Map<String, Object> data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", data=" + data +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}