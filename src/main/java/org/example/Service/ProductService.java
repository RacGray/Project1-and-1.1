package org.example.Service;

import org.example.DAO.ProductDao;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;

import java.util.List;

public class ProductService
{
        SellerService sellerService;
        ProductDao productDao;
        List<Product> productList;

//Using technique called Dependency Injection to call forth the SellerService and make
//sure it is scoped to be available throughout the project; to allow re-use of the same service.

//Lisa asked about: (SellerService sellerService) would just be an 'object' in this example.
        public ProductService(SellerService sellerService, ProductDao productDao) {
            this.sellerService = sellerService;
            this.productDao = productDao;
//            productList = new ArrayList<>();
        }

        public List<Product> getAllProduct()
        {
            return productDao.getAllProduct();
        }

        public Product saveProduct(Product p) throws ProductException{
            int productId = p.getProductId();
            if(productDao.getProductById(productId) == null){
                productDao.insertProduct(p);
            }else{
                throw new ProductException("Product with this id "+productId+" already exists.");
            }
            return p;
        }

        public Product getProductByProductSeller(String productSeller)
    {
        for (Product currentProduct : productList){
            if(currentProduct.getProductSeller() == productSeller) {
                return currentProduct;
            }
        }
        return null;
    }



        public Product addProduct(Product p) throws ProductException, SellerException
        {

            if ((p.getProductName() == null || p.getProductName().isEmpty()) || (p.getProductSeller() == null || p.getProductSeller().isEmpty()))
            {
                throw new ProductException("Product and Seller Name fields must be non-null");
            }

            if(sellerService.getSellerByName(p.productSeller) != null)
            {
                return productDao.insertProduct(p);
            }
            else {
                throw new ProductException("Seller must already exist in database");
            }
        }
//Def want to test this method in unit testing


    public Product updateProduct(int id, Product updatedProduct)
    {
        Product productToUpdate = productDao.getProductById(id);

        if (productToUpdate != null)
        {
            productToUpdate.setProductName(updatedProduct.getProductName());
            productToUpdate.setProductPrice(updatedProduct.getProductPrice());
            productToUpdate.setProductSeller(updatedProduct.getProductSeller());

            return productDao.updateProduct(productToUpdate);
        }
        else
        {
            return null;
        }
    }



    public Product deleteProductById(int id){

        productDao.deleteProductById(id);
        return null;
    }

    public Product getProductById()
    {
        for (int i=0; i < productList.size(); i++){
            Product currentProduct = productList.get(i);
            int productId = 0;
            if (currentProduct.getProductId() == productId){
                return currentProduct;
            }
        }return null;
    }
}





