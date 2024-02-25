package org.example;

import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.example.DAO.ProductDao;
import org.example.DAO.SellerDao;
import org.example.Service.SellerService;
import org.example.Service.ProductService;
import org.example.Util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main
{

    public static void main(String[] args){

        Connection conn = ConnectionSingleton.getConnection();
        SellerDao sellerDao = new SellerDao(conn);
        ProductDao productDao = new ProductDao(conn);
        SellerService sellerService = new SellerService(sellerDao);
        ProductService productService = new ProductService(sellerService, productDao);
        ProductController productController = new ProductController(sellerService, productService, sellerDao, productDao);

        Javalin api = productController.getAPI();
        api.start(9922);

    }
}
