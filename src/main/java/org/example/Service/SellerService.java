package org.example.Service;

import org.example.DAO.SellerDao;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;

import java.util.List;


public class SellerService
{
    SellerDao sellerDao;

    public SellerService(SellerDao sellerDao) {this.sellerDao = sellerDao;}

    public List<Seller> getAllSeller(){
        List<Seller> sellerList = sellerDao.getAllSeller();
        return sellerList;
    }

    public void saveSeller(Seller s){sellerDao.insertSeller(s);}

    public Seller getSellerById(int id) throws SellerException{
        Seller s = sellerDao.getSellerById(id);
        if(s == null){
            throw new SellerException("This seller was not found in the database.");
        }else{
            return s;
        }
    }

    public Seller addSeller(Seller s) throws ProductException
    {
        //System.out.println("addProduct running");
        //System.out.println("p.getProductName(): " + p.getProductName());
        //System.out.println("p.getProductSeller(): " + p.getProductSeller());
        if ((s.getValidName() == null || s.getValidName().isEmpty()))
        {
            throw new ProductException("Product and Seller Name fields must be non-null");
        }

        int id = (int) (Math.random() * Long.MAX_VALUE);
        s.setValidName();
        s.setId(id);
        getAllSeller().add(s);
        return s;
    }


}
