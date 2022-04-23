package com.base.ecommerce.api;

import com.base.ecommerce.model.Country;
import com.base.ecommerce.repository.elastic.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CountryController {

    private final CountryRepository repository;

    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/countries")
    public Page<Country> getCountries(String name) {
        return repository.findByName(name, Pageable.unpaged());
    }

    @PostMapping
    public Country save( @RequestBody Country country) {
        return repository.save(country);
    }
}
