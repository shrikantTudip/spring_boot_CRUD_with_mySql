package com.yuktamedia.service;

import com.yuktamedia.dao.ProductDAO;
import com.yuktamedia.model.Product;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	public ProductDAO productDAO;
	
	public ProductServiceImpl() {
		//Get the Spring Context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
				
        //Get the ProductDAO Bean
        productDAO = ctx.getBean("productDAO", ProductDAO.class);
	}
	
	@Override
	public Iterable<Product> listAllProducts() {
		return productDAO.getAll();
	}

	@Override
	public Product getProductById(Integer id) {
		return productDAO.getById(id.intValue());
	}

	@Override
	public Product saveProduct(Product product) {
		productDAO.save(product);
		return product;
    }

	@Override
	public Product updateProduct(Product product) {
		productDAO.update(product);
		return product;
	}
	
	@Override
	public void deleteProductById(Integer id) {
		productDAO.deleteById(id.intValue());
	}
	
}
