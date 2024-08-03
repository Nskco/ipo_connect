package com.yourcompany.ipoapp.repository;

import com.yourcompany.ipoapp.model.IPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface IPORepository extends MongoRepository<IPO, String> {
    Optional<IPO> findByCompanyName(String companyName);

    void deleteByCompanyName(String s);

    boolean existsByCompanyName(String companyName);
}
