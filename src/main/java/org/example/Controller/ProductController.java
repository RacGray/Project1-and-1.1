package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Exception.ProductException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import org.example.Service.ProductService;
import org.example.DAO.SellerDao;
import org.example.DAO.ProductDao;

import java.util.List;

public class ProductController
{
    SellerService sellerService;

    ProductService productService;

    SellerDao sellerDao;

    ProductDao productDao;

    public ProductController(SellerService sellerService, ProductService productService, SellerDao sellerDao, ProductDao productDao)
    {
        this.sellerService = sellerService;
        this.productService = productService;
        this.sellerDao = sellerDao;
        this.productDao = productDao;
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

        api.get("seller", context ->
        {
            List<Seller> sellerList = sellerService.getAllSeller();
            context.json(sellerList);

        });
        api.get("product", context ->
        {
            List<Product> productList = productService.getAllProduct();
            context.json(productList);
        });

        api.get("product/{productSeller}", context ->
        {
            Product p = productDao.getProductByProductSeller(context.pathParam("productSeller"));
            if (p == null)
            {
                context.status(404);
                context.result("This product seller not found.");
            }
            else
            {
                context.json(p);
                context.status(200);
            }

        });
        api.post("seller", context ->
        {
            System.out.println("Seller post happening");
            try
            {
                ObjectMapper om = new ObjectMapper();

                Seller s = om.readValue(context.body(), Seller.class);
                //System.out.println("Seller being set, s: " + s.toString());

                //sellerService.saveSeller(s);
                sellerDao.insertSeller(s);
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
//                Product newProduct = productService.saveProduct(p);
                productDao.insertProduct(p);
                System.out.println("newProduct: " + p);
                context.status(201);
                context.json(p);

                //System.out.println("productList: " + productService.getProductList());
            }

            catch (JsonProcessingException e)
            {
                context.result(e.getMessage());
                context.status(400);
            }
        });
        api.get("product/{id}", context ->
        {
            int productId = (int) Long.parseLong(context.pathParam("id"));
            Product p = productService.getProductById();
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
            /*int id = (int) Long.parseLong(context.pathParam("id"));
            ObjectMapper om = new ObjectMapper();
            Product updatedProduct = om.readValue(context.body(), Product.class);*/

            //productService.updateProduct(id, updatedProduct);
            Product p = productService.updateProductPrice((int) Long.parseLong(context.pathParam("id")), Double.parseDouble(context.pathParam("newPrice")));
                context.status(200);
                //context.result("This product was updated.");
            context.json(p);

        });

        api.delete("product/{id}", context ->
        {
            int productId = (int) Long.parseLong(context.pathParam("productId"));
            Product p = productService.deleteProductById(productId);
            if(p == null)
            {
                context.status(404);
                context.result("Product ID entered was not found.");
            }else
            {
                productService.deleteProductById(productId);
                context.json(p);
                context.status(200);
                context.result("Product deleted");
            }
        });
        api.get("seller/{id}", context ->
        {
            int id = (int) Long.parseLong(context.pathParam("id"));
            Seller s = sellerService.getSellerById(id);
            if (s == null)
            {
                context.status(404);
                context.result("This seller ID not found.");
            }
            else
            {
                context.json(s);
                context.status(200);
            }

        });
        return api;

    }
}



