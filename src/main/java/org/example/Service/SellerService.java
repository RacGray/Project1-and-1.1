package org.example.Service;

import org.example.Exception.SellerException;
import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;


public class SellerService
{
    List<Seller> sellerList;

    public SellerService()
    {
        this.sellerList = new ArrayList<>();
    }

    public List<Seller> getSellerList(){
        return sellerList;
    }

    public void addSeller(Seller newSeller) throws SellerException
    {
        for(Seller existing : sellerList)
        {
            if(existing.getValidName().equals(newSeller.getValidName()))
            {
                throw new SellerException("This Seller already exists in the system");
            }
        }
        sellerList.add(newSeller);
    }
}
