package br.unirio.kipao.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unirio.kipao.model.Product;
import br.unirio.kipao.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public Product getProduct(String nameProduct) {
		return productRepository.findByName(nameProduct);
	}

	public Product saveProduct(@Valid Product productParam) {
		return productRepository.save(productParam);
	}
 
}
