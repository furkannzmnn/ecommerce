package com.base.ecommerce.service;

import com.base.ecommerce.core.utils.ContextAware;
import com.base.ecommerce.service.flows.CorporateCustomerFlow;
import com.base.ecommerce.service.flows.IndividualCustomerFlow;

public enum CustomerTypeFlow {
    INDIVIDUAL_CUSTOMER {
        @Override
        public SalesApplicationTemplate execute() {
            return ContextAware.getBean(IndividualCustomerFlow.class);
        }
    },

    CORPORATE_CUSTOMER {
        @Override
        public SalesApplicationTemplate execute() {
            return ContextAware.getBean(CorporateCustomerFlow.class);
        }
    };

    public abstract SalesApplicationTemplate execute();
}
