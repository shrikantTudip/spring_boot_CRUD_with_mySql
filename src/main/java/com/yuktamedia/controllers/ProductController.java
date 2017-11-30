/**
 * 
 */
package com.yuktamedia.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuktamedia.model.Product;
import com.yuktamedia.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.log4j.Logger;

/**
 * @author shrikant
 *
 */

@RestController
public class ProductController {
    private ProductService productService;
    
    private static final Logger logger = Logger.getLogger(ProductController.class);
    
    public ProductController() {
        //TODO: Get any initialization steps.
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Get the list of products.", nickname = "get_all_products", 
        response = Product.class, responseContainer = "List")
    @RequestMapping(value = "/products", method = {RequestMethod.GET}, produces = "application/json")
    @ResponseBody
    public Iterable<Product> list() {
        return productService.listAllProducts();
    }
    
    @ApiOperation(value = "Get the product for given id.", nickname = "get_product_by_id", response = Product.class)
    @RequestMapping(value = "product/{product_id}", method = {RequestMethod.GET}, produces = "application/json")
    public Product showProduct(@ApiParam(value="Id of the Product.", required=true)
        @PathVariable (value="product_id") Integer productId) {
        
        logger.info("Returning the product with id: " + productId);
        return productService.getProductById(productId);
    }

    @ApiOperation(value = "Create a new product.", nickname = "create_product")
    @RequestMapping(value = "product/new", method = {RequestMethod.POST}, produces = "application/json")
    public Product newProduct(@ApiParam(value="JSON Payload containing parameters to create the Product.", required=true)
        @RequestBody JsonNode payload,
        HttpSession session) {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> params = mapper.convertValue(payload, Map.class);

        Product pn = new Product();
        Integer pid = (Integer)params.get("pid");
        pn.setPid(pid);
        pn.setProductName(params.get("productName").toString());
        pn.setProductColor(params.get("productColor").toString());
        pn.setProductImage(params.get("productImage").toString());
        
        logger.info("Saving the product with id: " + pid);
        return productService.saveProduct(pn);
    }

    @ApiOperation(value = "Update an existing product.", nickname = "update_product")
    @RequestMapping(value = "product/update", method = {RequestMethod.PUT}, produces = "application/json")
    public Product updateProduct(@ApiParam(value="JSON Payload containing parameters to edit the Product.", required=true)
        @RequestBody JsonNode payload,
        HttpSession session) {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> params = mapper.convertValue(payload, Map.class);

        Product pn = new Product();
        Integer pid = (Integer)params.get("pid");
        pn.setPid(pid);
        pn.setProductName(params.get("productName").toString());
        pn.setProductColor(params.get("productColor").toString());
        pn.setProductImage(params.get("productImage").toString());
        
        logger.info("Updating the product with id: " + pid);
        return productService.updateProduct(pn);
    }

    @ApiOperation(value = "Delete the product for given id.", nickname = "delete_product_by_id", response = Product.class)
    @RequestMapping(value = "product/{product_id}", method = {RequestMethod.DELETE}, produces = "application/json")
    public void deleteProduct(@ApiParam(value="Id of the Product.", required=true)
        @PathVariable (value="product_id") Integer productId) {
        
        logger.info("Deleting the product with id: " + productId);
        productService.deleteProductById(productId);
    }
}
