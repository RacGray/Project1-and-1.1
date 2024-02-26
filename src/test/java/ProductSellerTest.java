import org.example.DAO.ProductDao;
import org.example.DAO.SellerDao;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductSellerTest
{
    SellerService sellerService;
    ProductService productService;

    ProductDao productDao;

    SellerDao sellerDao;

    SellerException sellerException;

    ProductException productException;

    @Before
    public void setUp()throws SellerException
    {
        sellerService = new SellerService();
        productService = new ProductService();
    }

    @Test
    public void sellerServiceEmptyAtStart()
    {
        List<Seller> sellerList = sellerService.getAllSeller();
        assertEquals(0, sellerList.size());
    }


    @Test
    public void productServiceEmptyAtStart()
    {
        List<Product> productList = productService.getAllProduct();
        assertEquals(0, productList.size());
    }

    @Test
    public void testAddSeller() throws SellerException
    {
        String testValidName = "Red";
        String testValidName2 = "Green";
        String testValidName3 = "Yellow";

        Seller seller = new Seller(id, validName);
        seller.setValidName(testValidName);
        seller.setValidName(testValidName2);
        seller.setValidName(testValidName3);

        sellerDao.insertSeller(seller);

        assertTrue(sellerService.getAllSeller().contains(seller));
    }

    @Test
    public void testAddProduct() throws ProductException
    {
        String testProductName = "Stove Top Grill";
        double testProductPrice = Double.parseDouble("99.99");
        String testProductSeller = "Red";

        Product product = new Product();
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setProductSeller(testProductSeller);

        productDao.insertProduct(product);

        assertTrue(productDao.getAllProduct().contains(product));
    }

    @Test
    public void testSellerDoesNotExist() throws ProductException
    {
        String testProductName = "Wood Burning BBQ";
        double testProductPrice = Double.parseDouble("399.99");
        String testProductSeller = "Orange";

        Product product = new Product();
        product.setProductName(testProductName);
        product.setProductPrice(testProductPrice);
        product.setProductSeller(testProductSeller);

        try
        {
            productService.addProduct(product);
        } catch (ProductException | SellerException e)
        {
            throw new RuntimeException(e);
        }

    }

}
