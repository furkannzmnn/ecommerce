package com.base.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "country", createIndex = false)
public class Country {

    @Id
    private String countryId;
    private String name;
    private String countryName;
    private String countryCode;

}
