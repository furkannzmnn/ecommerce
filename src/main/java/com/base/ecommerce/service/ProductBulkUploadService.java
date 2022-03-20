package com.base.ecommerce.service;

import com.base.ecommerce.core.utils.ExcelUtil;
import com.base.ecommerce.model.Category;
import com.base.ecommerce.model.Product;
import com.base.ecommerce.model.ProductPrice;
import com.base.ecommerce.model.ProductStatus;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ProductBulkUploadService implements BulkService{

    private final ProductService productService;
    private final MailService mailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBulkUploadService.class);


    public ProductBulkUploadService(ProductService productService, MailService mailService) {
        this.productService = productService;
        this.mailService = mailService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T upload(final MultipartFile multipartFiles) {
        Map<String,String > failedProductList = new HashMap<>();

        try {
            final Iterator<Row> iterator = getRowIterator(multipartFiles);
            final Map<Integer,String> keyIndex = ExcelUtil.getExcelHeaderMapper(iterator);

            while (iterator.hasNext()){
                final Map<String,Object> dataHolder = ExcelUtil.mapRowData(keyIndex,iterator.next());

                final String productName = (String) dataHolder.get("PRODUCT_NAME");
                final String productTitle = (String) dataHolder.get("PRODUCT_TITLE");
                final String productDesc = (String) dataHolder.get("PRODUCT_DESC");
                final String productPrice = (String) dataHolder.get("PRODUCT_PRICE");
                final String productCategory = (String) dataHolder.get("CATEGORY_ID");
                final String productStatus = (String) dataHolder.get("PRODUCT_STATUS");

                try {
                        Product db = generateProduct(productName, productTitle, productDesc, productPrice, productCategory, productStatus);
                        productService.saveAll(List.of(db));
                        LOGGER.info("product upload success:" + db.getId());
                        mailService.sendGenericMessageForBulk("BULK UPLOAD SUCCESS", "success");
                    return (T) HttpStatus.CREATED;

                }catch (NullPointerException e){
                    failedProductList.put("BULK UPDATE FAIL: FAIL NAME -> ", productName);
                    mailService.sendGenericMessageForBulk("BULK FAİL", "toplu ürün yüklenirken bir hata oluştu");
                    LOGGER.error(failedProductList.toString());
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return (T) HttpStatus.BAD_REQUEST;
    }

    private Iterator<Row> getRowIterator(MultipartFile multipartFiles) throws IOException {
        final XSSFWorkbook workbook = new XSSFWorkbook(multipartFiles.getInputStream());
        final XSSFSheet sheet = workbook.getSheetAt(0);
        return sheet.rowIterator();
    }

    @NotNull
    private Product generateProduct(String productName, String productTitle, String productDesc, String productPrice, String productCategory, String productStatus) {
        return new Product.Builder()
                .id(0)
                .productStatus(ProductStatus.valueOf(productStatus))
                .productDesc(productDesc)
                .productPrice(new ProductPrice.Builder().price(BigDecimal.valueOf(Long.parseLong(productPrice))).build())
                .category(new Category.Builder().id(Long.parseLong(productCategory)).build())
                .updateAt(LocalDateTime.now())
                .creatAt(LocalDateTime.now())
                .productTitle(productTitle)
                .productName(productName)
                .build();
    }
}
