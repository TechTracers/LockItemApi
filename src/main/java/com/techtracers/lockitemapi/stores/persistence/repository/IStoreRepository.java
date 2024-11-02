package com.techtracers.lockitemapi.stores.persistence.repository;

import com.techtracers.lockitemapi.stores.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoreRepository extends JpaRepository<Store, Long> {

}
