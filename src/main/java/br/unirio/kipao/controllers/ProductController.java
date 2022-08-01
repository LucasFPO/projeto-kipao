package br.unirio.kipao.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.unirio.kipao.model.Product;
import br.unirio.kipao.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="")
	public String hello() {
	    return "Bem vindo ao kipao";
	}
	
	@GetMapping(value="/{name}")
	public ResponseEntity<Product> getProduct(@PathVariable(value = "name") String nameProduct) {
		Product product = productService.getProduct(nameProduct); 
		if (product != null) {
			return ResponseEntity.ok(product);
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
	        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> post(@Valid Product productParam)
    {
		Product product = productService.saveProduct(productParam);
		if (product != null) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
    }
	
	@PutMapping("product")
	public ResponseEntity<?> saveOrder(@Valid @RequestBody Product productParam) {
		Product product = productService.saveProduct(productParam);
		if (product != null) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
}
