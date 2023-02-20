package com.stackroute.productservice.controller;

//import com.stackroute.productservice.service.ProductSearchService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.productservice.Repository.ProductRepository;
import com.stackroute.productservice.config.RabbitMq;
import com.stackroute.productservice.exception.*;
import com.stackroute.productservice.model.IncomingProductData;
import com.stackroute.productservice.model.Product;
import com.stackroute.productservice.service.ProductServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin("http://localhost:4200")
@Slf4j
public class ProductController {
    @Autowired
    private RabbitTemplate template;

    private ResponseEntity responseEntity;

    private ProductRepository productRepository;


    private ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductServiceImpl productService ){
        log.debug("Inside Prod Controller - constructor");
        this.productRepository = productRepository;
        this.productService = productService;

    }


    @PostMapping("/addproduct")
    public ResponseEntity<Product> createproduct(@RequestParam(value="Product") String product, @RequestParam(value="image") MultipartFile image) throws IOException, ProductAlreadyExistException {

        try {
            log.debug("Inside the ProductController -- createproduct methods");
            ObjectMapper objectMapper = new ObjectMapper();
            Product pro = objectMapper.readValue(product,Product.class);
            String pId = productService.autogenerateProductId();
            pro.setProductId(pId);
            pro.setImage(image.getBytes());
//            template.convertAndSend(RabbitMq.EXCHANGE, RabbitMq.ROUTING_KEY, pro);
            return new ResponseEntity<Product>(productService.addprod(pro),HttpStatus.OK);
        } catch (JsonMappingException e) {
            log.error("JsonMappingException",e);
            return new ResponseEntity("Json Mapping Exception",HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException", e);
            return new ResponseEntity("Json Processing Exception", HttpStatus.CONFLICT);
        }
//
//        catch (ProvideProperProductDetails e) {
//            log.error("Give product details",e);
//            return new ResponseEntity("Provice proper details",HttpStatus.CONFLICT);
//        }

    }


    @GetMapping("/product")
    public List<Product> getProducts() {
        List<Product> list=new ArrayList<>();
        productRepository.findAll().forEach(a->list.add(a));
        Collections.reverse( list);
        return list;
    }


    @GetMapping("/allProduct")
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            log.debug("Inside the ProductController -- getAllProducts methods");
            return new ResponseEntity<List<Product>>((List<Product>) productService.findallrepository(), HttpStatus.CREATED);
        } catch (NoProductExistsInTheRepository e) {
            log.error("Product not found",e);
            return new ResponseEntity("List not found", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createproduct(@RequestBody Product product) throws IOException {
        try {
            log.debug("Inside the ProductController -- createproduct methods");
            return new ResponseEntity<Product>(productService.addprod(product), HttpStatus.CREATED);
        } catch (ProductAlreadyExistException e) {
            log.error("Product already exists.",e);
            return new ResponseEntity("Product already Exist", HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/product/productId/{productId}")
    public ResponseEntity<Product> setNotAvailableProductById(@PathVariable String productId){
        try {
            log.debug("Inside ProductController --- not available");
            return new ResponseEntity<Product>(productService.setstatus(productId),HttpStatus.OK);

        }catch (NoProductExistsInTheRepository e) {
        log.error(" No prod of given id", e);
        return new ResponseEntity("product not in repo",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/productId/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId){
        try{
            log.debug("Inside ProdContoller -- getProductById");
            return  new ResponseEntity<Product>(productService.getByProductId(productId),HttpStatus.OK);

        }catch(ProductNotFoundException e){
            log.error("Product not found" , e);
            return new ResponseEntity("Prod not found" , HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/product/sellerEmail/{sellerEmail}")
    public ResponseEntity<?> findBySellerEmail(@PathVariable String sellerEmail)throws ProductNotFoundException{
        try{
            responseEntity=new ResponseEntity(productService.findBySellerEmail(sellerEmail),HttpStatus.OK);

        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/productByCategory/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String category) throws ProductNotFoundException{
        try{
            responseEntity = new ResponseEntity(productService.getProductByCategory(category),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;

    }

    @GetMapping("/productByState/{state}")
    public ResponseEntity<?> getProductByState(@PathVariable String state) throws ProductNotFoundException{
        try{
            responseEntity = new ResponseEntity(productService.getProductByState(state),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProductByProductId(@PathVariable String productId) throws ProductNotFoundException {
        try{
            responseEntity=new ResponseEntity<>(productService.deleteProductByProductId(productId),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        log.debug("Product Is Deleted");
        return responseEntity;
    }


}
