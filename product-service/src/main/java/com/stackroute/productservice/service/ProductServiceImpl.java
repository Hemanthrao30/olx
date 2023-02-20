package com.stackroute.productservice.service;


import com.stackroute.productservice.Repository.ProductRepository;
import com.stackroute.productservice.exception.*;
import com.stackroute.productservice.model.IncomingProductData;
import com.stackroute.productservice.model.Product;
//import com.stackroute.productservice.model.Status;
import com.stackroute.productservice.model.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public IncomingProductData outmodel(Product pro) {
        IncomingProductData prod = new IncomingProductData();
        prod.setProductId(pro.getProductId());
        prod.setProductName(pro.getProductName());
        prod.setProductPrice(pro.getProductPrice());
        prod.setProductCondition(pro.getProductCondition());
        prod.setAdditionalDetails(pro.getAdditionalDetails());
        prod.setProductDescription(pro.getProductDescription());
        prod.setImage(pro.getImage());

//        prod.setProductdatelocal(pro.getProductdatelocal());

        prod.setCategory(pro.getCategory());
        prod.setCity(pro.getCity());
        prod.setState(pro.getState());
        prod.setSellerName(pro.getSellerName());
        prod.setPincode(pro.getPincode());
        prod.setSellerEmail(pro.getSellerEmail());
        prod.setSellerState(pro.getSellerState());
        prod.setPurchaseDate(pro.getPurchaseDate());

        prod.setBrand(pro.getBrand());
        prod.setColor(pro.getColor());
        prod.setYearsOfUse(pro.getYearsOfUse());
        prod.setWarranty(pro.getWarranty());
        prod.setProductDefects(pro.getProductDefects());


        return prod;

    }

    public String autogenerateProductId() {
        String prefix = "swapon";
        Random r = new Random();
        int id = r.nextInt();
        String suffix = Integer.toString(id);
        String pid = prefix.concat(suffix);
        boolean idStatus = checkProductId(pid);
        if (idStatus = false) {
            pid = autogenerateProductId();
            idStatus = checkProductId(pid);
        }

        return pid;
    }


    private boolean checkProductId(String pid) {
        boolean flag = false;
        if (productRepository.findById(pid).isEmpty()) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }


    @Override
    public Iterable<Product> findallrepository() throws NoProductExistsInTheRepository {
        if (productRepository.findAll().isEmpty()) {
            log.error("No product exists in the repository");
            throw new NoProductExistsInTheRepository();
        } else {
            log.debug("Inside the ProductServiceImpl -- findallrepository methods");
            return productRepository.findAll();
        }
    }

    @Override
    public Product addprod(Product product) throws ProductAlreadyExistException {
        Optional<Product> prod = productRepository.findById(product.getProductId());
        if (prod.isPresent()) {
            log.error("No product exists in the repository");
            throw new ProductAlreadyExistException();
        } else {
            log.debug("Inside the ProductServiceImpl -- addProd methods");
            log.debug(String.valueOf(product));
            return productRepository.save(product);
        }
    }


    public Product setstatus(String productId) throws NoProductExistsInTheRepository {
        Optional<Product> prod = productRepository.findByProductId(productId);
        if (prod.isEmpty()) {
            log.error("Product is not in repo");
            throw new NoProductExistsInTheRepository();

        } else {
            log.debug("Inside the ProdserImpl -- setstatus");
            Status s = Status.NOTAVAILABLE;
            Product ps = prod.get();
            ps.setPstatus(s);
            Product savedprod = productRepository.save(ps);
            return savedprod;
        }

    }


    public Product getByProductId(String productId) throws ProductNotFoundException {
        Optional<Product> prod = productRepository.findByProductId(productId);
        if (prod.isPresent()) {
            log.debug("get by ProductId method");
            return prod.get();
        } else {
            log.error("Product not exists");
            throw new ProductNotFoundException();
        }
    }


        @Override
    public List<Product> findBySellerEmail(String sellerEmail) throws ProductNotFoundException {
        if(productRepository.findBySellerEmail(sellerEmail).isEmpty()){

            log.error("Product not exists in the repository");
            throw new ProductNotFoundException();

        }
            return productRepository.findBySellerEmail(sellerEmail);

    }

    public List<Product> getProductByCategory(String category) throws ProductNotFoundException{
        if(productRepository.getProductByCategory(category).isEmpty()){
            throw new ProductNotFoundException();

        }

        return productRepository.getProductByCategory(category);
    }

    public List<Product> getProductByState(String state) throws ProductNotFoundException {
        if(productRepository.getProductByState(state).isEmpty()){
            throw new ProductNotFoundException();

        }
        return productRepository.getProductByState(state);
    }


    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        productRepository.findAll().forEach(a -> list.add(a));
        Collections.reverse(list);
        return list;
    }

    @Override
    public boolean deleteProductByProductId(String productId) throws ProductNotFoundException {
        boolean flag = false;
        if (productRepository.findById(productId).isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            productRepository.deleteById(productId);
            flag = true;
        }
        return flag;

    }


}
