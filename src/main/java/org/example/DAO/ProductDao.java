package org.example.DAO;

import org.example.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao
{
    Connection conn;
    public ProductDao(Connection conn)
    {
        this.conn = conn;
    }
    public List<Product> getAllProduct(){
        List<Product> productResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Product");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                String productSeller = resultSet.getString("productSeller");
                int productSellerId = resultSet.getInt("productSellerId");
                Product p;
                p = new Product (productId, productName, productSeller, productSellerId);
                productResults.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productResults;
    }

    public void insertProduct(Product p){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into" +
                    "Product (productName, productSeller, productPrice, productSellerId) values (?, ?, ?, ?)");

            //ps.setInt(1, p.getProductId());
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getProductSeller());
            ps.setDouble(3, p.getProductPrice());
            ps.setInt(4, p.getProductSellerId());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Product getProductById(int ProductId){
        try
        {
            PreparedStatement ps = conn.prepareStatement("select * from product where productId = ?");
            int productId = 0;
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                String productSeller = rs.getString("productSeller");
                int productSellerId = rs.getInt("productSellerId");
                Product p = new Product(productId, productName, productSeller, productSellerId); // REFACTOR
                return p;
            }
            else
            {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Product updateProductPrice(int id, double newPrice)
    {
        /*
        UPDATE table
SET col = new_value
WHERE col = old_value;
         */
        try
        {
            PreparedStatement ps = conn.prepareStatement("update product set productPrice = " + newPrice + " where productId = " + id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                String productSeller = rs.getString("productSeller");
                int productSellerId = rs.getInt("productSellerId");
                Product p = new Product(productId, productName, productSeller, newPrice, productSellerId);

                return p;
            }
            else
            {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Product getProductByProductSeller(String ProductSeller){
        try
        {
            PreparedStatement ps = conn.prepareStatement("select productId, productName from product where productSeller = ?");
            String productSeller = null;
            ps.setString(1, productSeller);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                Product p = new Product(productId, productName);
                return p;
            }
            else
            {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public void deleteProductById(Product currentProduct){
        try
        {
            PreparedStatement ps = conn.prepareStatement("delete from product where productId = ?");
            ps.setInt(1, currentProduct.productId);
            ps.executeQuery();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
