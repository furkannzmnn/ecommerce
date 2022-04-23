package com.base.ecommerce.configure;

import org.springframework.stereotype.Component;

@Component
public class EmailConfiguration {

    private static final String apikey = "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";

    public String getApikey() {
        return apikey;
    }

}
