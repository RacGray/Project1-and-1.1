package org.example.Service;

import org.example.DAO.ProductDao;
import org.example.Exception.ProductException;
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

//        public Product getAllProductsById(int productId) throws ProductException{
//            Product p = productDao.getAllProductsById(productId);
//            if(p == null){
//                throw new ProductException("That product does not exist in the database.");
//            }else{
//                return p;
//            }
//        }


        public Product addProduct(Product p) throws ProductException
        {
            //System.out.println("addProduct running");
            //System.out.println("p.getProductName(): " + p.getProductName());
            //System.out.println("p.getProductSeller(): " + p.getProductSeller());
            if ((p.getProductName() == null || p.getProductName().isEmpty()) || (p.getProductSeller() == null || p.getProductSeller().isEmpty()))
            {
                throw new ProductException("Product and Seller Name fields must be non-null");
            }

//            boolean validNameFound = false;
//
//            int sellerId = -1;
//
//            for(int i=0; i < sellerService.getAllSeller(); i++)
//            {
//                if (p.equals(sellerService.getAllSeller().get(i).getValidName(), p.productSeller))
//                {
//                    validNameFound = true;
//                    sellerId = sellerService.sellerList.get(i).getId();
//                }
//
//                if(!validNameFound)
//                {
//                    throw new ProductException("This is not an authorized Product Seller.");
//                }
//            }

            int id = (int) (Math.random() * Long.MAX_VALUE);
            p.setProductId(id);
            p.setProductSeller();
            productList.add(p);
            return p;
        }
//Def want to test this method in unit testing


    public void updateProduct(int id, Product updatedProduct)
    {
        /*Product productToUpdate = getProductByID();
        {
            if (productToUpdate !=null)
            {
                productToUpdate.setProductName(updatedProduct.getProductName());
                productToUpdate.setProductPrice(updatedProduct.getProductPrice());
            }
        }*/

    }

    public Product updateProductPrice(int productId, double newPrice)
    {
        return productDao.updateProductPrice(productId, newPrice);
    }

    public Product deleteProductById(int id){
            List<Product> productList = productDao.getAllProduct();
            for(int i=0; i < productList.size(); i++){
                Product currentProduct = productList.get(i);
                if(currentProduct.getProductId() == id){
                    productDao.deleteProductById(currentProduct);
                }
            }
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


//    public Product getProductByProductSeller(String productSeller)
//    {
//        for (Product currentProduct : productList){
//            if(currentProduct.getProductSeller() == productSeller) {
//                return currentProduct;
//            }
//        }
//        return null;
//    }
//}
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



