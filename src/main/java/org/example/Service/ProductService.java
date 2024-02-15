package org.example.Service;

import org.example.Exception.ProductException;
import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductService
{
        SellerService sellerService;
        List<Product> productList;

//Using technique called Dependency Injection to call forth the SellerService and make
//sure it is scoped to be available throughout the project; to allow re-use of the same service.

//Lisa asked about: (SellerService sellerService) would just be an 'object' in this example.
        public ProductService(SellerService sellerService)
        {
            this.sellerService = sellerService;
            productList = new ArrayList<>();
        }

        public List<Product> getProductList()
        {
            return productList;
        }

        public Product addProduct(Product p) throws ProductException
        {
            //System.out.println("addProduct running");
            //System.out.println("p.getProductName(): " + p.getProductName());
            //System.out.println("p.getProductSeller(): " + p.getProductSeller());
            if ((p.getProductName() == null || p.getProductName().isEmpty()) || (p.getProductSeller() == null || p.getProductSeller().isEmpty()))
            {
                throw new ProductException("Product and Seller Name fields must be non-null");
            }

            boolean validNameFound = false;

            for(int i=0; i < sellerService.sellerList.size(); i++)
            {
                if (Objects.equals(sellerService.sellerList.get(i).getValidName(), p.productSeller))
                {
                    validNameFound = true;
                }

                if(!validNameFound)
                {
                    throw new ProductException("This is not an authorized Product Seller.");
                }
            }

            long id = (long) (Math.random() * Long.MAX_VALUE);
            p.setProductId(id);
            productList.add(p);
            return p;
        }
//Def want to test this method in unit testing
        public Product getProductByID(Long id)
        {
            for (int i=0; i < productList.size(); i++)
            {
                Product currentProduct = productList.get(i);
                if (currentProduct.getProductId() == id)
                {
                    return currentProduct;
                }
            }
            return null;


        }

    public void updateProduct(Long id, Product updatedProduct)
    {
        Product productToUpdate = getProductByID(id);
        {
            if (productToUpdate !=null)
            {
                productToUpdate.setProductName(updatedProduct.getProductName());
                productToUpdate.setProductPrice(updatedProduct.getProductPrice());
            }
        }

    }
    public void deleteProductByID(Long id){
        for(int i=0; i < productList.size(); i++){
            Product currentProduct = productList.get(i);
            if(currentProduct.getProductId() == id) {
                productList.remove(getProductByID(id));
            }
        }

    }

    }
//        public Product getProductByProductSeller(String name)
//        {
//            for (Product currentProduct : productList)
//            {
//                if (Objects.equals(currentProduct.getProductSeller(), name))
//                {
//                    return currentProduct;
//                }
//            }
//            return null;
//        }



