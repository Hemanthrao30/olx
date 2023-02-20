
package com.stackroute.Repository;

import com.stackroute.Model.IncomingProductData;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Repository
@Transactional
public interface ProductDataRepository extends Neo4jRepository<IncomingProductData,String> {
    @Query("MATCH (a:IncomingProductData{productId:$productId}),(b:Category{category:$category}) MERGE (a)-[r:category]->(b)")
    public void createCategoryRelationshipWithProduct(String productId,String category);
    @Query("MATCH (a:IncomingProductData{productId:$productId}),(b:Location{state:$state}) MERGE (a)-[r:state]->(b)")
    public void createLocationRelationshipWithProduct(String productId,String state);

    @Query("MATCH (a:IncomingProductData),(b:Location) where (a.state)=$state and (b.state)=$state create (a)-[:belongs]->(b) return a")
    HashSet<IncomingProductData> getProductRecommendationByLocation(String state);
    @Query ("Match (a:IncomingProductData),(b:Location),(c:Category) where (a.category)=$category and (c.category)=$category and (a.state)=$state and (b.state)=$state create (a)-[:from]->(b) create (a)-[:belongs]->(c) return a")
    HashSet<IncomingProductData> getProductRecommendationByStateAndCategory(String state, String category);
    @Query("Match (a:IncomingProductData),(b:Category) where (a.category)=$category and (b.category)=$category create (a)-[:belongs]->(b) return a")
    HashSet<IncomingProductData> getProductByCategory(String category);
 //   @Query("Match (a:SimilarProduct),(b:Category) where (a.category)=$category and (b.category)=$category create (a)-[:belongs]->(b) return a")
   // HashSet<IncomingProductData> getProductByCategory(String category);
//}
}