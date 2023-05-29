package com.coderhouse.java.persistences.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double total;
    private Date createdAt;

    @ManyToOne
    private Client client;

    public Invoice() {
    }

    public Invoice(Client client) {
        this.createdAt = new Date();
        this.client = client;
    }

    public static Invoice createWith(Client client) {
        return new Invoice(client);
    }

    public Long getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean hasClient() {
        return client != null;
    }
}
