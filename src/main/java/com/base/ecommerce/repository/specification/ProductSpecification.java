package com.base.ecommerce.repository.specification;

import com.base.ecommerce.dto.ProductSearchDto;
import com.base.ecommerce.model.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

public class ProductSpecification {

    private ProductSpecification() {
        throw new UnsupportedOperationException();
    }

    public static Specification<Product> searchAllBy(ProductSearchDto product) {

      return (root, query, builder) -> {

          final Path<Object> objectPath = root.get("product");

          final Predicates predicates =  new Predicates()
                  .add(SearchSpecificationBuilder.like(builder,root.get("productName"),product.getProductName()))
                  .add(SearchSpecificationBuilder.like(builder,root.get("productDesc"),product.getProductDesc()))
                  .add(SearchSpecificationBuilder.eq(builder, objectPath.get("productPrice.price"),product.getProductPrice()));
          return builder.and(predicates.get());
      };
    }
}
