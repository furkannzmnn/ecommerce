
package com.base.ecommerce.configure.elasticSearch;


import org.elasticsearch.client.RestHighLevelClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {


    @NotNull
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration
                .builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }




}

