package com.stackroute.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
   // private String ProductId;
    private String category;

}
