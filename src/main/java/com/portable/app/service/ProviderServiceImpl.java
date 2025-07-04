package com.portable.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portable.app.entity.Provider;
import com.portable.app.interfaces.IProviderService;
import com.portable.app.repository.ProviderRepository;

@Service
public class ProviderServiceImpl implements IProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Provider> listProviders() {
        return providerRepository.listProviders();
    }

    @Override
    @Transactional
    public Provider createProvider(Provider provider) {
        Integer newId = providerRepository.insertProvider(
            provider.getProviderCode(),
            provider.getProviderName(),
            provider.getProviderRuc()
        );
        
        provider.setProviderId(newId);
        return provider;
    }

    @Override
    @Transactional
    public void updateProvider(Provider provider) {
        providerRepository.updateProvider(
            provider.getProviderId(),
            provider.getProviderCode(),
            provider.getProviderName(),
            provider.getProviderRuc()
        ); 
    }

    @Override
    public void deleteProvider(Integer providerId) {
        providerRepository.deleteProvider(providerId);
    }
}
