package br.unirio.kipao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import br.unirio.kipao.model.Product;
import br.unirio.kipao.repository.ProductRepository;

@CacheConfig(cacheNames = "product")
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public ProductService() {
	}	
	public Product getProduct(String nameProduct) {
		return productRepository.findByName(nameProduct);
	}
	
	@Caching(evict = {@CacheEvict(value = "allproductcache", allEntries = true),
	        @CacheEvict(value = "productcache") })
	public Product saveProduct(@Valid Product productParam) {
		return productRepository.save(productParam);
	}
	
	@Cacheable(value = "allproductcache")
	public List<Product> getAllProducts() {
	    return (List<Product>) productRepository.findAll();
	}
 
}
