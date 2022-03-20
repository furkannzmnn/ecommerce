package com.base.ecommerce.service;

import com.base.ecommerce.dto.SalesApplicationDto;
import com.base.ecommerce.model.SalesApplication;
import com.base.ecommerce.model.SalesApplicationType;
import com.base.ecommerce.repository.SalesApplicationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SalesApplicationService {

    private final SalesApplicationRepository applicationRepository;

    public SalesApplicationService(SalesApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public SalesApplicationDto apply (SalesApplicationDto dto) {

        SalesApplicationTemplate salesApplicationTemplate = CustomerTypeFlow.valueOf(dto.getCustomerType().name()).execute();
        salesApplicationTemplate.apply(dto);

        final SalesApplication salesApplication  = new SalesApplication.Builder()
                .id(dto.getSalesApplicationId())
                .customerId(dto.getCustomerId())
                .companyName(dto.getCompanyName())
                .createdDate(LocalDateTime.now())
                .salesApplicationType(SalesApplicationType.SALES)
                .build();

        applicationRepository.save(salesApplication);
        return dto;
    }


}
