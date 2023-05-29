package com.coderhouse.java.controllers;

import com.coderhouse.java.dto.InvoiceDTO;
import com.coderhouse.java.dto.ResponseHandler;
import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.persistences.models.Invoice;
import com.coderhouse.java.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        try {
            Invoice invoice = invoiceService.getOne(id);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody InvoiceDTO body) {
        try {
            Invoice invoice = invoiceService.createOne(body);
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
