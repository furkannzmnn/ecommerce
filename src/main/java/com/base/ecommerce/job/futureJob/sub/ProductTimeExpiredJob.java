package com.base.ecommerce.job.futureJob.sub;

import com.base.ecommerce.job.futureJob.BaseJob;
import com.base.ecommerce.model.Product;
import com.base.ecommerce.model.ProductStatus;
import com.base.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class ProductTimeExpiredJob extends BaseJob {

    private static final Logger logger = Logger.getLogger(ProductTimeExpiredJob.class.getName());
    private final ProductRepository productRepository;

    public ProductTimeExpiredJob(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void execute(Map<String, Object> params) {
            final List<Product> list = productRepository.findAll();
            list.stream().filter(Objects::nonNull).forEach(product -> {
                if (product.getProductStatus().equals(ProductStatus.ACTIVE)) {
                    if (product.getProductTimeExpired().isBefore(LocalDateTime.now())) {
                        product.setProductStatus(ProductStatus.EXPIRED);
                        productRepository.save(product);
                        logger.info("Product " + product.getProductName() + " is expired");
                    }
                }
            });
    }
}
