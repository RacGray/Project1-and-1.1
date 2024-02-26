package org.example.Model;

import java.text.DecimalFormat;
import java.util.Objects;

import org.example.Exception.SellerException;
import org.example.Service.SellerService;

public class Product
{
    public int productId;
    public String productName;
    public String productSeller;
    public double productPrice;

    public int productSellerId = -1;


    public Product()
    {
    }

    public Product(int productId, String productName, String productSeller, double productPrice, int productSellerId)
    {
        this.productId = productId;
        this.productName = productName;
        this.productSeller = productSeller;
        this.productPrice = productPrice;
        this.productSellerId = productSellerId;
    }

    public Product(int productId, String productName)
    {
        this.productId = productId;
        this.productName = productName;
    }

    public Product(int productId, String productName, String productSeller)
    {
        this.productId = productId;
        this.productName = productName;
        this.productSeller = productSeller;
    }

    public Product(int productId, String productName, String productSeller, Double productPrice)
    {
        this.productId = productId;
        this.productName = productName;
        this.productSeller = productSeller;
        this.productPrice = productPrice;
    }

    public int getProductId()
    {
        return productId;
    }



    public Product(String productName, String productSeller, double productPrice)
    {
        DecimalFormat decFor = new DecimalFormat("0.00");
        this.productName = productName;
        this.productSeller = productSeller;
        this.productPrice = Double.parseDouble(decFor.format(productPrice));
    }

    public String getProductName(){return productName;}

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductSeller()
    {
        return productSeller;
    }

    public void setProductSeller(String newName) {
        this.productSeller = newName;
    }


    public double getProductPrice() {return productPrice;}

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Product))
        {
            return false;
        }
        Product product = (Product) o;
        return productId == product.productId && Double.compare(productPrice, product.productPrice) == 0 && productSellerId == product.productSellerId && Objects.equals(productName, product.productName) && Objects.equals(productSeller, product.productSeller);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(productId, productName, productSeller, productPrice, productSellerId);
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productSeller='" + productSeller + '\'' +
                ", productPrice=" + productPrice +
                ", productSellerId=" + productSellerId +
                '}';
    }


    public int getProductSellerId(SellerService sellerService) throws SellerException
    {
        int newSellerId = sellerService.getSellerIdByName(productSeller);

        if(productSellerId == -1)
        {
            this.productSellerId = newSellerId;
        }

        return newSellerId;
    }

    public void setProductSellerId(int newId)
    {
        if(productSellerId == -1)
        {
            productSellerId = newId;
        }
    }
}
