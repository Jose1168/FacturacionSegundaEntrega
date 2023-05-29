package com.coderhouse.java.services;

import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.persistences.models.Invoice;
import com.coderhouse.java.persistences.repositories.ClientRepository;
import com.coderhouse.java.persistences.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getOne(Long clientId) {
        return findClientOrFail(clientId);
    }

    public Client findClientOrFail(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new ApiException("Client not found", HttpStatus.NOT_FOUND));
    }

    public Client createOne(Client client) {
        validateProperties(client);
        checkIfClientExists(client);
        Client instance = Client.createWith(client.getFirstName(), client.getLastName(), client.getDni());
        return clientRepository.save(instance);
    }

    private void checkIfClientExists(Client client) {
        boolean exists = clientRepository.findByDni(client.getDni()).isPresent();
        if (exists) throw new ApiException("Client already exists", HttpStatus.BAD_REQUEST);
    }


    public Client updateOne(Client client, Long clientId) {
        validateProperties(client);
        Client instance = findClientOrFail(clientId);
        instance.updateWith(client.getFirstName(), client.getLastName(), client.getDni());
        return clientRepository.save(instance);
    }

    private static void validateProperties(Client client) {
        if (!client.hasFirstName() || !client.hasLastName() || !client.hasDNI())
            throw new ApiException("Properties body missing", HttpStatus.BAD_REQUEST);
    }

    public void deleteOne(Long clientId) {
        Client client = findClientOrFail(clientId);
        clientRepository.delete(client);
    }

    public List<Invoice> getInvoices(Long clientId) {
        Client client = findClientOrFail(clientId);
        return invoiceRepository.findAllByClientId(client.getId());
    }
}
