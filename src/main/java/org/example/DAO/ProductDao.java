package org.example.DAO;

import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Service.SellerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao
{
    Connection conn;
    SellerService sellerService;
    public ProductDao(Connection conn, SellerService sellerService)
    {
        this.conn = conn;
        this.sellerService = sellerService;
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
                double productPrice = Double.valueOf(resultSet.getString("productPrice"));
                int productSellerId = resultSet.getInt("productSellerId");
                Product p = new Product (productId, productName, productSeller, productPrice, productSellerId);
                productResults.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productResults;
    }

    public Product insertProduct(Product p){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "Product (productName, productSeller, productPrice, productSellerId) values (?, ?, ?, ?)");

            //ps.setInt(1, p.getProductId());
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getProductSeller());
            ps.setDouble(3, p.getProductPrice());
            ps.setInt(4, p.getProductSellerId(sellerService));
            ps.executeUpdate();

            PreparedStatement select = conn.prepareStatement("select * from product where productName = ?");
            select.setString(1, p.getProductName());
            ResultSet rs = select.executeQuery();

            if (rs.next())
            {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                String productSeller = rs.getString("productSeller");
                int productSellerId = rs.getInt("productSellerId");
                double productPrice = rs.getDouble("productPrice");
                return new Product(productId, productName, productSeller, productPrice, productSellerId);
            }
            else
            {
                return null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (SellerException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Product getProductById(int productId){
        try
        {
            PreparedStatement ps = conn.prepareStatement("select * from product where productId = ?");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                String productSeller = rs.getString("productSeller");
                Double productPrice = rs.getDouble("productPrice");
                Product p = new Product(productId, productName, productSeller,productPrice);
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

    public Product updateProduct(Product updatedProduct){
        try{
            PreparedStatement ps = conn.prepareStatement(" update product set productName = ?, productSeller = ?, productPrice = ?" +
                    "where productId = ?");
            ps.setString(1, updatedProduct.getProductName());
            ps.setString(2, updatedProduct.getProductSeller());
            ps.setDouble(3, updatedProduct.getProductPrice());
            ps.setInt(4, updatedProduct.getProductId());
            //System.out.println(updatedProduct.getProductName() + " " + updatedProduct.getProductSeller() + " " + updatedProduct.getProductPrice() + " " + updatedProduct.getProductID());
            ps.executeUpdate();

            PreparedStatement select = conn.prepareStatement("select * from product where productId = ?");
            select.setInt(1, updatedProduct.getProductId());
            ResultSet rs = select.executeQuery();

            if (rs.next())
            {
                int productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                String productSeller = rs.getString("productSeller");
                int productSellerId = rs.getInt("productSellerId");
                double productPrice = rs.getDouble("productPrice");
                return new Product(productId, productName, productSeller, productPrice, productSellerId);
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


    public void deleteProductById(int id){
        try
        {
            PreparedStatement ps = conn.prepareStatement("delete from product where productId = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
