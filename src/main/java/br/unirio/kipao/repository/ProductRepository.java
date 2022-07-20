package br.unirio.kipao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.unirio.kipao.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
  
    @Query("from Product where name = :name")
    public Product findByName(@Param("name")String name);

}
