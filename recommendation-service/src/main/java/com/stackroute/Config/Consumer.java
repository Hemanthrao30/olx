package com.stackroute.Config;

import com.stackroute.Model.IncomingProductData;
import com.stackroute.service.RecommendationServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Consumer {
    //Log log= LogFactory.getLog(Consumer.class);
    @Autowired
    public RecommendationServiceImpl recommendationServiceImp;
    @RabbitListener(queues = RabbitMq.QUEUE)
    public void  consumeMessageFromQueue(ProductDTO productDTO){
//        log.info("consuming data");
//        log.info(productDTO);
        IncomingProductData product = new IncomingProductData();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setImage(productDTO.getImage());
        product.setCity(productDTO.getCity());
        // product.setState(productDTO.getState());
        product.setState(productDTO.getState());
        product.setCategory(productDTO.getCategory());
        recommendationServiceImp.createNode(product);
    }
}