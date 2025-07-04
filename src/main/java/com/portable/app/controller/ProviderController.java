package com.portable.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portable.app.entity.Provider;
import com.portable.app.service.ProviderServiceImpl;

@RestController
@RequestMapping("/v1/api/providers")
public class ProviderController {

    @Autowired
    private ProviderServiceImpl providerServiceImpl;

    @GetMapping("/list")
    public List<Provider> getProvidersList() {
        return providerServiceImpl.listProviders();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        try {
            Provider createdProvider = providerServiceImpl.createProvider(provider);
            return ResponseEntity.ok(createdProvider);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> updateProvider(@PathVariable Integer id, @RequestBody Provider provider) {
        try {
            provider.setProviderId(id);
            providerServiceImpl.updateProvider(provider);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteProvider(@PathVariable Integer id) {
        try {
            providerServiceImpl.deleteProvider(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
