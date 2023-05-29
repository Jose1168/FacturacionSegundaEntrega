package com.coderhouse.java.persistences;

import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.persistences.models.Invoice;
import com.coderhouse.java.persistences.models.InvoiceDetail;
import com.coderhouse.java.persistences.models.Product;
import com.coderhouse.java.persistences.repositories.ClientRepository;
import com.coderhouse.java.persistences.repositories.InvoiceDetailRepository;
import com.coderhouse.java.persistences.repositories.InvoiceRepository;
import com.coderhouse.java.persistences.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${initData:false}")
    private String initData;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (initData.equals("false")) return;

        Client client = Client.createWith("Garcia", "Hernan", "25647861");
        clientRepository.save(client);

        Client client2 = Client.createWith("Iglesias", "Jose", "20329871");
        clientRepository.save(client2);

        Product PlatoVidrio = Product.createWith("Plato de vidrio", "PV100", 200.00, 150);
        productRepository.save(PlatoVidrio);

        Product CubiertosAcero = Product.createWith("Cubiertos de Acero Inoxidable", "CA100", 230.00, 180);
        productRepository.save(CubiertosAcero);

        Product Destapador = Product.createWith("Destapador", "DE100", 180.00, 50);
        productRepository.save(Destapador);

        Invoice invoice = Invoice.createWith(client);
        invoiceRepository.save(invoice);

        InvoiceDetail invoiceDetail = InvoiceDetail.createWith(invoice, PlatoVidrio, 9);
        invoiceDetailRepository.save(invoiceDetail);

        InvoiceDetail invoiceDetail2 = InvoiceDetail.createWith(invoice, CubiertosAcero, 5);
        invoiceDetailRepository.save(invoiceDetail2);

        List<InvoiceDetail> invoicesDetail = invoiceDetailRepository.findAllByInvoiceId(invoice.getId());
        double total = invoicesDetail.stream().map(iDetail -> iDetail.getQuantity() * iDetail.getPrice()).reduce(0D, Double::sum);

        invoice.setTotal(total);
        invoiceRepository.save(invoice);

        initData = "false";
    }

}