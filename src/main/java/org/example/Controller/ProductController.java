package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import org.example.Service.ProductService;

import javax.xml.namespace.QName;
import java.util.List;

public class ProductController
{
    SellerService sellerService;

    ProductService productService;

    public ProductController(SellerService sellerService, ProductService productService)
    {
        this.sellerService = sellerService;
        this.productService = productService;
    }

    public Javalin getAPI()
    {
        Javalin api = Javalin.create();

        api.get("health", context -> {
            context.result("Server is UP");
        });
//          plan out some of our endpoints here.
//          Need 'a get' for both Seller and Product
//          Need 'a post' for both Seller and Product
//          Later I will showcase an endpoint that requires usage of both the author and painting service
//          - and exception handling with the controller
//          Test case/logging
        api.get("seller", context ->
        {
            List<Seller> sellerList = sellerService.getSellerList();
            context.json(sellerList);
        });
        api.get("product", context ->
        {
            List<Product> productList = productService.getProductList();
            context.json(productList);
        });
        api.post("seller", context ->
        {
            System.out.println("Seller post happening");
            try
            {
                ObjectMapper om = new ObjectMapper();
                Seller a = om.readValue(context.body(), Seller.class);
//This code, sellerService.addAuthor is a risky line of code; we should add a Custom Exception for this later.
//Line above this could also potentially cause the JsonProcessingException to trigger as well.
                sellerService.addSeller(a);
                context.status(201);
            } catch (JsonProcessingException e)
            {
                context.status(400);
            }
        });
        api.post("product", context ->
        {
            System.out.println("Product post happening");
            try
            {
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(context.body(), Product.class);
                Product newProduct = productService.addProduct(p);
                System.out.println("newProduct: " + newProduct);
                context.status(201);
                context.json(newProduct);

                //System.out.println("productList: " + productService.getProductList());
            }
            /*catch (SellerException e)
            {
                context.result(e.getMessage());
                context.status(400);
            }*/ catch (JsonProcessingException e)
            {
                context.result(e.getMessage());
                context.status(400);
            } catch (ProductException e)
            {
                context.result(e.getMessage());
                context.status(400);
            }
        });
        api.get("product/{id}", context ->
        {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.getProductByID(id);
            if (p == null)
            {
                context.status(404);
                context.result("This product ID not found.");
            }
            else
            {
                context.json(p);
                context.status(200);
            }

        });
        api.put("product/{id}", context ->
        {
            long id = Long.parseLong(context.pathParam("id"));
            ObjectMapper om = new ObjectMapper();
            Product updatedProduct = om.readValue(context.body(), Product.class);

            productService.updateProduct(id, updatedProduct);

                context.status(200);
                context.result("This product was updated.");

        });

        api.delete("product/{id}", context ->
        {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.getProductByID(id);
            if(p == null)
            {
                context.status(404);
                context.result("Product ID entered was not found.");
            }else
            {
                productService.deleteProductByID(id);
                context.json(p);
                context.status(200);
                context.result("Product deleted");
            }
        });
        return api;

//      api.get("product/{productSeller}", context ->
//      {
//          Product p = productService.getProductByProductSeller(context.pathParam("productSeller"));
//          if (p == null)
//          {
//              context.status(404);
//              context.result("This product seller not found.");
//          }
//          else
//          {
//              context.json(p);
//              context.status(200);
//          }

//      });
    }
}



