package com.portable.app.controller;

import com.portable.app.entity.Client;
import com.portable.app.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.listClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        Client client = clientService.findClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Client createdClient = clientService.createClient(client);
            return ResponseEntity.ok(createdClient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        try {
            client.setClientId(id);
            clientService.updateClient(client);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/total")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Integer> getTotalClients() {
        Integer total = clientService.getTotalClients();
        return ResponseEntity.ok(total);
    }
}