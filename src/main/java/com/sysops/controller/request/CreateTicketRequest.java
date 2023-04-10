package com.sysops.controller.request;

import java.util.Date;

public class CreateTicketRequest {

    private String description;

    private Date createdDate;

    private String location;

    private long customerId;

    public CreateTicketRequest() {
    }

    public CreateTicketRequest(String description, Date createdDate, String location, long customerId) {
        this.description = description;
        this.createdDate = createdDate;
        this.location = location;
        this.customerId = customerId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

