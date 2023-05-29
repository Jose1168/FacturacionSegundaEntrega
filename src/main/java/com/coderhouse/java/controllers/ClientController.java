package com.coderhouse.java.controllers;

import com.coderhouse.java.dto.ResponseHandler;
import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.persistences.models.Invoice;
import com.coderhouse.java.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<Client> clients = clientService.getAll();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseHandler.generate("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        try {
            Client client = clientService.getOne(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Client body) {
        try {
            Client client = clientService.createOne(body);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Client body) {
        try {
            Client client = clientService.updateOne(body, id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            clientService.deleteOne(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (Exception e) {
            return ResponseHandler.generate("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/invoices")
    public ResponseEntity<Object> getInvoices(@PathVariable Long id) {
        try {
            List<Invoice> invoices = clientService.getInvoices(id);
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } catch (ApiException apiException) {
            return ResponseHandler.generate(apiException.getMessage(), apiException.getHttpStatus());
        } catch (RuntimeException e) {
            return ResponseHandler.generate("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
