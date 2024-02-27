import org.example.Controller.ProductController;
import org.example.DAO.ProductDao;
import org.example.DAO.SellerDao;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.example.Util.ConnectionSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class ProductSellerTest
{
    SellerService sellerService;
    ProductService productService;

    ProductDao productDao;

    SellerDao sellerDao;
    Connection conn = ConnectionSingleton.getConnection();
    ProductController productController;

    SellerException sellerException;

    ProductException productException;

    @Before
    public void setUp()throws SellerException
    {
        sellerDao = new SellerDao(conn);
        sellerService = new SellerService(sellerDao);

        productDao = new ProductDao(conn, sellerService);
        productService = new ProductService(sellerService, productDao);

        productController = new ProductController(sellerService, productService, sellerDao, productDao);
    }

    @Before
    public void restDataBase()
    {
        ConnectionSingleton.resetTestDatabase();
    }

    @Test
    public void sellerServiceEmptyAtStart()
    {
        if (sellerService != null){
        List<Seller> sellerList = sellerService.getAllSeller();
        assertEquals(0, sellerList.size());}
    }


    @Test
    public void productServiceEmptyAtStart()
    {
        if (productService != null){
        List<Product> productList = productService.getAllProduct();
        assertEquals(0, productList.size());}
    }

    @Test
    public void testAddSeller() throws SellerException
    {
        String testValidName = "Red";
        String testValidName2 = "Green";
        String testValidName3 = "Yellow";

        Seller seller = new Seller(1,testValidName);
        Seller seller2 = new Seller(2,testValidName2);
        Seller seller3 = new Seller(3,testValidName3);

        sellerDao.insertSeller(seller);

        assertTrue(sellerService.getAllSeller().contains(seller));
    }

    @Test
    public void testAddProduct() throws ProductException, SellerException
    {
        int testProductSellerId = 23;
        String testProductName = "Stove Top Grill";
        double testProductPrice = Double.parseDouble("99.99");
        String testProductSeller = "Red";

        Seller seller = new Seller(23, "Red");

        Product product = new Product();
        product.setProductSellerId(23);
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setProductSeller(testProductSeller);

        sellerDao.insertSeller(seller);
//        productDao.insertProduct(product);

        try
        {
            productService.addProduct(product);
        } catch (ProductException | SellerException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSellerDoesNotExist() throws ProductException
    {
        String testProductName = "Wood Burning BBQ";
        double testProductPrice = Double.parseDouble("399.99");
        String testProductSeller = "Orange";

        Seller seller = new Seller();
        seller.setId(10);
        seller.setValidName();

        Product product = new Product();
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setProductSeller(testProductSeller);

        try {
            productService.addProduct(product);
            Assert.fail("This seller was not found in the database.");
        }catch (Exception e){

        }

    }
    @Test
    public void testProductUpdate() throws ProductException
    {
            List<Product>productList = productDao.getAllProduct();

        String testProductName = "Camp Stove Top Grill";
        double testProductPrice = Double.parseDouble("399.99");
        String testProductSeller = "Orange";

        Seller seller = new Seller(23, "Orange");

        Product product = new Product();
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setProductSeller(testProductSeller);

        sellerDao.insertSeller(seller);

        productDao.insertProduct(product);
        int productId = product.productId;

        String updatedProductName = "Camp Fire Pit Grill";
        double updatedProductPrice = 299.99;

        Product updatedProduct = new Product();
        updatedProduct.setProductName(updatedProductName);
        updatedProduct.setProductPrice(updatedProductPrice);
        try {
            productService.updateProduct(productId, updatedProduct);
        }catch (Exception e){

        }

    }

    @Test
    public void testProductDelete() throws ProductException
    {
        List<Product>productList = productDao.getAllProduct();

        String testProductName = "Wood Burning Grill";
        double testProductPrice = Double.parseDouble("99.99");
        String testProductSeller = "Green";

        Seller seller = new Seller(26, "Green");

        Product product = new Product();
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setProductSeller(testProductSeller);

        sellerDao.insertSeller(seller);

        productDao.insertProduct(product);
        int productId = product.productId;

        productDao.deleteProductById(productId);

        assertEquals(0, productList.size());
    }

}
