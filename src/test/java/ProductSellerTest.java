import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
//
//public class ProductSellerTest
//{
//    SellerService sellerService;
//    ProductService productService;
//
//    @Before
//    public void setUp()throws SellerException
//    {
//        sellerService = new SellerService();
//        productService = new ProductService(sellerService);
//    }
//
//    @Test
//    public void sellerServiceEmptyAtStart()
//    {
//        List<Seller> sellerList = sellerService.getAllSeller();
//        assertEquals(0, sellerList.size());
//    }
//
//
//    @Test
//    public void productServiceEmptyAtStart()
//    {
//        List<Product> productList = productService.getProductList();
//        assertEquals(0, productList.size());
//    }

//    @Test
//    public void testAddSeller() throws SellerException
//    {
//        String testValidName = "Red";
//        String testValidName2 = "Green";
//        String testValidName3 = "Yellow";
//
//        Seller seller = new Seller(validName, id);
//        seller.setValidName(testValidName);
//        seller.setValidName(testValidName2);
//        seller.setValidName(testValidName3);
//
//        sellerService.insertSeller(seller);
//
//        assertTrue(sellerService.getSellerList().contains(seller));
//    }

//    @Test
//    public void testAddProduct() throws ProductException
//    {
//        String testProductName = "Stove Top Grill";
//        double testProductPrice = Double.parseDouble("99.99");
//        String testProductSeller = "Red";
//
//        Product product = new Product();
//        product.setProductName(testProductName);
//        product.setProductPrice(testProductPrice);
//        product.setProductSeller(testProductSeller);
//
//        productService.addProduct(product);
//
//        assertTrue(productService.getProductList().contains(product));
//    }

//    @Test
//    public void testSellerDoesNotExist() throws ProductException
//    {
//        String testProductName = "Wood Burning BBQ";
//        double testProductPrice = Double.parseDouble("399.99");
//        String testProductSeller = "Orange";
//
//        Product product = new Product();
//        product.setProductName(testProductName);
//        product.setProductPrice(testProductPrice);
//        product.setProductSeller(testProductSeller);
//
//        try
//        {
//            productService.addProduct(product);
//        } catch (ProductException e)
//        {
//            throw new RuntimeException(e);
//        }
//
//    }

}
