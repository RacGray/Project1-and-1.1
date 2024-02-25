package org.example.Model;

import java.text.DecimalFormat;
import java.util.Objects;

public class Product
{
    public int productId;
    public String productName;
    public String productSeller;
    public double productPrice;

    public int productSellerId;

    public Product()
    {

    }

    public Product(int productId, String productName, String productSeller, double newPrice, int sellerId)
    {
    }

    public Product(int productId, String productName)
    {
    }

    public int getProductId()
    {
        return productId;
    }


//    public void setProductId(int productId)
//    {
//        this.productId = productId;
//    }
//
//    public void setProductName(String productName)
//    {
//        this.productName = productName;
//    }
//
//    public void setProductSeller(String productSeller)
//    {
//        this.productSeller = productSeller;
//    }
//
//    public double getProductPrice()
//    {
//        return productPrice;
//    }
//
//    public void setProductPrice(double productPrice)
//    {
//        this.productPrice = productPrice;
//    }
//
//    public void setProductSellerId(int sellerId)
//    {
//        this.productSellerId = sellerId;
//    }





    public Product(int productId, String productName, String productSeller, int productSellerId)
    {
    }


//    public Product(String productName, String productSeller, String productPrice, int sellerId)
//    {
//        DecimalFormat decFor = new DecimalFormat("0.00");
//        this.productName = productName;
//        this.productSeller = productSeller;
//        this.productPrice = Double.parseDouble(decFor.format(productPrice));
//        this.productSellerId = sellerId;
//    }

    public Product(String productName, String productSeller, double productPrice, int sellerId)
    {
        DecimalFormat decFor = new DecimalFormat("0.00");
        this.productName = productName;
        this.productSeller = productSeller;
        this.productPrice = Double.parseDouble(decFor.format(productPrice));
        this.productSellerId = sellerId;
    }

    public String getProductName(){return productName;}


    public String getProductSeller()
    {
        return productSeller;
    }

    public double getProductPrice() {return productPrice;}


    public int getProductSellerId()
    {
        return productSellerId;
    }

    public void setProductId(int id)
    {
    }

    public void setProductSeller()
    {
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


}
