package com.coderhouse.java.persistences.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "dni"))
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(length = 75)
    private String firstName;

    @Nonnull
    @Column(length = 75)
    private String lastName;

    @Nonnull
    @Column(unique = true, length = 11)
    private String dni;

//    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
//    private List<Invoice> invoice;

    public Client() {
    }

    public Client(String firstName, String lastName, String dni) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }

    public static Client createWith(String firstName, String lastName, String dni) {
        return new Client(firstName, lastName, dni);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean hasFirstName() {
        return firstName != null;
    }

    public boolean hasLastName() {
        return lastName != null;
    }

    public boolean hasDNI() {
        return dni != null;
    }

    public void updateWith(String firstName, String lastName, String dni) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }

//    public List<Invoice> getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(List<Invoice> invoice) {
//        this.invoice = invoice;
//    }
}
