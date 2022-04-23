package com.base.ecommerce.repository.elastic;

import com.base.ecommerce.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends ElasticsearchRepository<Country, String> {

    Page<Country> findByName(String name, Pageable pageable);
}
