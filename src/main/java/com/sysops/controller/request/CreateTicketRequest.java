package com.sysops.controller.request;

public class CreateTicketRequest {

    private String description;

    private String location;

    private String customerId;

    public CreateTicketRequest() {
    }

    public CreateTicketRequest(String description, String location, String customerId) {
        this.description = description;
        this.location = location;
        this.customerId = customerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

