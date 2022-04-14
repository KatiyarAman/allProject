package com.quokka.catalog.service.impl;

import com.google.gson.Gson;
import com.quokka.catalog.model.Product;
import com.quokka.catalog.repository.ProductRepository;
import com.quokka.catalog.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private CassandraTemplate cassandraTemplate;

    @Override
    public Product createProduct(Product product, String currentUser) {
        logger.info("Persisting the entity by userName : {}", new Gson().toJson(currentUser));
        try {
            product.setLastUpdatedBy(currentUser);
            return productRepository.save(product);
        } catch (Exception e) {
            logger.error("error occured while persisting the entity {}", new Gson().toJson(product) + " e message " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product getById(String idProduct) {
        try {
            return productRepository.findById(idProduct).get();
        } catch (Exception e) {
            logger.error("error occured while fetching the entity by productId {}", new Gson().toJson(idProduct) + "e message " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAllByColumnName(String columnName, Object value) {
//        try {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("Select * from product_by_id where");
//            stringBuilder.append(" ").append(columnName).append(" = ").append(value).append(" AND ").append(" deleted_flag = ").append(false)
//                    .append(" ALLOW FILTERING ");
//            String sql = stringBuilder.toString();
//            logger.info("sql query ",sql);
//            return cassandraTemplate.select(sql, Product.class);
//        } catch (Exception e) {
//            logger.error("error occured while fetching the entity by column : {} and value :{}", new Gson().toJson(columnName) + new Gson().toJson(value) + "e message " + e.getMessage());
//            e.printStackTrace();
//        }
        return null;
    }
}
