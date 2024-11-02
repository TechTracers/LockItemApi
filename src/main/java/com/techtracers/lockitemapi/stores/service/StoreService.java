package com.techtracers.lockitemapi.stores.service;

import com.crudjpa.service.impl.CrudService;
import com.techtracers.lockitemapi.stores.domain.model.Store;
import com.techtracers.lockitemapi.stores.domain.services.IStoreService;
import com.techtracers.lockitemapi.stores.persistence.repository.IStoreRepository;
import org.springframework.stereotype.Service;


@Service
public class StoreService extends CrudService<Store, Long> implements IStoreService {

    public StoreService(IStoreRepository repository) {
        super(repository);
    }
}
