
package com.stackroute.service;
import com.stackroute.Config.ProductDTO;
//import com.stackroute.Exception.ProductAlreadyExistException;
import com.stackroute.Exception.ProductNotFoundException;
import com.stackroute.Model.Category;
import com.stackroute.Model.Location;
import com.stackroute.Model.IncomingProductData;
import com.stackroute.Repository.CategoryRepository;
import com.stackroute.Repository.LocationRepository;
import com.stackroute.Repository.ProductDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
@Slf4j
@Service
public class RecommendationServiceImpl implements RecommendationService {


    private ProductDataRepository productDataRepository;
    private CategoryRepository categoryRepository;
    private LocationRepository locationRepository;

    @Autowired
    public RecommendationServiceImpl(ProductDataRepository Repository, CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.productDataRepository = Repository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }


    @Override
    public void createNode(IncomingProductData incomingProductData){

        if (productDataRepository.findById(incomingProductData.getProductId()).isEmpty()) {
            productDataRepository.save(incomingProductData);
        }
        if (categoryRepository.findById(incomingProductData.getCategory()).isEmpty()) {
            Category category1 = new Category(incomingProductData.getCategory());
            categoryRepository.save(category1);
        }
        if (locationRepository.findById(incomingProductData.getState()).isEmpty()) {
            Location location1 = new Location(incomingProductData.getState(), incomingProductData.getCity());
            locationRepository.save(location1);
        }


        productDataRepository.createCategoryRelationshipWithProduct(incomingProductData.getProductId(), incomingProductData.getCategory());
        productDataRepository.createLocationRelationshipWithProduct(incomingProductData.getProductId(), incomingProductData.getState());
    }
    @Override
    public void deleteProductNode(String productId) {
        IncomingProductData data = productDataRepository.findById(productId).get();
        productDataRepository.delete(data);
    }



    @Override
    public HashSet<IncomingProductData> getProductRecommendationsByLocation(String state) throws ProductNotFoundException {
        HashSet<IncomingProductData> prod = productDataRepository.getProductRecommendationByLocation(state);
        if (prod.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return prod;
        }
    }

    @Override
    public HashSet<IncomingProductData> getProductByCategory(String category) throws ProductNotFoundException {
        //      return null;
        HashSet<IncomingProductData> catpo = productDataRepository.getProductByCategory(category);
        if (catpo.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return catpo;
        }
    }


    @Override
    public HashSet<IncomingProductData> getProductRecommendationByStateAndCategory(String state, String category) throws ProductNotFoundException {
        //  return null;
        HashSet<IncomingProductData> produ = productDataRepository.getProductRecommendationByStateAndCategory(state, category);
        if (produ.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return produ;
        }
    }


    @Override
    public ArrayList<IncomingProductData> getAllProduct() {
        ////  return null;
        return (ArrayList<IncomingProductData>) productDataRepository.findAll();
    }

    @Override
    public IncomingProductData getById(String i) throws ProductNotFoundException {
        Optional<IncomingProductData> pro = productDataRepository.findById(i);
        if (pro.isPresent()) {
            return pro.get();
        } else {
            throw new ProductNotFoundException();
        }
    }
    @Override
    public IncomingProductData addNew(IncomingProductData data) {

        return productDataRepository.save(data);
    }
    public IncomingProductData outmodel(ProductDTO product) {
        IncomingProductData prod = new IncomingProductData();
        prod.setProductId(product.getProductId());
        prod.setProductName(product.getProductName());
        prod.setProductPrice(product.getProductPrice());
        prod.setImage(product.getImage());
        prod.setCity(product.getCity());
        prod.setState(product.getState());
        prod.setCategory(product.getCategory());
        return prod;
    }
}
