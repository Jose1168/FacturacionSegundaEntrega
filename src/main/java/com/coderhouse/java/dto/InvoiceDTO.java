package com.coderhouse.java.dto;

import java.util.Date;

public class InvoiceDTO {

    private Long invoiceId;
    private Long clientId;
    private Date createdAt;
    private double total;

    public InvoiceDTO(Long invoiceId, Date createdAt, double total) {
        this.invoiceId = invoiceId;
        this.createdAt = createdAt;
        this.total = total;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public double getTotal() {
        return total;
    }

    public boolean hasClientId() {
        return clientId != null;
    }

    public Long getClientId() {
        return clientId;
    }
}
