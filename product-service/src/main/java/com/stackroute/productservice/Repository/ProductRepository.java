package com.stackroute.productservice.Repository;

import com.stackroute.productservice.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product,String> {


      List<Product> findAll();

    Optional<Product> findByProductId(String productId);


    List<Product> findBySellerEmail(String sellerEmail);

    List<Product> getProductByCategory(String category);

    List<Product> getProductByState(String state);



}
