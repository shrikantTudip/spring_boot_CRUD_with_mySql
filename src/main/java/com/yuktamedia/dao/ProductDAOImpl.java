package com.yuktamedia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.yuktamedia.model.Product;
import org.apache.log4j.Logger;

//CRUD operations
public class ProductDAOImpl implements ProductDAO {
	
	private static final Logger logger = Logger.getLogger(ProductDAOImpl.class);
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
    //Create
	@Override
    public void save(Product product) {
		
		String query = "insert into Product (id, name, image, color) values (?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, product.getPid());
			ps.setString(2, product.getProductName());
			ps.setString(3, product.getProductImage());
			ps.setString(4, product.getProductColor());
			
			int out = ps.executeUpdate();
			if (out != 0){
				logger.info("Saved the product with id: " + product.getPid());				
			} 
			else {
				logger.info("Failed to save the product with id: " + product.getPid());
			}
		} catch(SQLException e) {
			logger.info("Exception while inserting into the DB table: " + e.getStackTrace());
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.info("Exception while closing the DB conenction: " + e.getStackTrace());
				e.printStackTrace();
			}
		}
    }
    
    //Read
	@Override
    public Product getById(int id) {
		
		String query = "select name, image, color from Product where id = ?";
		
		Product product = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				product.setPid(id);
				product.setProductName(rs.getString("name"));
				product.setProductImage(rs.getString("image"));
				product.setProductColor(rs.getString("color"));
				
				logger.info("Found the product with id: " + id);
			}else {
				logger.info("Failed to find the product with id: " + id);
			}
		} catch(SQLException e) {
			logger.info("Exception while searching from the DB table: " + e.getStackTrace());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.info("Exception while closing the DB conenction: " + e.getStackTrace());
				e.printStackTrace();
			}
		}
		return product;
    }
    
    //Update
	@Override
    public void update(Product product) {

		String query = "update Product set name=?, image=?, color=? where id=?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getProductImage());
			ps.setString(3, product.getProductColor());
			ps.setInt(4, product.getPid());
			
			int out = ps.executeUpdate();
			if (out != 0) {
				logger.info("Update the product with id: " + product.getPid());
			}
			else {
				logger.info("Failed to update the product with id: " + product.getPid());
			}
		} catch(SQLException e) {
			logger.info("Exception while updating into the DB table: " + e.getStackTrace());
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.info("Exception while closing the DB conenction: " + e.getStackTrace());
				e.printStackTrace();
			}
		}
    }
    
    //Delete
	@Override
    public void deleteById(int id) {
		
		String query = "delete from Product where id=?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			
			if (out != 0) {
				logger.info("Deleted the product with id: " + id);
			}
			else {
				logger.info("Failed to delete the product with id: " + id);
			}
		} catch(SQLException e) {
			logger.info("Exception while deleting from the DB table: " + e.getStackTrace());
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.info("Exception while closing the DB conenction: " + e.getStackTrace());
				e.printStackTrace();
			}
		}
    }
    
    //Get All
	@Override
    public List<Product> getAll() {
    	    
		String query = "select id, name, image, color from Product";
		List<Product> products = new ArrayList<Product>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setPid(rs.getInt("id"));
				product.setProductName(rs.getString("name"));
				product.setProductImage(rs.getString("image"));
				product.setProductColor(rs.getString("color"));
				products.add(product);
				
				logger.info("Found the product with id: " + product.getPid());
			}
		} catch(SQLException e) {
			logger.info("Failed to find the products.");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				logger.info("Exception while closing the DB conenction: " + e.getStackTrace());
				e.printStackTrace();
			}
		}
		
		return products;
    }
}