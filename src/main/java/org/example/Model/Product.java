package org.example.Model;

import java.text.DecimalFormat;
import java.util.Objects;

public class Product
{
    public long productId;
    public String productName;
    public String productSeller;

    public double productPrice;

    public Product()
    {

    }
    public long getProductId()
    {
        return productId;
    }

    public void setProductId(long productId)
    {
        this.productId = productId;
    }

    public Product(String productName, String productSeller, String productPrice)
    {

        DecimalFormat decFor = new DecimalFormat("0.00");
        this.productName = productName;
        this.productSeller = productSeller;
        this.productPrice = Double.parseDouble(decFor.format(productPrice));
    }

    public String getProductName(){return productName;}

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductSeller()
    {
        return productSeller;
    }

    public void setProductSeller(String productSeller)
    {
        this.productSeller = productSeller;
    }

    public double getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Product product = (Product) o;
        return productId == product.productId && Objects.equals(productName, product.productName) && Objects.equals(productSeller, product.productSeller) && Double.compare(productPrice, product.productPrice) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(productId, productName, productSeller, productPrice);
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "productID='" + productId +
                ", title='" + productName + '\'' +
                ", productSeller='" + productSeller + '\'' +
                ", productPrice='" + String.format("%.2f", productPrice) + '\'' +
                '}';
    }
}
