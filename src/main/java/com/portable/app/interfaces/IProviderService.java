package com.portable.app.interfaces;

import java.util.List;

import com.portable.app.entity.Provider;

public interface IProviderService {
    List<Provider> listProviders();
    Provider createProvider(Provider provider);
    void updateProvider(Provider provider);
    void deleteProvider(Integer providerId);
}
