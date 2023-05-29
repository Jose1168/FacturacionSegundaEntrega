package com.coderhouse.java.persistences.models;

import jakarta.persistence.*;

@Entity
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double price;

    @ManyToOne
    private Invoice invoice;

    @OneToOne
    private Product product;

    public InvoiceDetail(Invoice invoice, Product product, int quantity) {
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    public InvoiceDetail() {
    }

    public static InvoiceDetail createWith(Invoice invoice, Product product, int quantity) {
        return new InvoiceDetail(invoice, product, quantity);
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
