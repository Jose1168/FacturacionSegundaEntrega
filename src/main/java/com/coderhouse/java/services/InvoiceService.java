package com.coderhouse.java.services;

import com.coderhouse.java.dto.InvoiceDTO;
import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.persistences.models.Invoice;
import com.coderhouse.java.persistences.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ClientService clientService;

    public Invoice getOne(Long invoiceId) {
        return findInvoiceOrFail(invoiceId);
    }

    private Invoice findInvoiceOrFail(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(() -> new ApiException("Invoice not found", HttpStatus.NOT_FOUND));
    }

    public Invoice createOne(InvoiceDTO invoiceDTO) {
        validateProperties(invoiceDTO);

        Client client = clientService.findClientOrFail(invoiceDTO.getClientId());

        Invoice invoice = Invoice.createWith(client);

        return invoiceRepository.save(invoice);
    }

    private void validateProperties(InvoiceDTO invoiceDTO) {
        if (invoiceDTO.hasClientId()) throw new ApiException("Properties body missing", HttpStatus.BAD_REQUEST);
    }
}
