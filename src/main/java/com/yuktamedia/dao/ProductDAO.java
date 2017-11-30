package com.yuktamedia.dao;

import java.util.List;
import com.yuktamedia.model.Product;

//CRUD operations
public interface ProductDAO {
    //Create
    public void save(Product product);
    
    //Read
    public Product getById(int id);
    
    //Update
    public void update(Product product);
    
    //Delete
    public void deleteById(int id);
    
    //Get All
    public List<Product> getAll();
}
