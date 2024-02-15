package org.example;

import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.example.Service.SellerService;
import org.example.Service.ProductService;

public class Main
{
    public static void main(String[] args)
    {
        SellerService sellerService = new SellerService();
        ProductService productService = new ProductService(sellerService);
        ProductController productController = new ProductController(sellerService, productService);

        Javalin api = productController.getAPI();
        api.start(9922);

    }
}
