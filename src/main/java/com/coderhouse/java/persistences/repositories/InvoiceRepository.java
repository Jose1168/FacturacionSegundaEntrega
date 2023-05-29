package com.coderhouse.java.persistences.repositories;

import com.coderhouse.java.persistences.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByClientId(Long clientId);
}
